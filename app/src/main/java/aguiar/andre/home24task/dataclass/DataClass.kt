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

class Media {
    @SerializedName("uri")
    var uri: String? = null
}

