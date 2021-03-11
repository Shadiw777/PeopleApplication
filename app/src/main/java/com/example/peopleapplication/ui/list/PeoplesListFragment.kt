package com.example.peopleapplication.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peopleapplication.R
import com.example.peopleapplication.data.model.People
import com.example.peopleapplication.databinding.FragmentPeoplesListBinding
import com.example.peopleapplication.utils.OnQueryTextChange
import dagger.hilt.android.AndroidEntryPoint

/**
 * The Fragment to show people list
 */
@AndroidEntryPoint
class PeoplesListFragment : Fragment(R.layout.fragment_peoples_list),
    PeoplesListAdapter.OnItemClickListener,
    SearchView.OnCloseListener {

    private lateinit var searchView: SearchView
    private lateinit var binding: FragmentPeoplesListBinding
    private lateinit var peoplesListAdapter: PeoplesListAdapter
    private val viewModel: PeoplesListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPeoplesListBinding.bind(view)
        peoplesListAdapter = PeoplesListAdapter(this)

        binding.apply {
            // Navigate to add people
            addFab.setOnClickListener {
                val action =
                    PeoplesListFragmentDirections.actionPeoplesListFragmentToAddPeopleFragment()
                findNavController().navigate(action)
            }

            peopleRecyclerView.apply {
                adapter = peoplesListAdapter
            }
        }

        viewModel.getPeopleList().observe(viewLifecycleOwner, { peoples ->
            peoples?.let {
                peoplesListAdapter.submitList(it)
            }
        })

        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_peoples_list, menu)

        // Initialize Search View
        searchView = menu.findItem(R.id.menu_search)?.actionView as SearchView

        val pendingQuery = viewModel.searchQuery.value
        if (pendingQuery != null && pendingQuery.isNotEmpty()) {
            searchView.onActionViewExpanded()
            searchView.setQuery(pendingQuery, false)
        }

        searchView.OnQueryTextChange {
            viewModel.searchPeopleByName(it)
            viewModel.searchQuery.value = it
        }

        searchView.setOnCloseListener(this)
        searchView.isIconified = true
    }

    /**
     * Callback for searchView close
     */
    override fun onClose(): Boolean {
        viewModel.getAllPeoples()
        searchView.onActionViewCollapsed()
        return true
    }

    /**
     * Navigates to people details on item click
     */
    override fun onItemClick(people: People) {
        val action =
            PeoplesListFragmentDirections.actionPeoplesListFragmentToPeopleDetailsFragment(people.id)

        findNavController().navigate(action)
    }

}
