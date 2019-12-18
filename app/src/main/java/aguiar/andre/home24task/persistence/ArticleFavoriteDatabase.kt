package aguiar.andre.home24task.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(ArticleFavorite::class), version = 3)
abstract class ArticleFavoriteDatabase : RoomDatabase() {
    abstract fun articleFavoriteDAO(): ArticleFavoriteDAO
}
