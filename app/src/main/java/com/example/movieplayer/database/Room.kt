package com.example.movieplayer.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoviesDao {

    @Query("select * from movie")
    fun getMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("delete from movie")
    fun deleteAll()
}

@Database(entities = [Movie::class], version = 2)
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
