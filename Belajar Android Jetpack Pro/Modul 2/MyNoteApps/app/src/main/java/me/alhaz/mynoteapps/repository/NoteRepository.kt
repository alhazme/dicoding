package me.alhaz.mynoteapps.repository

import android.app.Application
import androidx.lifecycle.LiveData
import me.alhaz.mynoteapps.database.Note
import me.alhaz.mynoteapps.database.NoteDao
import me.alhaz.mynoteapps.database.NoteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {

    private lateinit var noteDao: NoteDao
    private lateinit var executorService: ExecutorService

    fun NoteRepository(application: Application) {
        executorService = Executors.newSingleThreadExecutor()
        val noteDB = NoteDatabase.getInstance(application)
        noteDao = noteDB.noteDao()
    }

    fun insert(note: Note) {
        executorService.execute {
            noteDao.insert(note)
        }
    }

    fun update(note: Note) {
        executorService.execute {
            noteDao.update(note)
        }
    }

    fun delete(note: Note) {
        executorService.execute {
            noteDao.delete(note)
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }
}