package jp.androidbook.bookmark.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = ([BookEntity::class]), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun BookDao(): BookDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(AppDatabase::class) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "BookEntity.db").build()
        }
    }
}

