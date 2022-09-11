package com.example.movieplayer.ui.tvseries

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.core.view.MenuProvider
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplayer.R
import com.example.movieplayer.databinding.TvseriesListBinding
import com.example.movieplayer.feature.fetchtvseries.domain.TVOrder
import com.example.movieplayer.ui.common.Preferences
import com.example.movieplayer.ui.common.getErrorState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TVSeriesFragment : Fragment(R.layout.tvseries_list) {

    private val viewModel: TVSeriesViewModel by viewModels()
    private val sharedPreferences: SharedPreferences? by lazy {
        activity?.getPreferences(Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: TvseriesListBinding = TvseriesListBinding.bind(requireView())

        val tvSeriesAdapter = TVSeriesAdapter { itemView, tvSeries ->
            val extras = FragmentNavigatorExtras(
                itemView to getString(R.string.tv_card_detail_transition_name)
            )

            findNavController().navigate(
                TVSeriesFragmentDirections.actionTVSeriesFragmentToTVSeriesDetailsFragment(tvSeries),
                extras
            )
        }
        binding.tvSeriesRecyclerView.adapter = tvSeriesAdapter
        binding.tvSeriesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        tvSeriesAdapter.addLoadStateListener { states ->
            if (states.getErrorState() != null) {
                finishTransition(view)
            }
        }

        tvSeriesAdapter.addOnPagesUpdatedListener {
            finishTransition(view)
        }

        val savedOrder = restoreOrder()
        val selectedChipId = when (savedOrder) {
            TVOrder.TOP_RATED -> R.id.topRated
            TVOrder.ON_THE_AIR -> R.id.on_the_air
            TVOrder.AIRING_TODAY -> R.id.airing_today
            else -> R.id.popular
        }
        binding.chipGroup.check(selectedChipId)
        viewModel.onOrderChanged(savedOrder)

        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            onOrderChanged(checkedIds)
        }

        binding.retryButton.setOnClickListener {
            tvSeriesAdapter.retry()
        }

        addMenuProvider()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tvSeries.collectLatest { pagingData ->
                tvSeriesAdapter.submitData(pagingData)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            tvSeriesAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.errorView.isVisible = loadStates.getErrorState() != null
            }
        }
    }

    private fun onOrderChanged(checkedIds: List<Int>) {
        val order = when (checkedIds.first()) {
            R.id.topRated -> TVOrder.TOP_RATED
            R.id.airingToday -> TVOrder.AIRING_TODAY
            R.id.onTheAir -> TVOrder.ON_THE_AIR
            else -> TVOrder.POPULAR
        }

            viewModel.onOrderChanged(order)
            saveOrder(order)
        }

    private fun finishTransition(view: View) {
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun addMenuProvider() {
        activity?.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.appbar_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when (menuItem.itemId) {
                        R.id.search_item -> findNavController().navigate(
                            TVSeriesFragmentDirections.actionTVSeriesFragmentToSearchTvSeriesFragment()
                        )
                    }
                    return true
                }
            },
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )

        tvSeriesAdapter.addOnPagesUpdatedListener {
            view.doOnPreDraw { startPostponedEnterTransition() }
        }

        postponeEnterTransition()
    }

    private fun saveOrder(order: TVOrder) {
        sharedPreferences?.edit {
            putInt(getString(R.string.saved_order_key), order.ordinal)
        }
    }

    private fun restoreOrder(): TVOrder {
        val value =
            sharedPreferences?.getInt(getString(R.string.saved_order_key), TVOrder.POPULAR.ordinal)
        return TVOrder.from(value)
    }
}
