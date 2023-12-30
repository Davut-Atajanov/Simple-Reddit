package com.davutatajanov.redditclone.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.adapter.CustomListViewAdapter
import com.davutatajanov.redditclone.db.BlogPostViewModel

class PostsFragment : Fragment() {

    private lateinit var blogPostViewModel: BlogPostViewModel
    private lateinit var customAdapter: CustomListViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blogPostViewModel = ViewModelProvider(requireActivity()).get(BlogPostViewModel::class.java)

    }*/

    /*fun updatePosts(category: String) {
        blogPostViewModel.readAllData.observe(viewLifecycleOwner, Observer { posts ->
            val filteredPosts = posts.filter { it.topic == category }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, filteredPosts.map { it })
            view?.findViewById<ListView>(R.id.listViewPersonalPosts)?.adapter = adapter
        })
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blogPostViewModel = ViewModelProvider(requireActivity()).get(BlogPostViewModel::class.java)

        // Initialize with empty data
        val initialAdapter = CustomListViewAdapter(requireContext(), arrayListOf())
        view.findViewById<ListView>(R.id.listViewPersonalPosts).adapter = initialAdapter

        customAdapter = CustomListViewAdapter(requireContext(), arrayListOf())
        view.findViewById<ListView>(R.id.listViewPersonalPosts).adapter = customAdapter
    }


    fun updatePosts(category: String) {
        blogPostViewModel.readAllData.observe(viewLifecycleOwner, Observer { posts ->
            val filteredPosts = posts.filter { it.topic == category }

            // Assuming filteredPosts is a list of Social objects
            // If not, you will need to convert it to the appropriate type
            val adapter = CustomListViewAdapter(requireContext(), ArrayList(filteredPosts))

            view?.findViewById<ListView>(R.id.listViewPersonalPosts)?.adapter = adapter
        })
    }
}
