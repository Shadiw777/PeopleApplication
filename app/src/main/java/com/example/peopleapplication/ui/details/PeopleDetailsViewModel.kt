package com.example.peopleapplication.ui.details

import androidx.lifecycle.*
import com.example.peopleapplication.data.model.People
import com.example.peopleapplication.data.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleDetailsViewModel @Inject constructor(private val peopleRepository: PeopleRepository) :
    ViewModel() {

    private val peopleId = MutableLiveData<Int>()

    fun getPeopleDetails(id: Int): LiveData<People> {
        peopleId.value = id

        return Transformations.switchMap(peopleId) {
            peopleRepository.findPeople(it).asLiveData()
        }
    }

}