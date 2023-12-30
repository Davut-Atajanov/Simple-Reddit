package  com.davutatajanov.redditclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.db.BlogPost

class CustomListViewAdapter(private val context: Context, private val items: ArrayList<BlogPost>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.list_view, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val currentItem = getItem(position) as BlogPost
        viewHolder.tvPostTitle.text = currentItem.title
        viewHolder.tvPostContent.text = currentItem.content
        // Set up click listeners and other event handling

        return view
    }

    private class ViewHolder(view: View) {
        val tvPostTitle: TextView = view.findViewById(R.id.tvPostTitle)
        val tvPostContent: TextView = view.findViewById(R.id.tvPostContent)
    }
}
