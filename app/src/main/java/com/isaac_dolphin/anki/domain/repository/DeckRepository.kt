package com.isaac_dolphin.anki.domain.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.isaac_dolphin.anki.domain.interfaces.IDeckManager
import com.isaac_dolphin.anki.domain.models.Deck
import com.isaac_dolphin.anki.utility.exceptions.DeckCreationFailedException
import com.isaac_dolphin.anki.utility.exceptions.DeckDeletionException
import com.isaac_dolphin.anki.utility.exceptions.DeckNotFoundException
import com.isaac_dolphin.anki.utility.exceptions.DeckUpdateFailedException
import com.isaac_dolphin.anki.utility.exceptions.DecksInCategoryNotFoundException
import kotlinx.coroutines.tasks.await

/**
 * Deck for storing Cards.
 *
 * This class holds the CRUD operations for Decks using the "Repository" Design Pattern.
 * @param firestore Database connector for DeckRepository
 *
 * **/
class DeckRepository(
    private val firestore: FirebaseFirestore
): IDeckManager {
    /**
     * Retrieves a deck from the list of decks by its ID.
     *
     * @param deckId The ID of the deck to retrieve.
     * @return The deck with the specified ID, or null if not found.
     *
     * **/
    override suspend fun getDeck(deckId: String): Deck {
        return try {
            val document = firestore
                .collection("decks").document(deckId).get().await()
            document.toObject(Deck::class.java) ?: throw DeckNotFoundException(deckId)
        } catch (e: Exception) {
            Log.e("DeckRepository", "Error getting deck", e)
            throw DeckNotFoundException(deckId)
        }
    }

    /**
     * Retrieves all decks from the list of decks.
     *
     * @param categoryId The ID of the decks from which category.
     * @return Decks as an object. If no decks exist, return an empty object.
     *
     * **/
    override suspend fun getDecks(categoryId: String): List<Deck> {
        return try {
            val query = firestore
                .collection("categories").document(categoryId)
                .collection("decks").get().await()
            query.documents.mapNotNull { it.toObject(Deck::class.java) }
        } catch (e: Exception) {
            Log.e("DeckRepository", "Error getting decks", e)
            throw DecksInCategoryNotFoundException(categoryId)
        }
    }

    /**
     * Creates a new deck within a specified category.
     *
     * @param deck The deck to create.
     *
     * **/
    override suspend fun createDeck(deck: Deck) {
        try {
            firestore
                .collection("categories").document(deck.categoryId)
                .collection("decks").document(deck.id).set(deck).await()
        } catch (e: Exception) {
            Log.e("DeckRepository", "Error creating deck", e)
            throw DeckCreationFailedException(deck)
        }
    }

    /**
     * Edit an existing deck in the repository.
     *
     * @param deck The new deck information to overwrite.
     *
     * **/
    override suspend fun updateDeck(deck: Deck) {
        try {
            val document = firestore
                .collection("categories").document(deck.categoryId)
                .collection("decks").document(deck.id)
            document.set(deck, SetOptions.merge()).await()
        } catch (e:Exception) {
            Log.e("DeckRepository", "Error updating deck", e)
            throw DeckUpdateFailedException(deck)
        }
    }

    /**
     * Delete an existing deck from the repository.
     *
     * @param deckId The ID of the deck to be deleted.
     *
     * **/
    override suspend fun deleteDeck(deckId: String) {
        try {
            firestore.collection("decks").document(deckId).delete().await()
        } catch (e: Exception) {
            Log.e("DeckRepository", "Error deleting deck", e)
            throw DeckDeletionException(deckId)
        }
    }

    override suspend fun addCard(deckId: String, cardId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addCards(deckId: String, cardIds: List<String>) {
        TODO("Not yet implemented")
    }

    override suspend fun removeCard(deckId: String, cardId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeCards(deckId: String, cardIds: List<String>) {
        TODO("Not yet implemented")
    }

}