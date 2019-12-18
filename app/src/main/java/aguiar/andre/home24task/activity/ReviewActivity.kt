package aguiar.andre.home24task.activity

import aguiar.andre.home24task.Adapter
import aguiar.andre.home24task.R
import aguiar.andre.home24task.persistence.ArticleFavorite
import aguiar.andre.home24task.persistence.ArticleFavoriteService
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ReviewActivity : BaseActivity() {

    var recyclerView: RecyclerView? = null
    var listArticleFavorite: ArrayList<ArticleFavorite> = ArrayList()
    var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.title = "Review Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        recyclerView = findViewById(R.id.recycler) as RecyclerView

        listArticleFavorite = populateList()

        adapter = Adapter(this, listArticleFavorite!!)
        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        recyclerView!!.addOnItemTouchListener(RecyclerTouchListener(applicationContext, recyclerView!!, object : ClickListener {

            override fun onClick(view: View, position: Int) {
                Toast.makeText(this@ReviewActivity, listArticleFavorite!![position].title, Toast.LENGTH_LONG).show()
            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))
    }

    private fun populateList(): ArrayList<ArticleFavorite> {

        var listArticleFavoriteReturn = ArrayList<ArticleFavorite>()
        doAsync {
            val listArticleFavorite = ArticleFavoriteService.getArticles()
            for (i in listArticleFavorite){
                val articleFavorite = ArticleFavorite()
                articleFavorite.sku = i.sku
                articleFavorite.title = i.title
                articleFavorite.uri = i.uri
                articleFavorite.flagFavorite = i.flagFavorite
                listArticleFavoriteReturn.add(articleFavorite)
            }
            uiThread {

            }
        }
        return listArticleFavoriteReturn

    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(context: Context, recyclerView: RecyclerView, private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }
}
