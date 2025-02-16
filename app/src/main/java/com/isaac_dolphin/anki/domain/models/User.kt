package com.isaac_dolphin.anki.domain.models

import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(),
    var email: String,
    var password: String
)
