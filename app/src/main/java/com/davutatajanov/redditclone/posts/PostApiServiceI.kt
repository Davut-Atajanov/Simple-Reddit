package com.davutatajanov.redditclone.posts

import retrofit2.http.GET

interface PostApiServiceI {
    @GET("posts.json")
    suspend fun getBlogPosts(): List<PostModel>
}