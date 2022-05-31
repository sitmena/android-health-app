package me.sitech.health.domain.usecase

import me.sitech.health.domain.repository.MainRepository
import javax.inject.Inject

class RedeemUseCase @Inject constructor(private val repository: MainRepository) {

    suspend operator fun invoke() = repository.redeem()
}