package com.davutatajanov.redditclone.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.databinding.ActivityFeedBinding
import com.davutatajanov.redditclone.databinding.ActivityHomeBinding
import com.davutatajanov.redditclone.db.BlogPost
import com.davutatajanov.redditclone.db.BlogPostViewModel
import com.davutatajanov.redditclone.fragments.CategoryFragment
import com.davutatajanov.redditclone.fragments.PostsFragment
import com.davutatajanov.redditclone.posts.FeedContent
class HomeActivity : AppCompatActivity() {

    private lateinit var categoryFragment: CategoryFragment
    private lateinit var postsFragment: PostsFragment
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryFragment = CategoryFragment()
        postsFragment = PostsFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerCategory, categoryFragment)
            .replace(R.id.fragmentContainerPosts, postsFragment)
            .commit()

        categoryFragment.setOnCategorySelectedListener { category ->
            postsFragment.updatePosts(category)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
