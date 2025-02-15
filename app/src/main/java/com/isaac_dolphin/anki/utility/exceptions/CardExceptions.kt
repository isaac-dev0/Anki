package com.isaac_dolphin.anki.utility.exceptions

// Customisable exceptions for card-related operations.

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