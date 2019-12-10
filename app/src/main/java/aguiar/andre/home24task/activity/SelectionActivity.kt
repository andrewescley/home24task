package aguiar.andre.home24task.activity

import aguiar.andre.home24task.ArticlesService
import aguiar.andre.home24task.R
import aguiar.andre.home24task.dataclass.Articles
import aguiar.andre.home24task.dataclass.ArticlesSku
import aguiar.andre.home24task.dataclass.HomeApiResponse
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_start.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelectionActivity : AppCompatActivity() {

    private var articlesData: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        supportActionBar?.title = "Selection Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        articlesData = findViewById(R.id.textCount)

        getEmbedded()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    internal fun getEmbedded() {
        var listArticle = ArrayList<Articles>()
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ArticlesService::class.java)
        val call = service.getEmbedded(appDomain, locale, limit)
        call.enqueue(object : Callback<HomeApiResponse> {
            override fun onResponse(call: Call<HomeApiResponse>, response: Response<HomeApiResponse>) {
                if (response.code() == 200) {
                    val homeApiResponse = response.body()!!

                    listArticle = homeApiResponse._embedded!!.articles

                    for (article in listArticle) {
                        for(media in article.media) {
                            //val sku = article.sku.toString()
                            //getArticle(sku)
                            //val stringBuilder = media.uri
                            //articlesData!!.text = stringBuilder
                        }
                    }

                    //val stringBuilder = articlesSku.sku!!

                    //articlesData!!.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<HomeApiResponse>, t: Throwable) {

            }
        })

    }

    internal fun getArticle(sku:String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ArticlesService::class.java)
        val callArticle = service.getArticles(sku , appDomain, locale)
        callArticle.enqueue(object : Callback<ArticlesSku> {
            override fun onResponse(callArticle: Call<ArticlesSku>, response: Response<ArticlesSku>) {
                if (response.code() == 200) {
                    val articlesSku = response.body()!!

                    val stringBuilder = articlesSku.sku!!

                    articlesData!!.text = stringBuilder
                }
            }

            override fun onFailure(callArticle: Call<ArticlesSku>, t: Throwable) {

            }
        })
    }

    companion object {

        var BaseUrl = "https://api-mobile.home24.com/api/"
        var appDomain = "1"
        var locale = "de_DE"
        var limit = "1"
    }
}
