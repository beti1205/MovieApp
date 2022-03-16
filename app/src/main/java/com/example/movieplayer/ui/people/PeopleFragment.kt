package com.example.movieplayer.ui.people

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieplayer.R
import com.example.movieplayer.databinding.PeopleListBinding

class PeopleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: PeopleListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.people_list,
            container,
            false
        )
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.appbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_item -> findNavController().navigate(
                PeopleFragmentDirections.actionPeopleFragmentToSearchPeopleFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}