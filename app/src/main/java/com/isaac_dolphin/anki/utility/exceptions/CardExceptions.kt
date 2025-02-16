package com.isaac_dolphin.anki.utility.exceptions

import com.isaac_dolphin.anki.domain.models.Card

// Customisable exceptions for card-related operations.

/**
 * If the card fails to be created.
 *
 * @param card Object describing the properties of the card.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CardCreationFailedException(card: Card, cause: Throwable? = null):
    Exception("Failed to create card: ${card.title}", cause)

/**
 * If the card fails to be deleted.
 *
 * @param cardId The ID of the card.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CardDeletionException(cardId: String, cause: Throwable? = null):
    Exception("Failed to delete card: $cardId", cause)

/**
 * If the card fails to be updated.
 *
 * @param card Object describing the properties of the new card.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CardUpdateFailedException(card: Card, cause: Throwable? = null):
    Exception("Failed to update to card: ${card.title}", cause)

/**
 * If the card cannot be found.
 *
 * @param cardId The ID of the card.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CardNotFoundException(cardId: String, cause: Throwable? = null):
    Exception("Card $cardId not found", cause)

/**
 * If card(s) cannot be found within a deck.
 *
 * @param deckId The ID of the deck.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CardsInDeckNotFoundException(deckId: String, cause: Throwable? = null):
    Exception("No cards found in deck: $deckId", cause)

/**
 * If the card fails to add to a deck.
 *
 * @param deckId The ID of the deck.
 * @param cardId The ID of the card.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CardAddToDeckFailedException(deckId: String, cardId: String, cause: Throwable? = null):
    Exception("Failed to add card: $cardId to deck: $deckId", cause)

/**
 * If the card fails to remove from a deck.
 *
 * @param deckId The ID of the deck.
 * @param cardId The ID of the card.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CardRemoveFromDeckFailedException(deckId: String, cardId: String, cause: Throwable? = null):
    Exception("Failed to remove card: $cardId from deck: $deckId", cause)