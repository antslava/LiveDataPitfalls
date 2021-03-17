package org.antmobile.livedata.case2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.antmobile.livedata.databinding.FragmentTestBinding
import org.koin.android.viewmodel.ext.android.viewModel

class Case2Fragment : Fragment() {

    private var _binding: FragmentTestBinding? = null

    @Suppress("UnsafeCallOnNullableType")
    private val binding
        get() = _binding!!

    private val viewModel by viewModel<Case2ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.uiState.observe(viewLifecycleOwner) {
            binding.firstLabel.text = "First value: ${it.firstValue}"
            binding.secondLabel.text = "Second value: ${it.secondValue}"
        }
        binding.fetch.setOnClickListener {
            viewModel.fetch()
        }
    }

}
