package me.alhaz.moviecatalog.repositories.tvshows.local.entities

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TVShowEntityDAO {

    // 1. Select All
    @Query("SELECT * FROM tvshow ORDER BY id ASC")
    fun getTVShowList(): DataSource.Factory<Int, TVShowEntity>

    // 2. Select by ID
    @Query("SELECT * FROM tvshow WHERE id = :id")
    fun find(id: Long): TVShowEntity

    // 3. Get People count
    @Query("SELECT COUNT(*) from tvshow")
    fun counts(): Int

    // 4. Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tvshow: TVShowEntity)

    // 5. Delete All
    @Query("DELETE FROM tvshow")
    fun deleteAll()

    // 6. Select All
    @Query("SELECT * FROM tvshow WHERE favorite = '1' ORDER BY id ASC")
    fun getTVShowFavorite(): DataSource.Factory<Int, TVShowEntity>

    // 7. Set Favorite
    @Query("UPDATE tvshow SET favorite = '1' WHERE id = :id")
    fun setFavorite(id: Long)

    // 8 Set Unfavorite
    @Query("UPDATE tvshow SET favorite = '0' WHERE id = :id")
    fun setUnfavorite(id: Long)
}