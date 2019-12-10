package aguiar.andre.home24task.dataclass

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class HomeApiResponse {

    @SerializedName("_embedded")
    var _embedded: _Embedded? = null

}

class _Embedded {
    @SerializedName("articles")
    var articles  = ArrayList<Articles>()
}

class Articles {
    @SerializedName("sku")
    var sku: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("media")
    var media  = ArrayList<Media>()
}

class ArticlesSku {
    @SerializedName("sku")
    var sku: String? = null
    @SerializedName("title")
    var title: Title? = null
    @SerializedName("images")
    var image  = ArrayList<Image>()
}

class Image {
    @SerializedName("url")
    var url: String? = null
}

class Media {
    @SerializedName("uri")
    var uri: String? = null
}

class Title {
    @SerializedName("cleanName")
    var cleanName: String? = null
    @SerializedName("variantName")
    var variantName: String? = null
}