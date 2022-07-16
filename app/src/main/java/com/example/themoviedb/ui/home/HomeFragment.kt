package com.example.themoviedb.ui.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.state.Result
import com.example.themoviedb.R
import com.example.themoviedb.base.BaseFragment
import com.example.themoviedb.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val adapter by lazy {
        HomeAdapter()
    }
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpObservers()
    }

    private fun setUpUi() {
        setMenu()
        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setUpObservers() {
        viewModel.providers.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {

                }
                is Result.Success -> {
                    it.data
                }
                is Result.EmptyList -> {

                }
                is Result.Error -> {

                }
                is Result.ErrorNetwork -> {

                }
            }
        }
    }

    private fun setMenu() {
        val galery = HomeAdapter.Menu("Provider", R.drawable.ic_launcher_foreground)
        val posts = HomeAdapter.Menu("Popular", R.drawable.ic_launcher_foreground)
        val menus = listOf(galery, posts)
        adapter.setData(menus)
    }
}