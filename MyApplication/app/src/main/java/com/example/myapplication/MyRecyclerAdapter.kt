package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.retrofit.ListNatural

class MyRecyclerAdapter(private val natList: MutableList<ListNatural>, private val activity: Fragment): RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerHolder>() {

        class MyRecyclerHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var name: TextView? = itemView.findViewById(R.id.txt_name_alert_elem)
            val amount: TextView? = itemView.findViewById(R.id.txt_amount_elem)
            val image: ImageView? = itemView.findViewById(R.id.image_movie_alert_elem)
        }

        override fun getItemCount() = natList.size

        override fun onBindViewHolder(holder: MyRecyclerHolder, position: Int) {
            holder.name?.text = natList[position].title
            holder.amount?.text = natList[position].date
            holder.image?.let { Glide.with(activity).load(natList[position].url).into(it) }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false)
            return MyRecyclerHolder(itemView)
        }
    }