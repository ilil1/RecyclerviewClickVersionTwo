package com.project.recyclerviewclickversiontwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter : RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<Model>()
        for(i: Int in 1..10) {
            var contact = Model("Add ", i.toString(), "https://picsum.photos/200")
            list.add(contact)
        }

        recyclerview = findViewById(R.id.recyclerView)

        adapter = RecyclerAdapter(list)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        adapter.setItemClickListener(object: RecyclerAdapter.ClickListener {
            override fun onClick(item: Model) {
                // 클릭 시 이벤트 작성
                Toast.makeText(this@MainActivity,
                    "${item.content}",
                    Toast.LENGTH_SHORT).show()
            }
        })
    }
}