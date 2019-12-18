package aguiar.andre.home24task.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "articleFavorite")
class ArticleFavorite() : Parcelable {
    @PrimaryKey (autoGenerate = true) var articleFavoriteId: Int =0

    @ColumnInfo (name ="sku") var sku:  String =""

    @ColumnInfo (name ="title") var title:  String =""

    @ColumnInfo (name ="uri") var uri:  String =""

    @ColumnInfo (name ="flagFavorite") var flagFavorite:  String =""

    constructor(parcel: Parcel) : this() {
        articleFavoriteId = parcel.readInt()
        sku = parcel.readString()
        flagFavorite = parcel.readString()
        title = parcel.readString()
        uri = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(articleFavoriteId)
        parcel.writeString(sku)
        parcel.writeString(flagFavorite)
        parcel.writeString(title)
        parcel.writeString(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleFavorite> {
        override fun createFromParcel(parcel: Parcel): ArticleFavorite {
            return ArticleFavorite(parcel)
        }

        override fun newArray(size: Int): Array<ArticleFavorite?> {
            return arrayOfNulls(size)
        }
    }
}