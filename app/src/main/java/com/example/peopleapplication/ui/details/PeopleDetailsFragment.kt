package com.example.peopleapplication.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.peopleapplication.R
import com.example.peopleapplication.databinding.FragmentPeopleDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The Fragment to show people details
 */
@AndroidEntryPoint
class PeopleDetailsFragment : Fragment(R.layout.fragment_people_details) {

    private lateinit var binding: FragmentPeopleDetailsBinding
    private val viewModel: PeopleDetailsViewModel by viewModels()
    private val args: PeopleDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPeopleDetailsBinding.bind(view)

        // Find people with provided id
        val peopleId = args.Id

        peopleId.let {
            viewModel.getPeopleDetails(peopleId).observe(viewLifecycleOwner, { people ->
                binding.apply {
                    textViewName.text = people?.name
                    textViewMet.text = people?.metAt
                    buttonContact.text = people?.contact
                    textViewEmail.text = people?.email
                    textViewFacebook.text = people?.facebook
                    textViewTwitter.text = people?.twitter
                }
            })
        }
    }

}
