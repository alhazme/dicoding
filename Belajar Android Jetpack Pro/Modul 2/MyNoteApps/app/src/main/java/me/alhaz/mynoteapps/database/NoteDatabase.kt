package me.alhaz.mynoteapps.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase() : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        private var INSTANCE : NoteDatabase? = null

        fun getInstance(context: Context) : NoteDatabase? {
            synchronized(NoteDatabase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note.db").build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}