package com.isaac_dolphin.anki.domain.repository

import android.util.Log
import com.isaac_dolphin.anki.domain.interfaces.ICategoryManager
import com.isaac_dolphin.anki.domain.models.Category
import com.isaac_dolphin.anki.utility.exceptions.CategoryNotFoundException
import java.util.UUID

/**
 * Category for storing Decks of Cards.
 *
 * This class holds the CRUD operations for Categories using the "Repository" Design Pattern.
 * @param categories A mutable list of categories.
 *
 * **/
class CategoryRepository(
    private val categories: MutableList<Category> = mutableListOf()
): ICategoryManager {
    /**
     * Retrieves a category from the list of categories by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The category with the specified ID, or null if not found.
     *
     * **/
    override fun getCategory(categoryId: UUID): Category? {
        try {
            return categories.find { it.id == categoryId }
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error getting category with ID $categoryId", e)
            return null
        }
    }

    /**
     * Retrieves all categories from the list of categories.
     *
     * @return Categories as a list. If no categories exist, returns an empty list.
     *
     * **/
    override fun getCategories(): List<Category?> {
        try {
            return categories.toList()
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error getting all categories", e)
            return emptyList()
        }
    }

    /**
     * Creates a new category.
     *
     * @param category The category to create.
     *
     * **/
    override fun createCategory(category: Category) {
        try {
            categories.add(category)
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error creating category: ${category.id}", e)
            throw e
        }
    }

    /**
     * Edit an existing category in the repository.
     *
     * @param categoryId The ID of the category to be updated.
     * @param categoryTitle The new title for the category.
     * @return The updated category.
     *
     * **/
    override fun editCategory(categoryId: UUID, categoryTitle: String) {
        val index = categories.indexOfFirst { it.id == categoryId }
        if (index == -1) {
            throw CategoryNotFoundException(categoryId)
        }
        try {
            categories[index].title = categoryTitle
            Log.d("CategoryRepository", "Category edited successfully: $categoryId")
        } catch(e: Exception) {
            Log.e("CategoryRepository", "Error editing category: $categoryId", e)
            throw e
        }
    }

    /**
     * Delete an existing category from the repository.
     *
     * @param categoryId The ID of the category to be deleted.
     * @return The deleted category.
     *
     * **/
    override fun deleteCategory(categoryId: UUID) {
        val categoryIndex = categories.indexOfFirst { it.id == categoryId }
        if (categoryIndex == -1) {
            throw CategoryNotFoundException(categoryId)
        }
        try {
            categories.removeAt(categoryIndex)
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error deleting category: $categoryId", e)
            throw e
        }
    }

}