package com.sergeybannikov.authclient.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class TokenResponse (
    @get:JsonProperty("access_token")
    val accessToken: String,
    @get:JsonProperty("expires_in")
    val expiresIn: Int,
    @get:JsonProperty("refresh_expires_in")
    val refreshExpiresIn: Int,
    @get:JsonProperty("refresh_token")
    val refreshToken: String,
    @get:JsonProperty("token_type")
    val tokenType: String,
    @get:JsonProperty("not-before-policy")
    val notBeforePolicy: Int,
    @get:JsonProperty("session_state")
    val sessionState: UUID,
    @get:JsonProperty("scope")
    val scope: String
)