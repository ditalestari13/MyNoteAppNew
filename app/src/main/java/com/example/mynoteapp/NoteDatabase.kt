package com.example.mynoteapp

import android.content.Context
import android.os.strictmode.InstanceCountViolation
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao

    companion object{
        private var INSTANCE: NoteDatabase? = null
        fun getInstance(context: Context):NoteDatabase? {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            NoteDatabase::class.java,
                             "NoteDatabase.db"
                        ).build()
                }
            }
            return INSTANCE
        }
    }
}