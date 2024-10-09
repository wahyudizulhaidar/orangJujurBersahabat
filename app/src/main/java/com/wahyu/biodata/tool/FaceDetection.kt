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

                    val extraSpace = 50
                    val left = (bounds.left - extraSpace).coerceAtLeast(0)
                    val top = (bounds.top - extraSpace).coerceAtLeast(0)
                    val right = (bounds.right + extraSpace).coerceAtMost(image.width)
                    val bottom = (bounds.bottom + extraSpace).coerceAtMost(image.height)

                    val croppedBitmap = Bitmap.createBitmap(
                        image,
                        left,
                        top,
                        right - left,
                        bottom - top
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