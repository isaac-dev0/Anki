package com.isaac_dolphin.anki.domain.models

// Separate class to pass into editCategory of data that can be modified.
data class UpdateCategory(
    var title: String? = null
)
