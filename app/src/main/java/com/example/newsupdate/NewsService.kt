package com.example.newsupdate

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org"
const val API_KEY = "ce02a24facbf498fbd48d4c1d961712e"

interface NewsInterface {
  //https://newsapi.org/v2/top-headlines?country=us&apiKey=ce02a24facbf498fbd48d4c1d961712e
  //https://newsapi.org
     @GET("v2/top-headlines?country=us&apiKey=$API_KEY")
     fun getHeadlines(@Query("country") country : String,@Query("page") page : Int) : Call<News>


 }
object NewsService{
    val newsInstance : NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }


}