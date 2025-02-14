package com.isaac_dolphin.anki.utility.exceptions

import java.util.UUID

class CategoryNotFoundException(
    categoryUUID: UUID
): Exception("Category with UUID $categoryUUID not found.")