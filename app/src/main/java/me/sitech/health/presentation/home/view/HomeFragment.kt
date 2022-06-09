package me.sitech.health.presentation.home.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.sitech.health.R
import me.sitech.health.app.utils.RequestState
import me.sitech.health.app.utils.viewBinding
import me.sitech.health.databinding.FragmentHomeBinding
import me.sitech.health.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment: Fragment(R.layout.fragment_home) {

    val binding by viewBinding(FragmentHomeBinding::bind)
    val viewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.redeem()

        observeStateFlow()

        viewModel.getStepRecordsList()


    }

    private fun observeStateFlow() {
        viewModel.mRedeemStateFlow.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        ).onEach {
            when(it){
                is RequestState.Error -> {
                    Toast.makeText(requireActivity(),it.exception.message, Toast.LENGTH_SHORT).show()
                }
                is RequestState.Loading -> {

                }
                is RequestState.Success -> {

                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.mStepRecordsListStateFlow.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        ).onEach {
            when(it){
                is RequestState.Error -> {
                    Toast.makeText(requireActivity(),it.exception.message, Toast.LENGTH_SHORT).show()
                }
                is RequestState.Loading -> {

                }
                is RequestState.Success -> {
                    binding.tvText.text = Gson().toJson(it.data)

                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}