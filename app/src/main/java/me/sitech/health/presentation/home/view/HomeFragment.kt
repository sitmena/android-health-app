package me.sitech.health.presentation.home.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import me.sitech.health.R
import me.sitech.health.app.utils.viewBinding
import me.sitech.health.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}