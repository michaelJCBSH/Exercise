package com.michael.presentation

import android.animation.AnimatorInflater
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.michael.DaggerFragmentX
import com.michael.R
import com.michael.domain.RowModel
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.row_item_layout.view.*
import javax.inject.Inject


class ListFragment : DaggerFragmentX() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: Adapter
    private val layoutManager: androidx.recyclerview.widget.GridLayoutManager =
        androidx.recyclerview.widget.GridLayoutManager(context, 1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = layoutManager
        swipeLayout.isEnabled = true
        swipeLayout.setOnRefreshListener {
            viewModel.loadData()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = Adapter(context!!)
        list.adapter = adapter
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
        viewModel.bindViewModel()
    }

    private fun ListViewModel.bindViewModel() {
        getTitleLiveData().observe(this@ListFragment.viewLifecycleOwner, Observer {
            activity?.title = it
        })
        getRowsLiveData().observe(this@ListFragment.viewLifecycleOwner, Observer {
            adapter.setData(it)
            swipeLayout.isRefreshing = false
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    inner class Adapter(private val context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v =
                LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
            val holder = ViewHolder(v)
            holder.itemView.stateListAnimator = AnimatorInflater.loadStateListAnimator(context, R.animator.list_item_elevation)
            return holder
        }

        override fun getItemCount(): Int = rows.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            rows[position].run {
                holder.itemView.title.text = title
                holder.itemView.description.text = description
                val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                Glide.with(context).load(rows[position].image_href?.toHTTPS()).apply(requestOptions)
                    .placeholder(R.mipmap.placeholder)
                    .into(holder.itemView.image)

            }
        }

        private fun String.toHTTPS(): String {
            return replace("http", "https")
        }

        private val rows: MutableList<RowModel> = mutableListOf()

        fun setData(it: List<RowModel>) {
            rows.clear()
            rows.addAll(it)
            notifyDataSetChanged()
        }
    }
}
