package com.zqc.ml.snapchat

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main4.*

class Main4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main4)
        startFaceProcessor2()
        flipcamerabutton2.setOnClickListener {
            val intentToActivity2 = Intent(this, Main3Activity::class.java)
            startActivity(intentToActivity2)
            finish()
        }
        changeFilter2.setOnClickListener {
            val intentToActivity2 = Intent (this,Main6Activity::class.java )
            startActivity(intentToActivity2)
            finish() }
    }



    private fun startFaceProcessor2() {
        // Observe activity lifecycle to start, stop and destroy camera view based on lifecycle events
        lifecycle.addObserver(MainActivityLifecycleObserver(camera_view2))



        // Start the face processing
        val faceProcessor = FaceProcessor2(camera_view2, overlay_view3)
        faceProcessor.startProcessing()
    }
}