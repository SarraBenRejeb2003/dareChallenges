package com.example.dareWheel.challenge.data.mappers

import com.example.dareWheel.challenge.data.ChallengeModel
import com.example.dareWheel.challenge.domain.ChallengeEntity

fun ChallengeEntity.toModel(): ChallengeModel {
    return ChallengeModel(name = this.name)
}