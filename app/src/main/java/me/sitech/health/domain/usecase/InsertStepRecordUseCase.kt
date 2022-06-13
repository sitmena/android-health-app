package me.sitech.health.domain.usecase

import me.sitech.health.data.model.StepEntity
import me.sitech.health.domain.repository.MainRepository

class InsertStepRecordUseCase constructor(private val repository: MainRepository) {

    suspend operator fun invoke(stepEntity: StepEntity) = repository.insertStepsRecord(stepEntity)
}