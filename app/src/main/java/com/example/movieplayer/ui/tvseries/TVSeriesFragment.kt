package com.example.movieplayer.ui.tvseries

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.edit
import androidx.core.view.MenuProvider
import androidx.core.view.doOnPreDraw
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val savedOrder = restoreOrder()
        val selectedChipId = when (savedOrder) {
            TVOrder.TOP_RATED -> R.id.topRated
            TVOrder.ON_THE_AIR -> R.id.on_the_air
            TVOrder.AIRING_TODAY -> R.id.airing_today
            else -> R.id.popular
        }
        binding.chipGroup.check(selectedChipId)
        viewModel.onOrderChanged(savedOrder)

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val order = when (checkedId) {
                R.id.topRated -> TVOrder.TOP_RATED
                R.id.airing_today -> TVOrder.AIRING_TODAY
                R.id.on_the_air -> TVOrder.ON_THE_AIR
                else -> TVOrder.POPULAR
            }

            viewModel.onOrderChanged(order)
            saveOrder(order)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tvSeries.collectLatest { pagingData ->
                tvSeriesAdapter.submitData(pagingData)
            }
        }
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
