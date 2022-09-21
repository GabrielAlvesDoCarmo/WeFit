package com.gdsdesenvolvimento.wefit.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gdsdesenvolvimento.wefit.R
import com.gdsdesenvolvimento.wefit.data.datasource.retorfit.RetrofitInstance
import com.gdsdesenvolvimento.wefit.databinding.FragmentModalBinding
import com.gdsdesenvolvimento.wefit.di.AppInjection
import com.gdsdesenvolvimento.wefit.ui.viewmodel.base.ViewModelFactory
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.ModalViewModel
import com.gdsdesenvolvimento.wefit.util.extensions.dialog
import com.gdsdesenvolvimento.wefit.util.state.SearchUserState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentModalBinding
    private lateinit var viewModel: ModalViewModel
    private val db by lazy {
        AppInjection.initBd(requireContext())
    }

    companion object {
        private var fragmentInstance: ModalFragment? = null
        fun newInstance(): ModalFragment {
            fragmentInstance = fragmentInstance ?: ModalFragment()
            return fragmentInstance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
    }

    private fun initViewModel(): ModalViewModel {
        return ViewModelProvider(
            this, ViewModelFactory(
                AppInjection.getRepository(
                    db,
                    RetrofitInstance.api
                )
            )
        )[ModalViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
        observers()
    }

    private fun listeners() = with(binding) {
        closeModal.setOnClickListener {
            dismiss()
        }
        btnSalvar.setOnClickListener {
            viewModel.sendForHome(editNewUserName.text.toString().trim())
            editNewUserName.setText("")
        }
    }

    private fun observers() = with(viewModel) {
        searchUser.observe(viewLifecycleOwner) { searchState ->
            when (searchState) {
                is SearchUserState.Success -> {
                    binding.txtTitle.text = getString(R.string.alterar_usuario_selecionado)
                    dismiss()
                }
                is SearchUserState.Error -> {
                    binding.txtTitle.text = getString(R.string.alterar_usuario_selecionado)
                    dialog(getString(R.string.erro), searchState.msg, true) {
                        return@dialog
                    }
                }
            }
        }
    }
}