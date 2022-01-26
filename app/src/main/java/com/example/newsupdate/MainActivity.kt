package com.example.newsupdate

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles= mutableListOf<Article>()
    private var pageNum = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
        val newsList = findViewById<RecyclerView>(R.id.newsList)
        adapter = NewsAdapter(this@MainActivity, articles)
        newsList.adapter = adapter
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("us",pageNum)
        news.enqueue(object : Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("MUBEENCODE","Error in Call back",t)
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<News>, response: Response<News>){
                val news = response.body()
                if (news != null){

                   articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()


                }
            }

        })
    }

}



