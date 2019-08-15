package com.sergeybannikov.authclient.repositories

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.jackson.responseObject
import com.sergeybannikov.authclient.extentions.getToken
import com.sergeybannikov.authclient.response.TokenResponse


class DefaultKeycloakTokenRepository(
    val host: String,
    val realm: String,
    val clientId: String,
    val clientSecret: String,
    tokenEndpointRelativePath: String = "/protocol/openid-connect/token"
) : KeycloakTokenRepository {

    val tokenEndpoint: String

    init {
        tokenEndpoint = "${host.trimEnd('/')}/auth/realms/$realm$tokenEndpointRelativePath"
    }

    override fun getToken(login: String, password: String): TokenResponse {

        val (request, response, result) =
            tokenEndpoint.httpPost()
                .set(Headers.CONTENT_TYPE, "application/x-www-form-urlencoded")

                .body(
                    "client_id=$clientId" +
                            "&client_secret=$clientSecret" +
                            "&username=$login" +
                            "&password=$password" +
                            "&grant_type=password"
                )
                .responseObject<TokenResponse>()

        return result.getToken()
    }

    override fun refreshToken(refreshToken: String): TokenResponse {
        val (request, response, result) = tokenEndpoint.httpPost()
            .set(Headers.CONTENT_TYPE, "application/x-www-form-urlencoded")

            .body(
                "client_id=$clientId" +
                        "&client_secret=$clientSecret" +
                        "&grant_type=refresh_token" +
                        "&refresh_token=$refreshToken"
            )
            .responseObject<TokenResponse>()

        return result.getToken()
    }
}