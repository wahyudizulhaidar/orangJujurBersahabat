package com.wahyu.biodata.tool

import android.graphics.Bitmap
import android.widget.ImageView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class FaceDetection {
    fun faceDetector (image: Bitmap, imageView: ImageView) {
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

                    val maxWidthToCrop = 480
                    val cropWidth = if (bounds.width() > maxWidthToCrop) {
                        maxWidthToCrop
                    } else {
                        bounds.width()
                    }

                    val cropHeight = (cropWidth * bounds.height()) / bounds.width()
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
}