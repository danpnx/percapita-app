package br.com.percapita.android.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.model.EditUser
import br.com.percapita.model.User
import br.com.percapita.repository.ProfileRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val repository: ProfileRepository = ProfileRepository.instance
): ViewModel() {

    private val _editProfile = MutableStateFlow<DataResult<User>>(DataResult.Empty)
    val editProfile: StateFlow<DataResult<User>> = _editProfile

    private val _profile = MutableStateFlow<DataResult<User>>(DataResult.Empty)
    val profile: StateFlow<DataResult<User>> = _profile

    fun editName(user: User) = viewModelScope.launch {
        repository.editName(user).collectLatest {
            _profile.value = it
        }
    }

    fun editPassword(user: User) = viewModelScope.launch {
        repository.editPassword(user).collectLatest {
            _profile.value = it
        }
    }

    fun getProfile() = viewModelScope.launch {
        repository.getProfile().collectLatest {
            _profile.value = it
        }
    }

    fun editUser(
        actualPassword: String,
        newPassword: String,
        newName: String
    ) = viewModelScope.launch {

        val edit = EditUser(actualPassword, newPassword, newName)

        repository.editUser(edit).collectLatest {
            _editProfile.value = it
        }
    }

    fun defaultState() {
        _editProfile.value = DataResult.Empty
    }
}