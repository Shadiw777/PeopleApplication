package com.example.peopleapplication.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peopleapplication.data.model.People
import com.example.peopleapplication.data.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPeopleViewModel @Inject constructor(private val peopleRepository: PeopleRepository) :
    ViewModel() {

    fun addPeople(people: People) = viewModelScope.launch {
        peopleRepository.insertPeople(people)
    }

}