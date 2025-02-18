package dto.file

import kotlinx.serialization.Serializable

@Serializable
data class FileType(
    val fileName: String,
    val isDirectory: Boolean
)
