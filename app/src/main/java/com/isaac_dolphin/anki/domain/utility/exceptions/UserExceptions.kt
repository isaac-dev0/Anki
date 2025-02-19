package com.isaac_dolphin.anki.domain.utility.exceptions

import com.isaac_dolphin.anki.data.models.User

/**
 * If the user fails to be created.
 *
 * @param user Object describing the properties of the user.
 * @param cause Describes the cause of the exception.
 *
 * **/
class UserCreationFailedException(user: User, cause: Throwable? = null):
    Exception("Failed to create user: ${user.email}", cause)

/**
 * If the user fails to be deleted.
 *
 * @param userId The ID of the user.
 * @param cause Describes the cause of the exception.
 *
 * **/
class UserDeletionException(userId: String, cause: Throwable? = null):
    Exception("Failed to delete user: $userId", cause)

/**
 * If the user fails to be updated.
 *
 * @param user Object describing the properties of the new user.
 * @param cause Describes the cause of the exception.
 *
 * **/
class UserUpdateFailedException(user: User, cause: Throwable? = null):
    Exception("Failed to update user: ${user.email}", cause)

/**
 * If the user cannot be found.
 *
 * @param userId The ID of the user.
 * @param cause Describes the cause of the exception.
 *
 * **/
class UserNotFoundException(userId: String, cause: Throwable? = null):
    Exception("User $userId not found", cause)