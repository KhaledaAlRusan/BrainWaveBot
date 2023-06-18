package com.example.brainwavebot.presentation.features.choosing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.brainwavebot.R
import com.example.brainwavebot.databinding.FragmentChooseListBinding

class FragmentChooseList: Fragment() {
    private lateinit var binding: FragmentChooseListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseListBinding.inflate(inflater, container, false)

        val items = arrayOf("Chat", "Item 2", "Item 3") // Your list items
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        binding.listView.adapter = adapter

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as String

            if (item == "Chat") {
                findNavController().navigate(R.id.action_fragmentChooseList_to_fragmentChatting)
            }
        }
    }
}