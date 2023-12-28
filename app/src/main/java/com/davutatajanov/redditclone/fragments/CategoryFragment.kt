package com.davutatajanov.redditclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davutatajanov.redditclone.R
import com.davutatajanov.redditclone.db.BlogPostViewModel

class CategoryFragment : Fragment() {

    private lateinit var blogPostViewModel: BlogPostViewModel
    private var onCategorySelected: ((String) -> Unit)? = null

    fun setOnCategorySelectedListener(listener: (String) -> Unit) {
        onCategorySelected = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView: ListView = view.findViewById(R.id.listViewCategory)

        blogPostViewModel = ViewModelProvider(requireActivity()).get(BlogPostViewModel::class.java)
        blogPostViewModel.readAllData.observe(viewLifecycleOwner, Observer { posts ->
            val uniqueCategories = posts.distinctBy { it.topic }.map { it.topic }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, uniqueCategories)
            listView.adapter = adapter
        })

        listView.setOnItemClickListener { _, _, position, _ ->
            onCategorySelected?.invoke((listView.adapter.getItem(position) as String))
        }
    }
}
