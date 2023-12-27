package com.davutatajanov.redditclone.posts

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val apiService: PostApiServiceI by lazy {
        Retrofit.Builder()
            .baseUrl("https://yzvb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApiServiceI::class.java)
    }
}
