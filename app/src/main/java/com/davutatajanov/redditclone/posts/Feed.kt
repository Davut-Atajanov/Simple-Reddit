package com.davutatajanov.redditclone.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.WindowManager
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.adapter.CustomRecyclerViewAdapter
import com.davutatajanov.redditclone.databinding.ActivityFeedBinding
import com.davutatajanov.redditclone.db.BlogPostViewModel
import com.google.android.material.snackbar.Snackbar

class Feed : AppCompatActivity() , GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

    lateinit var binding:ActivityFeedBinding
    lateinit var adapter: CustomRecyclerViewAdapter
    private lateinit var blogPostViewModel: BlogPostViewModel

    var gDetector: GestureDetectorCompat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_feed)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        blogPostViewModel = ViewModelProvider(this).get(BlogPostViewModel::class.java)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()

        gDetector =  GestureDetectorCompat(this, this)
        gDetector?.setOnDoubleTapListener(this)

        adapter = CustomRecyclerViewAdapter(this)
        binding.postRV.setLayoutManager(LinearLayoutManager(this))
        binding.postRV.adapter = adapter

        binding.btnBack.setOnClickListener {
            finish()
        }


    }

    fun getData() {
        //Whenever data is changed that change will refresh the recyclerview
        blogPostViewModel.readAllData.observe(this, Observer {
            Log.d("Feed Activity", FeedContent.posts.toString())
            val posts = FeedContent.posts.shuffled()
            adapter.setData(posts)
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gDetector?.onTouchEvent(event)
        Log.i("GESTURE","onTouchEvent ${event.action}")
        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent): Boolean {
        return false    }

    override fun onShowPress(e: MotionEvent) {
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return false    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false    }

    override fun onLongPress(e: MotionEvent) {
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        return false    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        finish()
        //setContentView(R.layout.activity_main)
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent): Boolean {
        return false
    }
}