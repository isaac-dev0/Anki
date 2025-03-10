package com.isaac_dolphin.anki.domain.utility.exceptions

import com.isaac_dolphin.anki.data.model.Deck

// Customisable exceptions for deck-related operations.

/**
 * If the deck fails to be created.
 *
 * @param deck Object describing the properties of the deck.
 * @param cause Describes the cause of the exception.
 *
 * **/
class DeckCreationFailedException(deck: Deck, cause: Throwable? = null):
    Exception("Failed to create deck: ${deck.title}", cause)

/**
 * If the deck fails to be deleted.
 *
 * @param deckId The ID of the deck.
 * @param cause Describes the cause of the exception.
 *
 * **/
class DeckDeletionException(deckId: String, cause: Throwable? = null):
    Exception("Failed to delete deck: $deckId", cause)

/**
 * If the deck fails to be updated.
 *
 * @param deck Object describing the properties of the new deck.
 * @param cause Describes the cause of the exception.
 *
 * **/
class DeckUpdateFailedException(deck: Deck, cause: Throwable? = null):
    Exception("Failed to update to deck: ${deck.title}", cause)

/**
 * If the deck cannot be found.
 *
 * @param deckId The ID of the deck.
 * @param cause Describes the cause of the exception.
 *
 * **/
class DeckNotFoundException(deckId: String, cause: Throwable? = null):
    Exception("Deck $deckId not found", cause)

/**
 * If deck(s) cannot be found within a category.
 *
 * @param categoryId The ID of the category.
 * @param cause Describes the cause of the exception.
 *
 * **/
class DecksInCategoryNotFoundException(categoryId: String, cause: Throwable? = null):
    Exception("No decks found in category: $categoryId", cause)

/**
 * If the deck fails to add to a category.
 *
 * @param categoryId The ID of the category.
 * @param deckId The ID of the deck.
 * @param cause Describes the cause of the exception.
 *
 * **/
class DeckAddToCategoryFailedException(categoryId: String, deckId: String, cause: Throwable? = null):
    Exception("Failed to add deck: $deckId to category: $categoryId", cause)

/**
 * If the deck fails to remove from a category.
 *
 * @param categoryId The ID of the category.
 * @param deckId The ID of the deck.
 * @param cause Describes the cause of the exception.
 *
 * **/
class DeckRemoveFromCategoryFailedException(categoryId: String, deckId: String, cause: Throwable? = null):
    Exception("Failed to remove deck: $deckId from category: $categoryId", cause)