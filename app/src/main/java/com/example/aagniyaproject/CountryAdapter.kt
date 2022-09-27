package com.example.aagniyaproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aagniyaproject.databinding.CountryAdapterBinding

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(private val binding: CountryAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setViews(home: NewModel) {
            binding.alphaTwoCode.text = home.alpha_two_code
            binding.country.text = home.country

            var ne1 = home.domains.toString().replace("[", "")
            ne1 = ne1.replace("]", "")
            binding.domains.text = ne1

            binding.province.text = home.state_province
            binding.name.text = home.name

            var ne = home.web_pages.toString().replace("[", "")
            ne = ne.replace("]", "")
            binding.webPages.text = ne

        }

        init {

            binding.country.isSelected = true
            binding.domains.isSelected = true
            binding.name.isSelected = true
            binding.webPages.isSelected = true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

        return CountryViewHolder(
            CountryAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        val view = differ.currentList[position]
        holder.setViews(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val callback = object : DiffUtil.ItemCallback<NewModel>() {

        override fun areItemsTheSame(oldItem: NewModel, newItem: NewModel): Boolean {
            return oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: NewModel, newItem: NewModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

}