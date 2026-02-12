package com.example.dareWheel.challenge.domain.usecase

import com.example.dareWheel.challenge.domain.ChallengeEntity

class PickRandomChallengeUseCase {
    operator fun invoke(challenges: List<ChallengeEntity>): ChallengeEntity? {
        if (challenges.isEmpty()) {
            return null
        }
        return challenges.random()
    }
}