package com.example.dareWheel.challenge.data

import com.example.dareWheel.challenge.domain.ChallengeEntity
import com.example.dareWheel.challenge.domain.ChallengeRepository

class ChallengeRepositoryImpl(private val challengeDao: ChallengeDao) : ChallengeRepository {

    override suspend fun getAllChallenges(): List<ChallengeEntity> {
        return challengeDao.getAllChallenges().map { ChallengeEntity(id = it.id.toString(), name = it.name) }
    }

    override suspend fun addChallenge(challenge: ChallengeEntity) {
        challengeDao.insertChallenge(ChallengeDTO(name = challenge.name))
    }
}