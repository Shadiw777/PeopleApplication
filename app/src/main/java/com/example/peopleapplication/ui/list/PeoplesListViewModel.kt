package com.example.peopleapplication.ui.list

import androidx.lifecycle.*
import com.example.peopleapplication.data.model.People
import com.example.peopleapplication.data.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeoplesListViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository,
    state: SavedStateHandle
) : ViewModel() {

    private val peopleList = MediatorLiveData<List<People>>()
    val searchQuery = state.getLiveData("searchQuery", "")

    init {
        getAllPeoples()
    }

    fun getPeopleList(): LiveData<List<People>> = peopleList

    fun getAllPeoples() = viewModelScope.launch {
        peopleList.addSource(peopleRepository.getAllPeople().asLiveData()) {
            peopleList.postValue(it)
        }
    }

    fun searchPeopleByName(name: String) = viewModelScope.launch {
        peopleList.addSource(peopleRepository.findPeopleByName(name).asLiveData()) { people ->
            peopleList.postValue(people)
        }
    }

}