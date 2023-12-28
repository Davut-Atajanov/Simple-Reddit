package com.davutatajanov.redditclone.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.db.BlogPost
import com.davutatajanov.redditclone.db.BlogPostViewModel
import com.davutatajanov.redditclone.posts.FeedContent

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


        blogPostViewModel.readAllData.observe(this, Observer { it ->
            val uniqueContentValues = it.distinctBy { it.topic }

            val titles = uniqueContentValues.map { it.topic }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)
            val postAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)

            // Set the adapter to the ListView
            listViewTitle.adapter = adapter
            listViewPersonalPosts.adapter = postAdapter
            Log.d("Personal Posts",titles.toString())

            listViewTitle.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedTitle = titles[position]
                // Exclude posts with the selected title from it
                val selectedPosts = it.filterNot { it.topic == selectedTitle }

                val postAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, selectedPosts)
                listViewPersonalPosts.adapter = postAdapter
            }

        })
    }
}