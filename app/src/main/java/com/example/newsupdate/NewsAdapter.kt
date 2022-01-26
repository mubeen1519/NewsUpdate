package com.example.newsupdate

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context, private val articles: List<Article>): RecyclerView.Adapter<NewsAdapter.newsViewHolder>() {


    class newsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
    val newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)
    val newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
      val view = LayoutInflater.from(context).inflate(R.layout.item_news,parent,false)
        return newsViewHolder(view)
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val article= articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailNews::class.java)
            intent.putExtra("Url", article.url)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return articles.size
    }

}
