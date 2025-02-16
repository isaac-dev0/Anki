package com.isaac_dolphin.anki.domain.models

import com.isaac_dolphin.anki.domain.enums.ECardType
import java.util.UUID

data class Card(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var question: String,
    var answer: String,
    var type: ECardType,
    var difficulty: Int = 1,
    var deckId: String
)
