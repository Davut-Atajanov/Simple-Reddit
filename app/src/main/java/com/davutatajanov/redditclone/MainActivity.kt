package com.davutatajanov.redditclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.davutatajanov.redditclone.adapter.CustomRecyclerViewAdapter
import com.davutatajanov.redditclone.databinding.ActivityMainBinding
import com.davutatajanov.redditclone.db.BlogPost
import com.davutatajanov.redditclone.db.BlogPostViewModel
import com.davutatajanov.redditclone.home.HomeActivity
import com.davutatajanov.redditclone.posts.AddPost
import com.davutatajanov.redditclone.posts.Feed
import com.davutatajanov.redditclone.posts.FeedContent
import com.davutatajanov.redditclone.posts.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import java.util.Collections


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var blogPostViewModel: BlogPostViewModel

    private var clickCount = 0
    private val requiredClicks = 5

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

        val konfettiView = findViewById<KonfettiView>(R.id.viewKonfetti)
        binding.imageView2.setOnClickListener {
            clickCount++
            if (clickCount >= requiredClicks) {
                konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(1000L)
                    .addShapes(Shape.Square, Shape.Circle)
                    .addSizes(Size(12))
                    .setPosition(-50f, konfettiView.width + 50f, -50f, -50f)
                    .streamFor(300, 2000L)

                clickCount = 0 // Reset the counter
            }
        }

        binding.postBtn.setOnClickListener {
            val intent = Intent(this, AddPost::class.java)
            startActivity(intent)
        }

        binding.feedBtn.setOnClickListener {
            val intent2 = Intent(this, Feed::class.java)
            startActivity(intent2)
        }

        binding.profileBtn.setOnClickListener {
            val intent2 = Intent(this, HomeActivity::class.java)
            startActivity(intent2)
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val blogPosts = RetrofitService.apiService.getBlogPosts()
                // Update LiveData or state here
                FeedContent.posts = blogPosts
                Log.d("MainActivity", blogPosts.toString())
            } catch (e: Exception) {
                // Handle the exception
                Log.e("MainActivity", "Error in getting JSON file", e)
                println("There was an error in getting json file")
            }
        }

    }

    fun prepareData() {
        var blogPosts = ArrayList<BlogPost>()
        Collections.addAll(blogPosts)

        blogPostViewModel.addBlogPosts(blogPosts)
    }
}