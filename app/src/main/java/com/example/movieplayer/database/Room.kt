package com.example.movieplayer.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoviesDao {

    @Query("select * from databasemovie")
    fun getMovies(): LiveData<List<DatabaseMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<DatabaseMovie>)
}

@Database(entities = [DatabaseMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MoviesDao
}


private lateinit var INSTANCE: MovieDatabase

fun getDatabase(context: Context): MovieDatabase {
    synchronized(MovieDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movies"
            ).build()
        }
    }
    return INSTANCE
}
