package com.project.recyclerviewclickversiontwo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.recyclerviewclick.clear
import com.project.recyclerviewclick.load

class RecyclerAdapter(private val items: ArrayList<Model>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title : TextView = itemView.findViewById(R.id.titleText)
        val content : TextView = itemView.findViewById(R.id.contentText)
        val thumbnail : ImageView = itemView.findViewById(R.id.thumbnail)

        fun bind(item: Model) {
            thumbnail.clear()
            title.text = item.title
            content.text = item.content
            thumbnail.load(item.imageurl, 16f)
        }

        fun bindViews(item: Model, listener: ClickListener)  {
            itemView.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.board_list, parent, false)
        return RecyclerAdapter.ViewHolder(View)
    }

    // 1. ClickListener 인터페이스
    interface ClickListener {
        fun onClick(item: Model)
    }

    // 2. MainActivity에서 클릭 시 이벤트 설정
    fun setItemClickListener(itemclickListener: ClickListener) {
        this.ItemClickListener = itemclickListener
    }

    // 3. setItemClickListener로 받아온 파라미터 할당
    private lateinit var ItemClickListener : ClickListener

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        // 4. 리스트 내 항목 클릭 시 onClick() 호출
        holder.bindViews(item, ItemClickListener)
    }
}