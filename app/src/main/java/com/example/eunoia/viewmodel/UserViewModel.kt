package com.example.eunoia.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eunoia.data.model.User
import com.example.eunoia.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> get() = _usersLiveData

    init {
        fetchUsers()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val users = repository.fetchUsers()
                _usersLiveData.value = users
            } catch (e: Exception) {
                _usersLiveData.value = emptyList()
            }
        }
    }
}
