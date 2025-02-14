package com.isaac_dolphin.anki.domain.interfaces

import com.isaac_dolphin.anki.domain.models.Deck
import java.util.UUID

interface IDeckManager {
    fun getDeck(deckId: UUID): Deck?
    fun getDecks(): List<Deck?>
    fun createDeck(deck: Deck)
    fun editDeck(deckId: UUID, deckTitle: String) // TODO: use updateDeck class instead.
    fun deleteDeck(deckId: UUID)
    // fun addCard(cardId: UUID)
    // fun addCards(cardIds: List<UUID>)
    // fun removeCard(cardId: UUID)
    // fun removeCards(cardIds: List<UUID>)
}