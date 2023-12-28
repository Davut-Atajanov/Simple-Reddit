package com.davutatajanov.redditclone.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.db.BlogPostViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var blogPostViewModel: BlogPostViewModel
    lateinit var listViewTitle: ListView
    lateinit var listViewPersonalPosts: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        listViewTitle = findViewById(R.id.listViewCategory)
        listViewPersonalPosts = findViewById(R.id.listViewPersonalPosts)
        blogPostViewModel = ViewModelProvider(this).get(BlogPostViewModel::class.java)

//        val data = blogPostViewModel.readAllData.observe(
//
//        )
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
//        listViewTitle.adapter = adapter



    }
}