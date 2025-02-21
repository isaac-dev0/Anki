package com.isaac_dolphin.anki.domain.interfaces

import com.isaac_dolphin.anki.data.model.User

interface IUserManager {
    suspend fun getUser(userId: String): User
    suspend fun getUsers(): List<User>
    suspend fun createUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(userId: String)
    suspend fun addCategory(userId: String, categoryId: String)
    suspend fun removeCategory(userId: String, categoryId: String)
}