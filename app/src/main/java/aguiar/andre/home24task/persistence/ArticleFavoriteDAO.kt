package aguiar.andre.home24task.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ArticleFavoriteDAO {
    @Query(
        "SELECT * from articleFavorite ORDER BY articleFavoriteId ASC"
    )
    fun findAll(): List<ArticleFavorite>

    @Query("SELECT * FROM articleFavorite where sku = :sku")
    fun getBySku(sku: String): ArticleFavorite?

    @Insert
    fun insert(articleFavorite: ArticleFavorite)

    @Query("DELETE FROM articleFavorite")
    fun deleteAll()
}

