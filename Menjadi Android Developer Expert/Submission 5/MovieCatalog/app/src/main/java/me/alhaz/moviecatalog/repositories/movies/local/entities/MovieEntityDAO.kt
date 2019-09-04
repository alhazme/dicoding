package me.alhaz.moviecatalog.repositories.movies.local.entities

import android.database.Cursor
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieEntityDAO {

    // 1. Select All
    @Query("SELECT * FROM movie ORDER BY id ASC")
    fun getMovieList(): DataSource.Factory<Int, MovieEntity>

    // 2. Select by ID
    @Query("SELECT * FROM movie WHERE id = :id")
    fun find(id: Long): MovieEntity

    // 3. Get People count
    @Query("SELECT COUNT(*) from movie")
    fun counts(): Int

    // 4. Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity)

    // 5. Delete
    @Query("DELETE FROM movie WHERE id = :id")
    fun delete(id: Long)

    // 6. Delete All
    @Query("DELETE FROM movie")
    fun deleteAll()

    // 7. Select All
    @Query("SELECT * FROM movie WHERE favorite = '1' ORDER BY id ASC")
    fun getMovieFavorites(): DataSource.Factory<Int, MovieEntity>

    // 8. Set Favorite
    @Query("UPDATE movie SET favorite = '1' WHERE id = :id")
    fun setFavorite(id: Long)

    // 9 Set Unfavorite
    @Query("UPDATE movie SET favorite = '0' WHERE id = :id")
    fun setUnfavorite(id: Long)

    // 10 Search Movie
    @Query("SELECT * FROM movie WHERE title = :title ORDER BY id ASC")
    fun searchMovie(title: String): DataSource.Factory<Int, MovieEntity>

    // 11. Select All Favorite images
    @Query("SELECT * FROM movie WHERE favorite = '1' ORDER BY id ASC")
    fun getMoviePosterFavorites(): List<MovieEntity>

    // 12. Select All Favorite Movie for Content Provider
    @Query("SELECT * FROM movie WHERE favorite = '1' ORDER BY id ASC")
    fun getMovieFavoriteForContentProvider(): Cursor

    // 13. Select Detail Favorite Movie for Content Provider
    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieFavoriteDetailForContentProvider(id: Long): Cursor

}