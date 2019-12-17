package aguiar.andre.home24task.activity

import aguiar.andre.home24task.R
import aguiar.andre.home24task.persistence.ArticleFavoriteService
import android.content.Intent
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.title = "Start Screen"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val btStart = findViewById<Button>(R.id.btStart)
        btStart.setOnClickListener { onClickStart()}
    }

    private fun onClickStart(){
        doAsync {
            val delete = ArticleFavoriteService.delete()
            uiThread {
            }
        }
        val intent = Intent(context, SelectionActivity::class.java)
        startActivity(intent)
    }
}
