package com.DevAsh.RecWalletBackend.Api.Request

import com.fasterxml.jackson.annotation.JsonProperty

class OtpDetails(
        @JsonProperty("number") val number: String,
        @JsonProperty("otp") val otp: String
)
