package com.sergeybannikov.authclient.repositories

import io.kotlintest.matchers.string.shouldNotBeEmpty
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec

class DefaultKeycloakTokenRepositoryTest : StringSpec() {

    val repo = DefaultKeycloakTokenRepository(
        host = "https://example.com",
        realm = "myRealm",
        clientId = "myAppClientId",
        clientSecret = "84a05111-f88c-4004-97d0-4b3df787b7ee"
    )

    init {
        "getToken" {
            val result = repo.getToken("test", "123")

            result.accessToken.shouldNotBeEmpty()
            result.refreshToken.shouldNotBeEmpty()
        }
        "refreshToken" {
            val tokenResult = repo.getToken("test", "123")
            val result = repo.refreshToken(tokenResult.refreshToken)

            result.accessToken.shouldNotBeEmpty()
            result.refreshToken.shouldNotBeEmpty()

            result.accessToken shouldNotBe tokenResult.accessToken
        }

    }
}
