package com.zqc.ml.snapchat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark

class OverlayView5(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // The detected face
    var face: FirebaseVisionFace? = null
        set(value) {
            field = value

            // Trigger redraw when a new detected face object is passed in
            postInvalidate()
        }

    // The preview width
    var previewWidth: Int? = null

    // The preview height
    var previewHeight: Int? = null

    private var widthScaleFactor = 1.0f
    private var heightScaleFactor = 1.0f

    // The batman mask bitmap
    private val glassesBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.glasses)
    private val batmanBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Create local variables here so they canot not be changed anywhere else
        val face = face
        val previewWidth = previewWidth
        val previewHeight = previewHeight

        if (face != null && canvas != null && previewWidth != null && previewHeight != null) {

            // Calculate the scale factor
            widthScaleFactor = canvas.width.toFloat() / previewWidth.toFloat()
            heightScaleFactor = canvas.height.toFloat() / previewHeight.toFloat()

            drawGlasses(canvas, face)

        }
    }

    /***
     * Draw batman mask on top of eyes
     */
    private fun drawGlasses(canvas: Canvas, face: FirebaseVisionFace) {
        val leftCheek = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_CHEEK)
        val rightCheek = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_CHEEK)
        if (leftCheek != null && rightCheek != null) {
            val cheekDistance = leftCheek.position.x - rightCheek.position.x
            val delta = (widthScaleFactor * cheekDistance / 1.5 ).toInt()
            val glassesRect = Rect(
                    translateX(leftCheek.position.x).toInt() - (delta + 150),
                    translateY(leftCheek.position.y).toInt() - (delta + 450),
                    translateX(rightCheek.position.x).toInt() + (delta + 150),
                    translateY(rightCheek.position.y).toInt() + (delta+ 450))
            canvas.drawBitmap(batmanBitmap, null, glassesRect, null)
            //canvas.drawBitmap(ochelariBitmap, null, glassesRect, null)
        }
    }





    /**
     * Adjusts the x coordinate from the preview's coordinate system to the view coordinate system.
     */
    private fun translateX(x: Float): Float {
        return width - scaleX(x)
    }

    /**
     * Adjusts the y coordinate from the preview's coordinate system to the view coordinate system.
     */
    private fun translateY(y: Float): Float {
        return scaleY(y - 165)
    }

    /** Adjusts a vertical value of the supplied value from the preview scale to the view scale. */
    private fun scaleX(x: Float): Float {
        return x * widthScaleFactor
    }


    /** Adjusts a vertical value of the supplied value from the preview scale to the view scale. */
    private fun scaleY(y: Float): Float {
        return y * heightScaleFactor
    }
}