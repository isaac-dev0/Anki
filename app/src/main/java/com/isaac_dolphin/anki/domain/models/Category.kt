package com.isaac_dolphin.anki.domain.models

import java.util.UUID

data class Category(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var userId: String
)