package com.demo.demoapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapplication.R
import com.demo.demoapplication.databinding.ItemAcronymBinding
import com.demo.demoapplication.model.Store

/*
Recycler view for search fragment, uses DataBinding
Written by NN
*/

class RestaurantListAdapter(
    var restaurantList: ArrayList<Store>,
    var listener: OnItemClickListener
) : RecyclerView.Adapter<RestaurantListAdapter.DogViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(store : Store)
    }

    fun updateRestaurantList(newacronymList: ArrayList<Store>) {
        restaurantList.clear()
        restaurantList.addAll(newacronymList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemAcronymBinding>(
            inflater,
            R.layout.item_acronym,
            parent,
            false
        )
        return DogViewHolder(view)
    }

    override fun getItemCount() = restaurantList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(restaurantList[position], listener)
    }

    class DogViewHolder(var view: ItemAcronymBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(store: Store, listener: OnItemClickListener) {
            view.store = store
            view.root.setOnClickListener {
                listener.onItemClick(store)
            }
        }
    }

}
