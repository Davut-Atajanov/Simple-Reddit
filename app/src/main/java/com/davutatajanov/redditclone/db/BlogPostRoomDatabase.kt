package com.davutatajanov.redditclone.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.davutatajanov.redditclone.util.Constants

/*
If you change anything on the database like adding a field to table, chnaging type of a filed, deleting a filed, changing the name of the field
exportSchema: to have a version of history of your schema in your caode base, it is not required so assigned as false
 */
@Database(
    entities = [BlogPost::class],
    version = 2,
    exportSchema = false
)
abstract class BlogPostRoomDatabase : RoomDatabase() {
    abstract fun BlogPostDao(): BlogPostDAO

    companion object {
        @Volatile  // Makes the instance visible to other threads
        private var INSTANCE: BlogPostRoomDatabase? = null

        fun getDatabase(context: Context): BlogPostRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            // Everything in this block is protected from concurrent execution by multiple threads.
            // The same database instance will be used to avoid creating many instances, which would be expensive.
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BlogPostRoomDatabase::class.java,
                    Constants.DATABASENAME
                )
                    .fallbackToDestructiveMigration()  // Enables destructive migration
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}