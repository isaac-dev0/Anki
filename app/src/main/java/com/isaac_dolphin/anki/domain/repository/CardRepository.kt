package com.isaac_dolphin.anki.domain.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.isaac_dolphin.anki.domain.interfaces.ICardManager
import com.isaac_dolphin.anki.domain.models.Card
import com.isaac_dolphin.anki.utility.exceptions.CardCreationFailedException
import com.isaac_dolphin.anki.utility.exceptions.CardDeletionException
import com.isaac_dolphin.anki.utility.exceptions.CardNotFoundException
import com.isaac_dolphin.anki.utility.exceptions.CardUpdateFailedException
import com.isaac_dolphin.anki.utility.exceptions.CardsInDeckNotFoundException
import kotlinx.coroutines.tasks.await

/**
 * Card for storing information.
 *
 * This class holds the CRUD operations for cards using the "Repository" design pattern.
 * @param firestore Firestore connector for CardRepository.
 *
 * **/
class CardRepository(
    private val firestore: FirebaseFirestore
): ICardManager {
    /**
     * Retrieves a card from the list of cards by its ID.
     *
     * @param cardId The ID of the card to retrieve.
     * @return The card with the specified ID, or null if not found.
     *
     * **/
    override suspend fun getCard(cardId: String): Card {
        return try {
            val document = firestore
                .collection("cards").document(cardId).get().await()
            document.toObject(Card::class.java) ?: throw CardNotFoundException(cardId)
        } catch (e: Exception) {
            Log.e("CardRepository", "Error getting card", e)
            throw CardNotFoundException(cardId)
        }
    }

    /**
     * Retrieves all decks from the list of decks.
     *
     * @param deckId The ID of the deck from which cards will be retrieved.
     * @return Cards as an object. If no cards exist, return an empty object.
     *
     * **/
    override suspend fun getCards(deckId: String): List<Card> {
        return try {
            val query = firestore
                .collection("decks").document(deckId)
                .collection("cards").get().await()
            query.documents.mapNotNull { it.toObject(Card::class.java) }
        } catch (e: Exception) {
            Log.e("CardRepository", "Error getting cards", e)
            throw CardsInDeckNotFoundException(deckId)
        }
    }

    /**
     * Creates a new card within a specified deck.
     *
     * @param card The card to create.
     *
     * **/
    override suspend fun createCard(card: Card) {
        try {
            firestore
                .collection("decks").document(card.deckId)
                .collection("cards").document(card.id).set(card).await()
        } catch (e: Exception) {
            Log.e("CardRepository", "Error creating card", e)
            throw CardCreationFailedException(card)
        }
    }

    /**
     * Edit an existing card in the repository.
     *
     * @param card New card information to overwrite the current.
     *
     * **/
    override suspend fun updateCard(card: Card) {
        try {
            val document = firestore
                .collection("decks").document(card.deckId)
                .collection("cards").document(card.id)
            document.set(card, SetOptions.merge()).await()
        } catch (e: Exception) {
            Log.e("CardRepository", "Error updating card", e)
            throw CardUpdateFailedException(card)
        }
    }

    /**
     * Delete an existing card from the repository.
     *
     * @param cardId The ID of the card to be deleted.
     *
     * **/
    override suspend fun deleteCard(cardId: String) {
        try {
            firestore.collection("cards").document(cardId).delete().await()
        } catch (e: Exception) {
            Log.e("CardRepository", "Error deleting card", e)
            throw CardDeletionException(cardId)
        }
    }
}