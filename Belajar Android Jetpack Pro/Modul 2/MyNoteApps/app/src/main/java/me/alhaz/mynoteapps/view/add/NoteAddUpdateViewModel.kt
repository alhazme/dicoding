package me.alhaz.mynoteapps.view.add

import android.app.Application
import androidx.lifecycle.ViewModel
import me.alhaz.mynoteapps.database.Note
import me.alhaz.mynoteapps.repository.NoteRepository

class NoteAddUpdateViewModel: ViewModel() {

    private lateinit var noteRepository: NoteRepository

    fun NoteAddUpdateViewModel(application: Application) {
        noteRepository = NoteRepository(application)
    }

    fun insert(note: Note) {
        noteRepository.insert(note)
    }

    fun update(note: Note) {
        noteRepository.update(note)
    }

    fun delete(note: Note) {
        noteRepository.delete(note)
    }

}