package com.example.eunoia.feature.profile.data.repository

import com.example.eunoia.feature.auth.data.model.AuthUser
import com.example.eunoia.feature.auth.data.repository.AuthRepository
import com.example.eunoia.feature.profile.data.model.Profile
import com.example.eunoia.feature.profile.data.remote.ProfileService
import java.util.UUID
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileService: ProfileService,
    private val authRepository: AuthRepository
) {

    private suspend fun fetchProfile(userId: UUID): Profile? {
        return profileService.fetchProfile(userId)
    }

    private suspend fun createProfile(profile: Profile): Profile? =
        profileService.createProfile(profile)

    private suspend fun fetchAuthUserDetails(userId: UUID): AuthUser? =
        authRepository.getUserDetails(userId)

    suspend fun getOrCreateProfile(userId: UUID): Profile? {
        return fetchProfile(userId) ?: run {
            val authDetails = fetchAuthUserDetails(userId)
            val email = authDetails?.email ?: "unknown@example.com"
            val username = authDetails?.username ?: email.substringBefore("@")
            createProfile(Profile(username = username, email = email).apply { id = userId })
        }
    }
}
