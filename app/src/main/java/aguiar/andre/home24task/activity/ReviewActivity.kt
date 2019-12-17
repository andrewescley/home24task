package aguiar.andre.home24task.activity

import aguiar.andre.home24task.ArticlesService
import aguiar.andre.home24task.R
import aguiar.andre.home24task.dataclass.Articles
import aguiar.andre.home24task.dataclass.HomeApiResponse
import aguiar.andre.home24task.persistence.ArticleFavorite
import aguiar.andre.home24task.persistence.ArticleFavoriteService
import android.arch.lifecycle.LiveData
import android.os.Bundle
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReviewActivity : BaseActivity() {

    //var listArticle: ArrayList<Articles> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.title = "Review Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        getEmbedded()
    }

    fun getEmbedded() {
        var listArticle: ArrayList<Articles> = ArrayList<Articles>()
        val retrofit = Retrofit.Builder()
            .baseUrl(SelectionActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ArticlesService::class.java)
        val call = service.getEmbedded(
            SelectionActivity.appDomain,
            SelectionActivity.locale,
            SelectionActivity.limit
        )
        call.enqueue(object : Callback<HomeApiResponse> {
            override fun onResponse(
                call: Call<HomeApiResponse>,
                response: Response<HomeApiResponse>
            ) {
                //TODO: implement later
                if (response.code() == 200) {
                    val homeApiResponse: HomeApiResponse = response.body()!!

                    listArticle = homeApiResponse._embedded!!.articles
                    checkFavorite(listArticle)
                }
            }

            override fun onFailure(call: Call<HomeApiResponse>, t: Throwable) {

            }
        })

    }

    fun checkFavorite(listArticles: ArrayList<Articles>){

        var texto=""
        doAsync {
            val newListArticles:List<ArticleFavorite> = ArticleFavoriteService.getArticles()
            for (article in listArticles){
                var i: Int = 0
                for (i in newListArticles.indices) {
                    if (article.sku == newListArticles[i].sku){
                        if (newListArticles[i].flagFavorite == "1"){
                            texto = "like"
                        }
                        else{
                            texto = "dlike"
                        }
                    }
                }
            }

            uiThread {

            }
        }
    }
}
