package com.beti1205.movieapp.ui.tvseries

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.beti1205.movieapp.R
import com.beti1205.movieapp.databinding.TvseriesListBinding
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.feature.fetchtvseries.domain.TVOrder
import com.beti1205.movieapp.ui.common.Preferences
import com.beti1205.movieapp.ui.common.getErrorState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TVSeriesFragment : Fragment(R.layout.tvseries_list) {

    @Inject
    lateinit var preferences: Preferences

    private val viewModel: TVSeriesViewModel by hiltNavGraphViewModels(R.id.tvGraph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: TvseriesListBinding = TvseriesListBinding.bind(requireView())
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        postponeEnterTransition()

        val tvSeriesAdapter = TVSeriesAdapter { itemView, tvSeries ->
            navigateToTvSeriesDetails(itemView, tvSeries)
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
        val selectedChipId = getSelectedChipId(savedOrder)
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
            TVSeriesMenuProvider {
                findNavController().navigate(
                    TVSeriesFragmentDirections.actionTVSeriesFragmentToSearchTvSeriesFragment()
                )
            },
            viewLifecycleOwner,
            Lifecycle.State.STARTED
        )
    }

    private fun getSelectedChipId(savedOrder: TVOrder) =
        when (savedOrder) {
            TVOrder.TOP_RATED -> R.id.topRated
            TVOrder.ON_THE_AIR -> R.id.onTheAir
            TVOrder.AIRING_TODAY -> R.id.airingToday
            else -> R.id.popular
        }

    private fun navigateToTvSeriesDetails(
        itemView: View,
        tvSeries: TVSeries
    ) {
        val extras = FragmentNavigatorExtras(
            itemView to getString(R.string.tv_card_detail_transition_name)
        )

        findNavController().navigate(
            TVSeriesFragmentDirections.actionTVSeriesFragmentToTVSeriesDetailsFragment(tvSeries),
            extras
        )
    }

    private fun saveOrder(order: TVOrder) {
        preferences.tvOrder = order.ordinal
    }

    private fun restoreOrder(): TVOrder = TVOrder.from(preferences.tvOrder)
}
