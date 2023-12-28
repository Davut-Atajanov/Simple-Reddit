package  com.davutatajanov.redditclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.db.BlogPost
import com.davutatajanov.redditclone.posts.PostModel


class CustomRecyclerViewAdapter(private val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var recyclerItemValues: MutableList<PostModel> = mutableListOf()
    fun setData(items:List<PostModel>){
        recyclerItemValues.addAll(items)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View
        val inflator = LayoutInflater.from(viewGroup.context)

        itemView = inflator.inflate(R.layout.recycler_view, viewGroup, false)
        return BlogPostRecyclerViewItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recyclerItemValues.size
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = recyclerItemValues[position]

        val itemView = myRecyclerViewItemHolder as BlogPostRecyclerViewItemHolder
        itemView.tvPostTitle.text=currentItem.title
        itemView.tvPostContent.text=currentItem.content
    }

    internal inner class BlogPostRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvPostTitle: TextView
        var tvPostContent: TextView

        init {
            tvPostTitle = itemView.findViewById<TextView>(R.id.tvPostTitle)
            tvPostContent = itemView.findViewById<TextView>(R.id.tvPostContent)
        }
    }

}
