package com.isaac_dolphin.anki.domain.repository

import android.util.Log
import com.isaac_dolphin.anki.domain.interfaces.IDeckManager
import com.isaac_dolphin.anki.domain.models.Deck
import java.util.UUID

class DeckRepository(
    private val decks: MutableList<Deck> = mutableListOf()
): IDeckManager {
    override fun getDeck(deckId: UUID): Deck? {
        try {
            return decks.find { it.id == deckId }
        } catch (e: Exception) {
            Log.e("DeckRepository", "Error getting deck with ID $deckId", e)
            return null
        }
    }

    override fun getDecks(): List<Deck?> {
        try {
            return decks.toList()
        } catch (e: Exception) {
            Log.e("DeckRepository", "Error getting all decks", e)
            return emptyList()
        }
    }

    override fun createDeck(deck: Deck) {
        TODO("Not yet implemented")
    }

    override fun editDeck(deckId: UUID, deckTitle: String) {
        TODO("Not yet implemented")
    }

    override fun deleteDeck(deckId: UUID) {
        TODO("Not yet implemented")
    }

}