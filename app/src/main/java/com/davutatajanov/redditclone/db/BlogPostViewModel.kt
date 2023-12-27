package com.davutatajanov.redditclone.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.davutatajanov.redditclone.posts.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between repository and the UI
 */
class BlogPostViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<BlogPost>>
    private val repository:BlogPostRepository
    init {
        val StudentDAO= BlogPostRoomDatabase.getDatabase(application).BlogPostDao()
        repository = BlogPostRepository(StudentDAO)
        readAllData = repository.readAlldata
    }
    fun addBlogPost(BlogPost:BlogPost){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertBlogPost(BlogPost)
        }
    }
    // Function to get a student by ID using coroutines
    fun getBlogPostById(id: Int, onSuccess: (BlogPost) -> Unit, onError: (Exception) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val blogpost = repository.getBlogPostById(id)
                // Return the result to the UI thread
                launch(Dispatchers.Main) {
                    onSuccess(blogpost)
                }
            } catch (e: Exception) {
                // Handle error on the UI thread
                launch(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }
    fun addBlogPosts(BlogPost: List<BlogPost>){
        viewModelScope.launch(Dispatchers.IO) { // that code will be run in background thread, coroutine scope
            BlogPost.forEach{
                repository.insertBlogPost(it)
            }
        }
    }
    fun deleteBlogPost(BlogPost:BlogPost){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteBlogPost(BlogPost)
        }
    }
    fun deleteAllBlogPosts(){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteAllBlogPosts()
        }
    }
    fun updateBlogPost(BlogPost:BlogPost){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.updateBlogPost(BlogPost)
        }
    }
}