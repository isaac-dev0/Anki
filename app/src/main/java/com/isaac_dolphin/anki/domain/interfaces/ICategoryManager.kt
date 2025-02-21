package com.isaac_dolphin.anki.domain.interfaces

import com.isaac_dolphin.anki.data.model.Category

interface ICategoryManager {
    suspend fun getCategory(categoryId: String): Category
    suspend fun getCategories(userId: String): List<Category>
    suspend fun createCategory(category: Category)
    suspend fun updateCategory(category: Category)
    suspend fun deleteCategory(categoryId: String)
    suspend fun addDeck(categoryId: String, deckId: String)
    suspend fun removeDeck(categoryId: String, deckId: String)
}