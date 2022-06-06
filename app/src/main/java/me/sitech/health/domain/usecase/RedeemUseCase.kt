package me.sitech.health.domain.usecase

import me.sitech.health.domain.repository.MainRepository

class RedeemUseCase constructor(private val repository: MainRepository) {

    suspend operator fun invoke() = repository.redeem()
}