package me.sitech.health.app.modules

import me.sitech.health.domain.usecase.RedeemUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single { RedeemUseCase(get()) }
}