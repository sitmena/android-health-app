package me.sitech.health.domain.usecase

import me.sitech.health.data.model.StepEntity
import me.sitech.health.domain.repository.MainRepository

class DeleteStepRecordUseCase constructor(private val repository: MainRepository) {

    suspend operator fun invoke(stepEntity: StepEntity) = repository.deleteStepsRecord(stepEntity)
}