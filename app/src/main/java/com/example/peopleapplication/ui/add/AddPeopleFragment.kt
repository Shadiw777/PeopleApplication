package com.example.peopleapplication.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.peopleapplication.R
import com.example.peopleapplication.data.model.People
import com.example.peopleapplication.databinding.FragmentAddPeopleBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The Fragment to add people
 */
@AndroidEntryPoint
class AddPeopleFragment : Fragment(R.layout.fragment_add_people) {

    private lateinit var binding: FragmentAddPeopleBinding
    private val viewModel: AddPeopleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddPeopleBinding.bind(view)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_add_people, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add -> {
                savePeopleInfo()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Saves people info from user input and returns to PeopleListActivity
     */
    private fun savePeopleInfo() {
        val people = People(
            binding.textInputName.editText?.text.toString(),
            binding.textInputMetAt.editText?.text.toString(),
            binding.textInputContact.editText?.text.toString(),
            binding.textInputEmail.editText?.text.toString(),
            binding.textInputFacebook.editText?.text.toString(),
            binding.textInputTwitter.editText?.text.toString()
        )

        viewModel.addPeople(people)
        findNavController().navigateUp()
    }

}
