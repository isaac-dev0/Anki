package com.isaac_dolphin.anki.domain.interfaces

import com.isaac_dolphin.anki.domain.models.Category
import java.util.UUID

interface ICategoryManager {
    fun getCategory(categoryId: UUID): Category?
    fun getCategories(): List<Category?>
    fun createCategory(category: Category)
    fun editCategory(categoryId: UUID, categoryTitle: String) // TODO: use updateCategory class instead.
    fun deleteCategory(categoryId: UUID)
    // fun addDeck(deckId: UUID)
    // fun addDecks(deckIds: List<UUID>)
    // fun removeDeck(deckId: UUID)
    // fun removeDecks(deckIds: List<UUID>)
}