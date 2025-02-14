package com.isaac_dolphin.anki.domain.interfaces

import com.isaac_dolphin.anki.domain.models.Card
import java.util.UUID

interface ICardManager {
    fun getCard(cardId: UUID): Card?
    fun getCards(): List<Card?>
    fun createCard(card: Card)
    fun editCard(cardId: UUID, cardTitle: String) // TODO: use updateCard class.
    fun deleteCard(cardId: UUID)
}