package com.cogitator.vrview_android

import android.os.Bundle
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import kotlinx.android.synthetic.main.first_activity.*
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.IOException
import java.io.InputStream


/**
 * @author Ankit Kumar on 09/11/2018
 */
class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)

        loadPhotoSphere()
    }

    override fun onPause() {
        panorama_view.pauseRendering()
        super.onPause()
    }

    override fun onResume() {
        panorama_view.resumeRendering()
        super.onResume()
    }

    override fun onDestroy() {
        // Destroy the view and free memory.
        panorama_view.shutdown()
        super.onDestroy()
    }

    private fun loadPhotoSphere() {
        //This could take a while. Should do on a background thread, but fine for current example
        val options = VrPanoramaView.Options()
        val inputStream: InputStream?

        val assetManager = assets

        try {
            inputStream = assetManager.open("openspace.jpg")
            options.inputType = VrPanoramaView.Options.TYPE_MONO
            panorama_view.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options)
            inputStream!!.close()
        } catch (e: IOException) {
            Log.e("Tuts+", "Exception in loadPhotoSphere: " + e.message)
        }

    }
}