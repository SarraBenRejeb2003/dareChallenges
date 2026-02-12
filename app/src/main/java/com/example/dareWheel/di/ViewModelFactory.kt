package com.example.dareWheel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dareWheel.challenge.domain.ChallengeRepository
import com.example.dareWheel.challenge.presentation.viewmodel.ChallengeViewModel
import com.example.dareWheel.challenge.presentation.viewmodel.WheelViewModel

class ViewModelFactory(private val repository: ChallengeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChallengeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChallengeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(WheelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WheelViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}