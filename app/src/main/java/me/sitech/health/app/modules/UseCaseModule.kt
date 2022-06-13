package me.sitech.health.app.modules

import me.sitech.health.domain.usecase.DeleteStepRecordUseCase
import me.sitech.health.domain.usecase.InsertStepRecordUseCase
import me.sitech.health.domain.usecase.RedeemUseCase
import me.sitech.health.domain.usecase.StepRecordsUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single { RedeemUseCase(get()) }
    single { StepRecordsUseCase(get()) }
    single { InsertStepRecordUseCase(get()) }
    single { DeleteStepRecordUseCase(get()) }
}