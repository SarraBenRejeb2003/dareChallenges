package com.example.dareWheel.challenge.domain

interface ChallengeRepository {
    suspend fun getAllChallenges(): List<ChallengeEntity>
    suspend fun addChallenge(challenge: ChallengeEntity)
    suspend fun deleteChallenge(challenge: ChallengeEntity)
}
