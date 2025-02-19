package com.isaac_dolphin.anki.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.isaac_dolphin.anki.domain.interfaces.IUserManager
import com.isaac_dolphin.anki.data.models.User
import com.isaac_dolphin.anki.domain.utility.exceptions.CategoriesInUserNotFoundException
import com.isaac_dolphin.anki.domain.utility.exceptions.CategoryAddToUserFailedException
import com.isaac_dolphin.anki.domain.utility.exceptions.CategoryRemoveFromUserFailedException
import com.isaac_dolphin.anki.domain.utility.exceptions.UserCreationFailedException
import com.isaac_dolphin.anki.domain.utility.exceptions.UserDeletionException
import com.isaac_dolphin.anki.domain.utility.exceptions.UserNotFoundException
import com.isaac_dolphin.anki.domain.utility.exceptions.UserUpdateFailedException
import kotlinx.coroutines.tasks.await

/**
 * User for account management and authentication.
 *
 * This class holds the CRUD operations for users using the "Repository" design pattern.
 * @param firestore Firestore connector for UserRepository.
 *
 * **/
class UserRepository(
    private val firestore: FirebaseFirestore
): IUserManager {
    /**
     * Retrieves a user from the list of users by its ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user with the specified ID, or null if not found.
     *
     * **/
    override suspend fun getUser(userId: String): User {
        return try {
            val document = firestore
                .collection("users").document(userId).get().await()
            document.toObject(User::class.java) ?: throw UserNotFoundException(userId)
        } catch (e: Exception) {
            Log.e("UserRepository", "Error getting user", e)
            throw UserNotFoundException(userId)
        }
    }

    /**
     * Retrieves all users from the list of users.
     *
     * @return Users as an object. If no users exist, return an empty object.
     *
     * **/
    override suspend fun getUsers(): List<User> {
        return try {
            val query = firestore
                .collection("users").get().await()
            query.documents.mapNotNull { it.toObject(User::class.java) }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error getting users", e)
            throw CategoriesInUserNotFoundException()
        }
    }

    /**
     * Creates a new user.
     *
     * @param user The user to create.
     *
     * **/
    override suspend fun createUser(user: User) {
        try {
            firestore
                .collection("users").document(user.id)
                .set(user).await()
        } catch (e: Exception) {
            Log.e("UserRepository", "Error creating user", e)
            throw UserCreationFailedException(user)
        }
    }

    /**
     * Edit an existing user in the repository.
     *
     * @param user New user information to overwrite the current.
     *
     * **/
    override suspend fun updateUser(user: User) {
        try {
            val document = firestore
                .collection("users").document(user.id)
            document.set(user, SetOptions.merge()).await()
        } catch (e: Exception) {
            Log.e("UserRepository", "Error updating user", e)
            throw UserUpdateFailedException(user)
        }
    }

    /**
     * Delete an existing user from the repository.
     *
     * @param userId The ID of the user to be deleted.
     *
     * **/
    override suspend fun deleteUser(userId: String) {
        try {
            firestore.collection("users").document(userId).delete().await()
        } catch (e: Exception) {
            Log.e("UserRepository", "Error deleting user", e)
            throw UserDeletionException(userId)
        }
    }

    /**
     * Add a category to a specified user.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category to be added to the user.
     *
     * **/
    override suspend fun addCategory(userId: String, categoryId: String) {
        try {
            firestore
                .collection("users").document(userId)
                .collection("categories").document(categoryId)
                .set(categoryId).await()
        } catch (e: Exception) {
            Log.e("UserRepository", "Error adding category to user", e)
            throw CategoryAddToUserFailedException(userId, categoryId)
        }
    }

    /**
     * Remove a card from a specified deck.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category to be removed from the user.
     *
     * **/
    override suspend fun removeCategory(userId: String, categoryId: String) {
        try {
            firestore
                .collection("users").document(userId)
                .collection("categories").document(categoryId)
                .delete().await()
        } catch (e: Exception) {
            Log.e("UserRepository", "Error removing category from user", e)
            throw CategoryRemoveFromUserFailedException(userId, categoryId)
        }
    }
}