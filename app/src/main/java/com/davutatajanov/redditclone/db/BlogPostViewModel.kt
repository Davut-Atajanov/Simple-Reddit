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
        val studentDAO= BlogPostRoomDatabase.getDatabase(application).BlogPostDao()
        repository = BlogPostRepository(studentDAO)
        readAllData = repository.readAlldata
    }
    fun addBlogPost(blogPost:BlogPost){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertBlogPost(blogPost)
        }
    }
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
    fun addBlogPosts(blogPost: List<BlogPost>){
        viewModelScope.launch(Dispatchers.IO) { // that code will be run in background thread, coroutine scope
            blogPost.forEach{
                repository.insertBlogPost(it)
            }
        }
    }
    fun deleteBlogPost(blogPost:BlogPost){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteBlogPost(blogPost)
        }
    }
    fun deleteAllBlogPosts(){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteAllBlogPosts()
        }
    }
    fun updateBlogPost(blogPost:BlogPost){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.updateBlogPost(blogPost)
        }
    }
}