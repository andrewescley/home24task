package aguiar.andre.home24task.activity

import aguiar.andre.home24task.R
import aguiar.andre.home24task.ArticlesService
import aguiar.andre.home24task.dataclass.Articles
import aguiar.andre.home24task.dataclass.HomeApiResponse
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.view.MenuItem
import android.widget.*
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("UNREACHABLE_CODE")
class SelectionActivity : AppCompatActivity() {

    private var articlesData: TextView? = null
    private val context: Context get() = this
    var listArticle: ArrayList<Articles> = ArrayList()
    var intImage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        supportActionBar?.title = "Selection Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        articlesData = findViewById(R.id.textCount)

        val btReview = findViewById<FloatingActionButton>(R.id.btFloatReview)
        btReview.setOnClickListener { onClickReview() }
        btReview.hide()

        val btLike = findViewById<FloatingActionButton>(R.id.btFloatLike)
        btLike.setOnClickListener { onClickLike() }

        val btDislike = findViewById<FloatingActionButton>(R.id.btFloatDislike)
        btDislike.setOnClickListener { onClickDislike() }

        getEmbedded()
    }

    private fun onClickLike() {
        intImage++
        if (intImage < listArticle.size) {
            showImage(listArticle[intImage])
        } else{
            val btReview = findViewById<FloatingActionButton>(R.id.btFloatReview)
            val btLike = findViewById<FloatingActionButton>(R.id.btFloatLike)
            val btDislike = findViewById<FloatingActionButton>(R.id.btFloatDislike)

            btReview.show()
            btLike.hide()
            btDislike.hide()
            toast("You checked all articles.")
        }
    }

    private fun onClickDislike() {
        intImage++
        if (intImage < listArticle.size) {
            showImage(listArticle[intImage])
        } else{
            val btReview = findViewById<FloatingActionButton>(R.id.btFloatReview)
            val btLike = findViewById<FloatingActionButton>(R.id.btFloatLike)
            val btDislike = findViewById<FloatingActionButton>(R.id.btFloatDislike)

            btReview.show()
            btLike.hide()
            btDislike.hide()
            toast("You checked all articles.")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onClickReview(){
        val intent = Intent(context, ReviewActivity::class.java)
        startActivity(intent)
    }

    fun getEmbedded() {
        //var listArticle: ArrayList<Articles> = ArrayList<Articles>()
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ArticlesService::class.java)
        val call = service.getEmbedded(appDomain, locale, limit)
        call.enqueue(object : Callback<HomeApiResponse> {
            override fun onResponse(
                call: Call<HomeApiResponse>,
                response: Response<HomeApiResponse>
            ) {
                //TODO: implement later
                if (response.code() == 200) {
                    val homeApiResponse: HomeApiResponse = response.body()!!

                    listArticle = homeApiResponse._embedded!!.articles
                    showImage(listArticle[intImage])
                }
            }

            override fun onFailure(call: Call<HomeApiResponse>, t: Throwable) {

            }
        })

    }

    fun showImage(articles: Articles) {

        val title: String = articles.title.toString()
        val sku: String = articles.sku.toString()
        val imageView = findViewById<ImageView>(R.id.imageArticle)

        if (imageView != null) {
            Picasso
                .with(context)
                .load(articles.media[0].uri)
                .into(imageView)
        }
        val count = intImage+1
        val stringBuilder = count.toString()+"/"+ listArticle.size.toString()
        articlesData!!.text = stringBuilder
    }

    fun toast(message: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, message, length).show()
    }

    companion object {

        var BaseUrl = "https://api-mobile.home24.com/api/"
        var appDomain = "1"
        var locale = "de_DE"
        var limit = "10"
    }
}
