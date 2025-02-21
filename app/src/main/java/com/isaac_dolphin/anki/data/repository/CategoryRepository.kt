package com.isaac_dolphin.anki.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.isaac_dolphin.anki.domain.interfaces.ICategoryManager
import com.isaac_dolphin.anki.data.model.Category
import com.isaac_dolphin.anki.domain.utility.exceptions.CategoriesInUserNotFoundException
import com.isaac_dolphin.anki.domain.utility.exceptions.CategoryCreationFailedException
import com.isaac_dolphin.anki.domain.utility.exceptions.CategoryDeletionException
import com.isaac_dolphin.anki.domain.utility.exceptions.CategoryNotFoundException
import com.isaac_dolphin.anki.domain.utility.exceptions.CategoryUpdateFailedException
import com.isaac_dolphin.anki.domain.utility.exceptions.DeckAddToCategoryFailedException
import com.isaac_dolphin.anki.domain.utility.exceptions.DeckRemoveFromCategoryFailedException
import kotlinx.coroutines.tasks.await

/**
 * Category for storing decks.
 *
 * This class holds the CRUD operations for categories using the "Repository" design pattern.
 * @param firestore Firestore connector for CategoryRepository.
 *
 * **/
class CategoryRepository(
    private val firestore: FirebaseFirestore
): ICategoryManager {
    /**
     * Retrieves a category from the list of categories by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The category with the specified ID, or null if not found.
     *
     * **/
    override suspend fun getCategory(categoryId: String): Category {
        return try {
            val document = firestore
                .collection("categories").document(categoryId).get().await()
            document.toObject(Category::class.java) ?: throw CategoryNotFoundException(categoryId)
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error getting category", e)
            throw CategoryNotFoundException(categoryId)
        }
    }

    /**
     * Retrieves all categories from the list of categories.
     *
     * @param userId The ID of the user from which categories will be retrieved.
     * @return categories as an object. If no categories exist, return an empty object.
     *
     * **/
    override suspend fun getCategories(userId: String): List<Category> {
        return try {
            val query = firestore
                .collection("users").document(userId)
                .collection("categories").get().await()
            query.documents.mapNotNull { it.toObject(Category::class.java) }
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error getting categories", e)
            throw CategoriesInUserNotFoundException()
        }
    }

    /**
     * Creates a new category under a specified user.
     *
     * @param category The category to create.
     *
     * **/
    override suspend fun createCategory(category: Category) {
        try {
            firestore
                .collection("users").document(category.userId)
                .collection("categories").document(category.id).set(category).await()
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error creating category", e)
            throw CategoryCreationFailedException(category)
        }
    }

    /**
     * Edit an existing category in the repository.
     *
     * @param category New category information to overwrite the current.
     *
     * **/
    override suspend fun updateCategory(category: Category) {
        try {
            val document = firestore
                .collection("users").document(category.userId)
                .collection("categories").document(category.id)
            document.set(category, SetOptions.merge()).await()
        } catch (e:Exception) {
            Log.e("CategoryRepository", "Error updating category", e)
            throw CategoryUpdateFailedException(category)
        }
    }

    /**
     * Delete an existing category from the repository.
     *
     * @param categoryId The ID of the category to be deleted.
     *
     * **/
    override suspend fun deleteCategory(categoryId: String) {
        try {
            firestore.collection("categories").document(categoryId).delete().await()
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error deleting category", e)
            throw CategoryDeletionException(categoryId)
        }
    }

    /**
     * Add a deck to a specified category.
     *
     * @param categoryId The ID of the category.
     * @param deckId The ID of the deck to be added to the category.
     *
     * **/
    override suspend fun addDeck(categoryId: String, deckId: String) {
        try {
            firestore
                .collection("categories").document(categoryId)
                .collection("decks").document(deckId)
                .set(deckId).await()
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error adding deck to category", e)
            throw DeckAddToCategoryFailedException(categoryId, deckId)
        }
    }

    /**
     * Remove a deck from a specified category.
     *
     * @param categoryId The ID of the category.
     * @param deckId The ID of the deck to be removed from the category.
     *
     * **/
    override suspend fun removeDeck(categoryId: String, deckId: String) {
        try {
            firestore
                .collection("categories").document(categoryId)
                .collection("decks").document(deckId)
                .delete().await()
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error removing deck from category", e)
            throw DeckRemoveFromCategoryFailedException(categoryId, deckId)
        }
    }
}