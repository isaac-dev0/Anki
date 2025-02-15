package com.isaac_dolphin.anki.utility.exceptions

import com.isaac_dolphin.anki.domain.models.Deck

class DeckCreationFailedException(deck: Deck, cause: Throwable? = null):
    Exception("Failed to create deck: ${deck.title}", cause)

class DeckDeletionException(deckId: String, cause: Throwable? = null):
    Exception("Failed to delete deck: $deckId", cause)

class DeckUpdateFailedException(deck: Deck, cause: Throwable? = null):
    Exception("Failed to update deck: ${deck.title}", cause)

class DeckNotFoundException(deckId: String, cause: Throwable? = null):
    Exception("Deck $deckId not found", cause)

class DecksInCategoryNotFoundException(categoryId: String, cause: Throwable? = null):
    Exception("No decks found in category: $categoryId", cause)