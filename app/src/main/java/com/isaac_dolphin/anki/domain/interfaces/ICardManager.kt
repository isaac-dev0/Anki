package com.isaac_dolphin.anki.domain.interfaces

import com.isaac_dolphin.anki.data.models.Card

interface ICardManager {
    suspend fun getCard(cardId: String): Card
    suspend fun getCards(deckId: String): List<Card>
    suspend fun createCard(card: Card)
    suspend fun updateCard(card: Card)
    suspend fun deleteCard(cardId: String)
}