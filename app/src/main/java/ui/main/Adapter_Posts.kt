package ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rest.R
import pojo.Post_Model

class Adapter_Posts: RecyclerView.Adapter<Adapter_Posts.Posts_view_Holder>() {

    private var arraylist = arrayListOf<Post_Model>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Posts_view_Holder {

        return Posts_view_Holder(LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false))

    }

    override fun onBindViewHolder(holder: Posts_view_Holder, position: Int) {
        holder.tv_userid.text = arraylist[position].userId.toString()
        holder.tv_id.text = arraylist[position].id.toString()
        holder.tv_title.text = arraylist[position].title
        holder.tv_body.text = arraylist[position].body
    }

    override fun getItemCount(): Int {

       return arraylist.size

    }

    class Posts_view_Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var tv_userid = itemView.findViewById<TextView>(R.id.tv_user_id)
        var tv_id = itemView.findViewById<TextView>(R.id.tv_id)
        var tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        var tv_body = itemView.findViewById<TextView>(R.id.tv_body)
    }

    fun setList(ArrayList:ArrayList<Post_Model>)
    {
        this.arraylist = ArrayList
        notifyDataSetChanged()
    }
}