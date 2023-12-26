package com.davutatajanov.redditclone.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.databinding.ActivityAddPostBinding
import com.davutatajanov.redditclone.databinding.ActivityMainBinding
import com.davutatajanov.redditclone.db.BlogPost
import com.davutatajanov.redditclone.db.BlogPostViewModel
import java.util.Collections

class AddPost : AppCompatActivity() {

    lateinit var binding: ActivityAddPostBinding
    private lateinit var blogPostViewModel:BlogPostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        blogPostViewModel = ViewModelProvider(this).get(BlogPostViewModel::class.java)

        binding.btnBack.setOnClickListener{
            addBlogPost()
            finish()
        }
    }
    fun addBlogPost(){
        var title=binding.etPostTitle.text.toString()
        var content=binding.etPostContent.text.toString()

        var blogPost=BlogPost(title=title,content=content,topic="a")

        blogPostViewModel.addBlogPost(blogPost)
    }
}