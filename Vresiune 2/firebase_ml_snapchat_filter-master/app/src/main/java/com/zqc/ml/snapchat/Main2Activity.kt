package com.zqc.ml.snapchat

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.otaliastudios.cameraview.CameraView
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.view.*


class Main2Activity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    /**
     * The constant permission request code
     */
    private val PERMISION_REQUEST_CODE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main2)
        checkAndRequestCameraPermission()


        flipcamerabutton2.setOnClickListener {
            val intentToActivity2 = Intent (this,MainActivity::class.java )
            startActivity(intentToActivity2)
            finish() }

        changeFilter2.setOnClickListener {
            val intentToActivity2 = Intent (this,Main4Activity::class.java )
            startActivity(intentToActivity2)
            finish() }
    }

    /**
     * Request for camera permission if it is needed
     */
    private fun checkAndRequestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
                    PERMISION_REQUEST_CODE)
        } else {
            startFaceProcessor()
        }
    }

    /**
     * Start the face processor
     */




    private fun startFaceProcessor() {
        // Observe activity lifecycle to start, stop and destroy camera view based on lifecycle events
        lifecycle.addObserver(MainActivityLifecycleObserver(camera_view2))



        // Start the face processing
        val faceProcessor = FaceProcessor(camera_view2, overlay_view)
        faceProcessor.startProcessing()
    }



    /**
     * Handle the request permission result here
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISION_REQUEST_CODE) {
            if (android.Manifest.permission.CAMERA == permissions[0] &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startFaceProcessor()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
