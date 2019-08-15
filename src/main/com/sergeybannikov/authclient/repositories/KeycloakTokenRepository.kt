package com.sergeybannikov.authclient.repositories

import com.sergeybannikov.authclient.response.TokenResponse

interface KeycloakTokenRepository {
    fun getToken(login: String, password: String): TokenResponse
    fun refreshToken(refreshToken: String): TokenResponse
}