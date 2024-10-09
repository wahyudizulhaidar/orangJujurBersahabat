package com.wahyu.biodata.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.wahyu.biodata.R
import com.wahyu.biodata.databinding.ActivityDescPersonBinding

class DetailPerson : AppCompatActivity() {

    private lateinit var binding: ActivityDescPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDescPersonBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val index = intent.getIntExtra("PERSON_INDEX", 0)

        val personName = resources.getStringArray(R.array.name_person)
        val personNick = resources.getStringArray(R.array.nick_person)
        val personDescDetail = resources.getStringArray(R.array.desc_person)
        val personPhoto = resources.obtainTypedArray(R.array.photo_person)
        val personDetail = resources.getStringArray(R.array.desc_detail)

        binding.tvPersonName.text = personName[index]
        binding.tvPersonNick.text = personNick[index]
        binding.tvPersonDescDetail.text = personDescDetail[index]

        binding.tvFollow.text = getString(R.string.follow_me)
        binding.tvDetailPerson.text = personDetail[index]

        binding.imgSocialmediafb.setImageResource(R.drawable.facebook)
        binding.imgSocialmedia2.setImageResource(R.drawable.instagram)
        binding.imgSocialmedia3.setImageResource(R.drawable.tiktok)
        binding.actionShare.setImageResource(R.drawable.share)

        val resourceId = personPhoto.getResourceId(index, -1)
        if (resourceId != -1) {
            binding.imgPerson.setImageResource(resourceId)
        } else {
            Log.e("DetailPerson", "Gambar tidak ditemukan untuk index: $index")
//            binding.imgPerson.setImageResource(R.drawable.nophoto)
        }
        personPhoto.recycle()

        binding.imgSocialmediafb.setOnClickListener {
            val message = getString(R.string.social_media1)
            showToast(message)
        }

        binding.imgSocialmedia2.setOnClickListener {
            val message = getString(R.string.social_media2)
            showToast(message)
        }

        binding.imgSocialmedia3.setOnClickListener {
            val message = getString(R.string.social_media3)
            showToast(message)
        }

        binding.actionShare.setOnClickListener {
            shareContent()
        }

    }

    private fun shareContent() {
        val shareText = binding.tvDetailPerson.text.toString()
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
