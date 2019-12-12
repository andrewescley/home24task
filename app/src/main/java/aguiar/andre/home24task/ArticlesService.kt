package aguiar.andre.home24task

import aguiar.andre.home24task.dataclass.HomeApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesService {

    @GET("V2.0/categories/100/articles?")
    fun getEmbedded(@Query("appDomain") appDomain: String, @Query("locale") locale: String, @Query("limit") limit: String): Call<HomeApiResponse>

  }