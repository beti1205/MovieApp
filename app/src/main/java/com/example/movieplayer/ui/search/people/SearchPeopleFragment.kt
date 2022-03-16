package com.example.movieplayer.ui.search.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movieplayer.R
import com.example.movieplayer.databinding.SearchListBinding

class SearchPeopleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SearchListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_list,
            container,
            false
        )

        return binding.root
    }

}