package aguiar.andre.home24task

import android.support.multidex.MultiDexApplication
import android.util.Log
import java.lang.IllegalStateException

/**
 * MultiDexApplication:
 * https://developer.android.com/studio/build/multidex.html?hl=pt-br
 */
class HomeTaskApplication : MultiDexApplication() {
    private val TAG = "HomeTaskApplication"

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: HomeTaskApplication? = null

        fun getInstance(): HomeTaskApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure Application class in AndroidManifest.xml")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "onTerminate()")
    }
}
