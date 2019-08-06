package me.alhaz.mynoteapps.database

import androidx.lifecycle.LiveData
import androidx.room.*

interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FORM note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}