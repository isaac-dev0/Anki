package com.isaac_dolphin.anki.domain.utility.exceptions

import com.isaac_dolphin.anki.data.models.Category

// Customisable exceptions for category-related operations.

/**
 * If the category fails to be created.
 *
 * @param category Object describing the properties of the category.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CategoryCreationFailedException(category: Category, cause: Throwable? = null):
    Exception("Failed to create category: ${category.title}", cause)

/**
 * If the category fails to be deleted.
 *
 * @param categoryId The ID of the category.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CategoryDeletionException(categoryId: String, cause: Throwable? = null):
    Exception("Failed to delete category: $categoryId", cause)

/**
 * If the category fails to be updated.
 *
 * @param category Object describing the properties of the new category.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CategoryUpdateFailedException(category: Category, cause: Throwable? = null):
    Exception("Failed to update to category: ${category.title}", cause)

/**
 * If the category cannot be found.
 *
 * @param categoryId The ID of the category.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CategoryNotFoundException(categoryId: String, cause: Throwable? = null):
    Exception("Category $categoryId not found", cause)

/**
 * If category(ies) cannot be found within a user.
 *
 * @param cause Describes the cause of the exception.
 *
 * **/
class CategoriesInUserNotFoundException(cause: Throwable? = null):
    Exception("No categories found in user", cause)

/**
 * If the category fails to add to a user.
 *
 * @param userId The ID of the user.
 * @param categoryId The ID of the category.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CategoryAddToUserFailedException(userId: String, categoryId: String, cause: Throwable? = null):
    Exception("Failed to add category: $categoryId to user: $userId", cause)

/**
 * If the category fails to remove from a user.
 *
 * @param userId The ID of the user.
 * @param categoryId The ID of the category.
 * @param cause Describes the cause of the exception.
 *
 * **/
class CategoryRemoveFromUserFailedException(userId: String, categoryId: String, cause: Throwable? = null):
    Exception("Failed to remove category: $categoryId from user: $userId", cause)