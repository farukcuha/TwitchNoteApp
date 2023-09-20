package com.farukcuha.twitchnoteapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farukcuha.twitchnoteapp.domain.model.Note

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "body") val body: String?,
    @ColumnInfo(name = "time") val time: Long?
) {
    fun toNote(): Note {

        return Note(
            id = id,
            title = title,
            body = body,
            time = time
        )
    }
}