package aguiar.andre.home24task.activity

import aguiar.andre.home24task.R
import android.content.Intent
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.title = "Start Screen"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val btStart = findViewById<Button>(R.id.btStart)
        btStart.setOnClickListener { onClickStart()}
    }

    private fun onClickStart(){
        val intent = Intent(context, SelectionActivity::class.java)
        startActivity(intent)
    }
}
