package com.isaac_dolphin.anki.domain.models

import java.util.UUID

data class Deck(
    val id: UUID,
    val title: String,
    val cards: MutableList<Card>
)