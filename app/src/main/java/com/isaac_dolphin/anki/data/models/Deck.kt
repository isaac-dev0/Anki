package com.isaac_dolphin.anki.data.models

import java.util.UUID

data class Deck(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var categoryId: String
)