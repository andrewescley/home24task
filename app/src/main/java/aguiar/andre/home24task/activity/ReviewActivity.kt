package aguiar.andre.home24task.activity

import aguiar.andre.home24task.R
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ReviewActivity : AppCompatActivity() {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.title = "Review Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

    }
}
