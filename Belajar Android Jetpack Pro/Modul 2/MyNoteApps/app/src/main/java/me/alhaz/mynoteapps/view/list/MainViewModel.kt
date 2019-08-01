package me.alhaz.mynoteapps.view.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.alhaz.mynoteapps.database.Note
import me.alhaz.mynoteapps.repository.NoteRepository

class MainViewModel: ViewModel() {

    private lateinit var noteRepository: NoteRepository

    fun MainViewModel(application: Application) {
        noteRepository = NoteRepository(application)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return noteRepository.getAllNotes()
    }

}