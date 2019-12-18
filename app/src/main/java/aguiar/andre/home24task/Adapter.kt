package aguiar.andre.home24task

import aguiar.andre.home24task.activity.ReviewActivity
import aguiar.andre.home24task.persistence.ArticleFavorite
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class Adapter(ctx: Context, private val articleFavoriteArrayList: ArrayList<ArticleFavorite>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private val inflater: LayoutInflater


    init {

        inflater = LayoutInflater.from(ctx)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.MyViewHolder {

        val view = inflater.inflate(R.layout.rv_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.MyViewHolder, position: Int) {

        if (articleFavoriteArrayList[position].flagFavorite == "1"){
            holder.favorite.setText("You liked this article.")
        } else{
            holder.favorite.setText("")
        }
        holder.title.setText(articleFavoriteArrayList[position].title)

        var imageUrl = articleFavoriteArrayList[position].uri
        holder?.updateWithUrl(imageUrl)
    }

    override fun getItemCount(): Int {
        return articleFavoriteArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.tv) as TextView
        var favorite: TextView = itemView.findViewById(R.id.favorite) as TextView
        //var iv: ImageView = itemView.findViewById(R.id.iv) as ImageView

        private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.iv)

        fun updateWithUrl(url: String) {
            Picasso.with(itemView.context).load(url).into(myImageView)
        }

    }
}