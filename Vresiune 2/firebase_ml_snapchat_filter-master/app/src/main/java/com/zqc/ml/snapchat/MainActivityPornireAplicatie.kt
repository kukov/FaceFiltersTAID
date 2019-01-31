package com.zqc.ml.snapchat

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.otaliastudios.cameraview.CameraView
import kotlinx.android.synthetic.main.activity_mainstart.*

class MainActivityPornireAplicatie : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {   //initializeaza aplicatia
        super.onCreate(savedInstanceState)
        // Make this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_mainstart)


        Porneste_aplicatie.setOnClickListener {
            val intentToActivity2 = Intent(this, MainActivity::class.java)
            startActivity(intentToActivity2)
            finish() }


    }


}