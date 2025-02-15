package com.isaac_dolphin.anki.domain.interfaces

import com.isaac_dolphin.anki.domain.models.Deck

interface IDeckManager {
    suspend fun getDeck(deckId: String): Deck
    suspend fun getDecks(categoryId: String): List<Deck>
    suspend fun createDeck(deck: Deck)
    suspend fun updateDeck(deck: Deck)
    suspend fun deleteDeck(deckId: String)
    suspend fun addCard(deckId: String, cardId: String)
    suspend fun removeCard(deckId: String, cardId: String)
}