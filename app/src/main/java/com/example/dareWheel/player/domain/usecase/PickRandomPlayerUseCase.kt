package com.example.dareWheel.player.domain.usecase

import com.example.dareWheel.player.domain.PlayerEntity

class PickRandomPlayerUseCase {
    operator fun invoke(players: List<PlayerEntity>): PlayerEntity? {
        if (players.isEmpty()) {
            return null
        }
        return players.random()
    }
}
