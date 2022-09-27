package com.example.aagniyaproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.aagniyaproject.databinding.FragmentLogoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Logout : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentLogoutBinding
    private lateinit var dataStore: LoginDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLogoutBinding.inflate(inflater, container, false)

       dataStore = LoginDataStore(requireContext())

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logout.setOnClickListener {

            lifecycleScope.launchWhenStarted {
                dataStore.clearValues()
                dialog?.dismiss()
                requireActivity().finish()
                requireContext().startActivity(Intent(requireContext(), Login::class.java))
            }

        }

        binding.cancelButton.setOnClickListener {

            dialog?.dismiss()

        }

    }

}