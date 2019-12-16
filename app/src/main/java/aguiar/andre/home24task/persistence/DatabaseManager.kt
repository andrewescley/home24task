package aguiar.andre.home24task.persistence

import aguiar.andre.home24task.HomeTaskApplication
import android.arch.persistence.room.Room

object DatabaseManager {
    // Singleton do Room: banco de dados
    private var dbInstance: ArticleFavoriteDatabase

    init {
        val appContext = HomeTaskApplication.getInstance().applicationContext

        // Configura o Room
        dbInstance = Room.databaseBuilder(
                appContext,
                ArticleFavoriteDatabase::class.java,
                "db.sqlite")
                .build()
    }

    fun getArticleFavoriteDAO(): ArticleFavoriteDAO {
        return dbInstance.articleFavoriteDAO()
    }
}