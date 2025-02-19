package com.isaac_dolphin.anki.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.isaac_dolphin.anki.data.repository.CardRepository
import com.isaac_dolphin.anki.data.repository.CategoryRepository
import com.isaac_dolphin.anki.data.repository.DeckRepository
import com.isaac_dolphin.anki.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideCardRepository(firestore: FirebaseFirestore): CardRepository = CardRepository(firestore)

    @Provides
    fun provideDeckRepository(firestore: FirebaseFirestore): DeckRepository = DeckRepository(firestore)

    @Provides
    fun provideCategoryRepository(firestore: FirebaseFirestore): CategoryRepository = CategoryRepository(firestore)

    @Provides
    fun provideUserRepository(firestore: FirebaseFirestore): UserRepository = UserRepository(firestore)
}