package com.isaac_dolphin.anki.data.model

import java.util.UUID

data class Deck(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var categoryId: String
)