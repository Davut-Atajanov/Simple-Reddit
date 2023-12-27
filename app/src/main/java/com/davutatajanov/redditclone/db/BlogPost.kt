package com.davutatajanov.redditclone.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davutatajanov.redditclone.util.Constants
import java.util.Date


@Entity(tableName = Constants.TABLENAME)
class BlogPost(
    @PrimaryKey(autoGenerate = true)
    var postId:Int=0,
    var current_date:String,
    var title: String,
    val content:String,
    var topic: String
)
{

    override fun toString(): String {
        return "\nBlogPost\nPostID: ${postId},Current Date:${current_date} Title: $title, Topic: $topic\n Content:\n: $content\n"
    }
}
