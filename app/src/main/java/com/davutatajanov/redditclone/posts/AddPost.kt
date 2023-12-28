package com.davutatajanov.redditclone.posts

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.databinding.ActivityAddPostBinding
import com.davutatajanov.redditclone.db.BlogPost
import com.davutatajanov.redditclone.db.BlogPostViewModel
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import java.util.Date

class AddPost : AppCompatActivity() {

    lateinit var binding: ActivityAddPostBinding
    private lateinit var blogPostViewModel:BlogPostViewModel
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mediaPlayer = MediaPlayer.create(this, R.raw.sound_file)
        blogPostViewModel = ViewModelProvider(this).get(BlogPostViewModel::class.java)

        binding.postButton.setOnClickListener{
            addBlogPost()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }

    }
    fun addBlogPost(){
        var title=binding.etPostTitle.text.toString()
        var content=binding.etPostContent.text.toString()
        var current_date:Date= Date()

        if(title.isEmpty()||title.length<3){
            Toast.makeText(this, "Inappropriate input length: Title!!", Toast.LENGTH_LONG).show()
        }else if(content.isEmpty()){
            Toast.makeText(this, "Content file cannot be empty!!", Toast.LENGTH_LONG).show()
        }else{
            var blogPost=BlogPost(current_date =current_date.toString() , title=title,content=content,topic="a")
            blogPostViewModel.addBlogPost(blogPost)
            Toast.makeText(this, "Post Created!", Toast.LENGTH_LONG).show()
            playSound()
            finish()
        }

    }

    private fun playSound() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            mediaPlayer = MediaPlayer.create(this, R.raw.sound_file)
        }
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}