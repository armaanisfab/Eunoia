package com.example.eunoia.feature.user.data.repository

import com.example.eunoia.feature.user.data.model.User
import com.example.eunoia.feature.user.data.remote.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
) {
    suspend fun fetchUsers(): List<User> = userService.fetchUsers()
}
