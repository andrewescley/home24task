package aguiar.andre.home24task.persistence

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import java.util.concurrent.Executors

@Database(entities = arrayOf(ArticleFavorite::class), version = 1)
abstract class ArticleFavoriteDatabase : RoomDatabase() {
    abstract fun articleFavoriteDAO(): ArticleFavoriteDAO
}
