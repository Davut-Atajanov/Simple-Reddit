package com.davutatajanov.redditclone.db

import androidx.lifecycle.LiveData


/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class BlogPostRepository(private val BlogPostDAO: BlogPostDAO) {
    val readAlldata:LiveData<List<BlogPost>> = BlogPostDAO.getAllBlogPosts()

    fun insertBlogPost(BlogPost:BlogPost){
        BlogPostDAO.insertBlogPost(BlogPost)
    }

    fun insertAllBlogPosts(BlogPost:ArrayList<BlogPost>){
        BlogPostDAO.insertAllBlogPosts(BlogPost)
    }

    fun updateBlogPost(BlogPost: BlogPost){
        BlogPostDAO.updateBlogPost(BlogPost)
    }

    fun deleteBlogPost(BlogPost: BlogPost){
        BlogPostDAO.deleteBlogPost(BlogPost)
    }

    fun deleteAllBlogPosts(){
        BlogPostDAO.deleteAllBlogPosts()
    }

    fun getAllBlogPosts():LiveData<List<BlogPost>>{
        return BlogPostDAO.getAllBlogPosts()
    }

   fun getBlogPostById(id:Int):BlogPost{
        return BlogPostDAO.getBlogPostById(id)
   }
}