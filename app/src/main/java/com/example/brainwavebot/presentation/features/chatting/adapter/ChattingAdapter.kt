package com.example.brainwavebot.presentation.features.chatting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.brainwavebot.databinding.ItemChatBinding
import com.example.brainwavebot.domain.models.ChoiceResponse

//adapter for chat

class ChattingAdapter(private var messageModelList: List<ChoiceResponse>): RecyclerView.Adapter<ChattingAdapter.ChattingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingViewHolder {
        val binding = ItemChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ChattingViewHolder(binding)
    }

    override fun getItemCount()= messageModelList.size

    override fun onBindViewHolder(holder: ChattingViewHolder, position: Int) {
        val currentItem = messageModelList[position]
        holder.bind(currentItem)
    }

    inner class ChattingViewHolder(private val binding:ItemChatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(choiceResponse: ChoiceResponse) {
            if (choiceResponse.userType == "ME") {
                binding.leftChatView.isVisible = false
                binding.rightChatView.isVisible = true
                binding.tvRightChat.text = choiceResponse.text.trim()
            } else if (choiceResponse.userType == "BOT") {
                binding.rightChatView.isVisible = false
                binding.leftChatView.isVisible = true
                binding.tvLeftChat.text = choiceResponse.text.trim()
            }
        }
    }

}