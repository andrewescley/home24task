package aguiar.andre.home24task.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "articleFavorite")
class ArticleFavorite {
    @PrimaryKey (autoGenerate = true) var articleFavoriteId: Int =0

    @ColumnInfo (name ="sku") var sku:  String =""

    @ColumnInfo (name ="isFavorite") var isFavorite:  String =""
}