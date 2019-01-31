package com.zqc.ml.snapchat

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.otaliastudios.cameraview.CameraView
import kotlinx.android.synthetic.main.activity_main6.*
import kotlinx.android.synthetic.main.activity_main6.view.*
class Main6Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main6)
        startFaceProcessor3()
        flipcamerabutton2.setOnClickListener {
            val intentToActivity2 = Intent(this, Main5Activity::class.java)
            startActivity(intentToActivity2)
            finish()
        }
        changeFilter2.setOnClickListener {
            val intentToActivity2 = Intent(this, Main2Activity::class.java)
            startActivity(intentToActivity2)
            finish() }
    }



    private fun startFaceProcessor3() {
        // Observe activity lifecycle to start, stop and destroy camera view based on lifecycle events
        lifecycle.addObserver(MainActivityLifecycleObserver(camera_view2))



        // Start the face processing
        val faceProcessor = FaceProcessor3(camera_view2, overlay_view5)
        faceProcessor.startProcessing()
    }
}