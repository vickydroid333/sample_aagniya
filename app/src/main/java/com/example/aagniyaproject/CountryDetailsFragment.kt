package com.example.aagniyaproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aagniyaproject.databinding.FragmentCountryDetailsBinding
import kotlinx.coroutines.delay

class CountryDetailsFragment : Fragment(R.layout.fragment_country_details) {

    private lateinit var binding: FragmentCountryDetailsBinding
    private lateinit var viewModel: RetrofitViewModel
    private lateinit var adapter: CountryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCountryDetailsBinding.bind(view)

        val repository = RetrofitRepository()
        val factory = RetrofitViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[RetrofitViewModel::class.java]

        adapter = CountryAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        country()
        saveCountry()
    }

    private fun country() {

        lifecycleScope.launchWhenStarted {

            viewModel.getUniversity("India")

        }
    }

    private fun saveCountry() {
        lifecycleScope.launchWhenStarted {
            viewModel.countryFlow.collect {
                when (it) {
                    is Resources.Loading -> {

                        binding.progress.isVisible = true
                    }

                    is Resources.Success -> {
                        binding.progress.isVisible = false
                        Log.i("TAG", "setCountry: ${it.data}")
                        adapter.differ.submitList(it.data)

                    }

                    is Resources.Error -> {
                        binding.progress.isVisible = true
                        delay(20000)
                        binding.progress.isVisible = false
                        Toast.makeText(
                            requireContext(),
                            "Something went wrong!!",
                            Toast.LENGTH_SHORT
                        ).show()

                        Log.i("TAG", "setCountry: ${it.message}")
                    }
                }
            }
        }
    }
}