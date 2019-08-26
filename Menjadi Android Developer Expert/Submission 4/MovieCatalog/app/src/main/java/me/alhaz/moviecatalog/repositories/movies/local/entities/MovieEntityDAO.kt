package me.alhaz.moviecatalog.repositories.movies.local.entities

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

    // 5. Delete All
    @Query("DELETE FROM movie")
    fun deleteAll()

    // 6. Select All
    @Query("SELECT * FROM movie WHERE favorite = '1' ORDER BY id ASC")
    fun getMovieFavorites(): DataSource.Factory<Int, MovieEntity>

    // 7. Set Favorite
    @Query("UPDATE movie SET favorite = '1' WHERE id = :id")
    fun setFavorite(id: Long)

    // 8 Set Unfavorite
    @Query("UPDATE movie SET favorite = '0' WHERE id = :id")
    fun setUnfavorite(id: Long)

}