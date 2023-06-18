package com.example.brainwavebot.presentation.features.chatting

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.brainwavebot.databinding.FragmentChatBinding
import com.example.brainwavebot.domain.models.ChoiceResponse
import com.example.brainwavebot.presentation.features.chatting.adapter.ChattingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentChatting: Fragment()  {
    //define viewmodel
    private val viewModel: ChattingViewModel by viewModels()
    private lateinit var binding: FragmentChatBinding
    private lateinit var adapter: ChattingAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set viewbinding
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch{
            viewModel.answerStateFlow.collect (::onSuccess)
        }
        lifecycleScope.launch {
            viewModel.answerLoadingStateFlow.collect(::onLoading)
        }
        lifecycleScope.launch {
            viewModel.answerErrorStateFlow.collect(::onErrorPatients)
        }
    }

    private fun initListeners() {

        //set adapter
        adapter = ChattingAdapter(viewModel.messages)
        binding.recyclerView.adapter = adapter

        //set button listener
        binding.btnSend.setOnClickListener {
            actionListener()
        }

    }

    private fun actionListener() {
        if (!binding.messageEditText.text.isNullOrEmpty()) {
            val text = binding.messageEditText.text.toString()
            viewModel.messages.add(ChoiceResponse(text, userType = "ME"))
            viewModel.requestGptResponse(text)
            clearChatBox()
        } else {
            Toast.makeText(requireContext(), "Write Something", Toast.LENGTH_SHORT)
                .show()
        }
    }
    private fun clearChatBox() {
        binding.messageEditText.setText("")
        binding.welcomeText.text = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onSuccess(text:String?) {
        if (text != null) {
                viewModel.messages.add(ChoiceResponse(text))
                adapter.notifyDataSetChanged()
                binding.recyclerView.smoothScrollToPosition(viewModel.messages.size - 1)
        }
    }
    private fun onLoading(show: Boolean) {
        binding.progressCircular.isVisible = show
        binding.btnSend.isClickable = !show
    }

    private fun onErrorPatients(response: Exception?) {
        if (response != null)
            Log.d("TAG00", " Error: ${response.message.toString()}")
    }

}

