package com.davutatajanov.redditclone.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.davutatajanov.redditclone.util.Constants

@Dao
interface BlogPostDAO {
    // The conflict strategy defines what happens,if there is an existing entry.
    // The default action is ABORT.
    //@Insert(onConflict = OnConflictStrategy.IGNORE) //if there is a conflict it will be ignored, if there is a new Student with the same data it will jut ignored
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBlogPost(BlogPost: BlogPost)

    @Update
    fun updateBlogPost(BlogPost: BlogPost)

    @Delete
    fun deleteBlogPost(BlogPost: BlogPost)

    @Query("DELETE FROM ${Constants.TABLENAME}")
    fun deleteAllBlogPosts()

    @Query("SELECT * FROM ${Constants.TABLENAME} ORDER BY postId ASC")
    fun getAllBlogPosts():LiveData<List<BlogPost>>

    @Query("SELECT * FROM ${Constants.TABLENAME} WHERE postId =:postID")
    fun getBlogPostById(postID: Int):BlogPost

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBlogPosts(BlogPosts: ArrayList<BlogPost>){
        BlogPosts.forEach{
            insertBlogPost(it)
        }
    }

}