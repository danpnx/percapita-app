package br.com.percapita.android.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        getProfile()
    }

    private val _profile = MutableStateFlow<DataResult<User>>(DataResult.Empty)
    val profile: StateFlow<DataResult<User>> = _profile

    fun editProfile(user: User) = viewModelScope.launch {
        repository.editProfile(user).collectLatest {
            _profile.value = it
        }
    }

    fun getProfile() = viewModelScope.launch {
        repository.getProfile().collectLatest {
            _profile.value = it
        }
    }

}