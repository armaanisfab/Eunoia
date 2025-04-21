package com.example.eunoia.feature.user.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eunoia.feature.user.presentation.viewmodel.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {
    val usersState = viewModel.users.collectAsState()

    LazyColumn {
        items(usersState.value, key = { it.id }) { user ->
            Column {
                Text(text = "Name: ${user.displayName}")
                Text(text = "Email: ${user.email}")
            }
        }
    }
}