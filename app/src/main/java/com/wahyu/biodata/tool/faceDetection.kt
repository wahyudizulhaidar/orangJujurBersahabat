package com.wahyu.biodata.tool

import android.graphics.Bitmap
import android.widget.ImageView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

private fun faceDetection (image: Bitmap, imageView: ImageView) {
    val inputImage = InputImage.fromBitmap(image, 0)

    val options = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
        .build()

    val detector = FaceDetection.getClient(options)

    detector.process(inputImage)
        .addOnSuccessListener { faces ->
            if (faces.isNotEmpty()) {
                val face: Face = faces[0]
                val bounds = face.boundingBox

                val croppedBitmap = Bitmap.createBitmap(
                    image,
                    bounds.left,
                    bounds.top,
                    bounds.width(),
                    bounds.height()
                )

                imageView.setImageBitmap(croppedBitmap)
            } else {
                imageView.setImageBitmap(image)
            }
        }
        .addOnFailureListener {
            imageView.setImageBitmap(image)
        }
}