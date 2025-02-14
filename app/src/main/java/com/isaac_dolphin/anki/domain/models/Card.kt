package com.isaac_dolphin.anki.domain.models

import com.isaac_dolphin.anki.domain.enums.ECardType
import java.util.UUID

data class Card(
    val id: UUID,
    val title: String,
    val question: String,
    val answer: String,
    val type: ECardType,
    val difficulty: Int = 1
)
