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

class MyRecyclerAdapter(private val natList: MutableList<ListNatural>, private val activity: Fragment, val clickListener: (ListNatural) -> Unit): RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerHolder>() {

        class MyRecyclerHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var name: TextView? = itemView.findViewById(R.id.txt_name_alert_elem)
            val amount: TextView? = itemView.findViewById(R.id.txt_amount_elem)
            val image: ImageView? = itemView.findViewById(R.id.image_movie_alert_elem)
        }

        override fun getItemCount() = natList.size

        override fun onBindViewHolder(holder: MyRecyclerHolder, position: Int) {
            holder.name?.text = natList[position].title
            holder.amount?.text = natList[position].date
            if(natList[position].media_type == "video"){
                holder.image?.setImageResource(R.mipmap.ic_launcher_foreground)
            }else{
                holder.image?.let { Glide.with(activity).load(natList[position].url).into(it) }
            }
            holder.itemView.setOnClickListener {
                val listnew = ListNatural(natList[position].copyright,natList[position].date,natList[position].explanation, natList[position].hdurl, natList[position].media_type,
                natList[position].service_version,natList[position].title,natList[position].url)
                clickListener(listnew)
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false)
            return MyRecyclerHolder(itemView)
        }
    }