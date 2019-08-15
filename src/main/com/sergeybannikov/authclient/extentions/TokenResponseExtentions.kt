package com.sergeybannikov.authclient.extentions

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.sergeybannikov.authclient.response.TokenResponse

fun Result<TokenResponse, FuelError>.getToken() = this.fold({ it }, {
    try {
        val extError = it.errorData.toString(kotlin.text.Charsets.UTF_8)

        if (extError.isNotBlank())
            throw com.sergeybannikov.authclient.exceptions.ExternalKeycloakException(extError)
    } catch (ex: Throwable) {
        kotlin.io.println(ex.toString())
    }
    throw it
})