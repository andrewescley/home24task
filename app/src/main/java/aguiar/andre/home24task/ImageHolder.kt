package aguiar.andre.home24task

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.iv)

    fun updateWithUrl(url: String) {
        Picasso.with(itemView.context).load(url).into(myImageView)
    }
}