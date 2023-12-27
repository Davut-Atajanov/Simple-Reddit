package com.davutatajanov.redditclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.davutatajanov.redditclone.databinding.ActivityMainBinding
import com.davutatajanov.redditclone.db.BlogPost
import com.davutatajanov.redditclone.db.BlogPostViewModel
import com.davutatajanov.redditclone.posts.AddPost
import com.davutatajanov.redditclone.posts.Feed
import java.util.Collections


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private lateinit var blogPostViewModel: BlogPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.feedBtn.setImageResource(R.drawable.feed)
        binding.profileBtn.setImageResource(R.drawable.profile)
        binding.postBtn.setImageResource(R.drawable.post)



        blogPostViewModel = ViewModelProvider(this).get(BlogPostViewModel::class.java)
        prepareData()

        binding.postBtn.setOnClickListener{
            val intent = Intent(this, AddPost::class.java)
            startActivity(intent)
        }

        binding.feedBtn.setOnClickListener{
            val intent2 = Intent(this, Feed::class.java)
            startActivity(intent2)
        }

    }

    fun prepareData(){
        var blogPosts=ArrayList<BlogPost>()
        Collections.addAll(blogPosts)

        blogPostViewModel.addBlogPosts(blogPosts)
    }
}