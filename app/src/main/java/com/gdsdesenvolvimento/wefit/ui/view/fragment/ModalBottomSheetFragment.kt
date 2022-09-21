package com.gdsdesenvolvimento.wefit.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdsdesenvolvimento.wefit.R
import com.gdsdesenvolvimento.wefit.databinding.FragmentModalBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentModalBottomSheetBinding
    private lateinit var viewModel: FragmentModalBottomSheetBinding

    companion object {
        private var fragmentInstnce: ModalBottomSheetFragment? = null
        fun newInstance(): ModalBottomSheetFragment {
            fragmentInstnce = fragmentInstnce ?: ModalBottomSheetFragment()
            return fragmentInstnce!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModalBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

}