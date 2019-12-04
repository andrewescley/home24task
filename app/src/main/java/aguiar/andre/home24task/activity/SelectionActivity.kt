package aguiar.andre.home24task.activity

import aguiar.andre.home24task.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class SelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        supportActionBar?.title = "Selection Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
