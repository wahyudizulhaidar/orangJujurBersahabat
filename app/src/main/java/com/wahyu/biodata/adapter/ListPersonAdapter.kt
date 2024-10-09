package com.wahyu.biodata.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wahyu.biodata.R
import com.wahyu.biodata.data.Person
import com.wahyu.biodata.tool.FaceDetection

// Menggunakan Model Machine Learning Face Detector
class ListPersonAdapter(private val listPerson: ArrayList<Person>) : RecyclerView.Adapter<ListPersonAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val faceDetection = FaceDetection()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_person, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, nick, description, photo) = listPerson[position]

        val bitMap = BitmapFactory.decodeResource(holder.itemView.context.resources, photo)
        faceDetection.faceDetector(bitMap, holder.imgPhoto)

        holder.imgPhoto.setImageResource(photo)
        holder.tvNick.text = nick
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listPerson[holder.adapterPosition], position)
        }
    }

    override fun getItemCount(): Int = listPerson.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_person)
        val tvName: TextView = itemView.findViewById(R.id.tv_person_name)
        val tvNick: TextView = itemView.findViewById(R.id.tv_person_nick)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_person_desc)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Person, index: Int)
    }
}


// Tidak menggunakan model ML
/*
class ListPersonAdapter(private val listPerson: ArrayList<Person>) : RecyclerView.Adapter<ListPersonAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_person, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, nick, description, photo) = listPerson[position]

        holder.imgPhoto.setImageResource(photo)
        holder.tvNick.text = nick
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listPerson[holder.adapterPosition], position)
        }
    }

    override fun getItemCount(): Int = listPerson.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_person)
        val tvName: TextView = itemView.findViewById(R.id.tv_person_name)
        val tvNick: TextView = itemView.findViewById(R.id.tv_person_nick)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_person_desc)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Person, index: Int)
    }
}*/
