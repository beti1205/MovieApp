package com.example.movieplayer.ui.tvseries

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplayer.R
import com.example.movieplayer.databinding.TvseriesListBinding
import com.example.movieplayer.feature.fetchtvseries.domain.TVOrder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TVSeriesFragment : Fragment() {

    private val viewModel: TVSeriesViewModel by viewModels()
    private var _binding: TvseriesListBinding? = null
    private val binding get() = _binding!!
    private val sharedPreferences: SharedPreferences? by lazy {
        activity?.getPreferences(Context.MODE_PRIVATE)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
                inflater,
                R.layout.tvseries_list,
                container,
                false
        )
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        val tvSeriesAdapter = TVSeriesAdapter()
        binding.recyclerView.adapter = tvSeriesAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val savedOrder = restoreOrder()
        val selectedChipId = when (savedOrder) {
            TVOrder.TOP_RATED -> R.id.top_rated
            TVOrder.ON_THE_AIR -> R.id.on_the_air
            TVOrder.AIRING_TODAY -> R.id.airing_today
            else -> R.id.popular
        }
        binding.chipGroup.check(selectedChipId)
        viewModel.onOrderChanged(savedOrder)

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val order = when (checkedId) {
                R.id.top_rated -> TVOrder.TOP_RATED
                R.id.airing_today -> TVOrder.AIRING_TODAY
                R.id.on_the_air -> TVOrder.ON_THE_AIR
                else -> TVOrder.POPULAR
            }

            viewModel.onOrderChanged(order)
            saveOrder(order)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                tvSeriesAdapter.submitData(pagingData)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.appbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search_item -> { true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
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