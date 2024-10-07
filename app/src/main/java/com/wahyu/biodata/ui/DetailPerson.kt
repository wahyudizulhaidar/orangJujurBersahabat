package com.wahyu.biodata.ui

import android.os.Bundle
import android.util.Log
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
        val personDescDetail = resources.getStringArray(R.array.desc_detail)
        val personPhoto = resources.obtainTypedArray(R.array.photo_person)

        binding.tvPersonName.text = personName[index]
        binding.tvPersonNick.text = personNick[index]
        binding.tvPersonDescDetail.text = personDescDetail[index]

        binding.tvFollow.text = getString(R.string.follow_me)
        binding.tvDetailPerson.text = getString(R.string.detail_text)

        binding.imgSocialmediafb.setImageResource(R.drawable.facebook)
        binding.imgSocialmedia2.setImageResource(R.drawable.instagram)
        binding.imgSocialmedia3.setImageResource(R.drawable.tiktok)

        val resourceId = personPhoto.getResourceId(index, -1)
        if (resourceId != -1) {
            binding.imgPerson.setImageResource(resourceId)
        } else {
            Log.e("DetailPerson", "Gambar tidak ditemukan untuk index: $index")
//            binding.imgPerson.setImageResource(R.drawable.rahul)
        }

        personPhoto.recycle()
    }
}
