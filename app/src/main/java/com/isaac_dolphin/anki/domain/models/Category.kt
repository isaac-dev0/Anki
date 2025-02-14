package com.isaac_dolphin.anki.domain.models

import java.util.UUID

data class Category(
    val id: UUID,
    var title: String,
    val decks: MutableList<Deck>
)