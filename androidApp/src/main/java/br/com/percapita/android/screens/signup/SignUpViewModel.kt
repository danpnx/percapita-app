package br.com.percapita.android.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.model.User
import br.com.percapita.repository.SignUpRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: SignUpRepository = SignUpRepository.instance
): ViewModel() {

    private val _signUp: MutableStateFlow<DataResult<User>> = MutableStateFlow(DataResult.Empty)
    val signUp: StateFlow<DataResult<User>> = _signUp

    fun signUp(username: String, password: String, name: String) = viewModelScope.launch {
        val signUp = User(username, password, name)

        repository.signUp(signUp).collectLatest {
            _signUp.value = it
        }
    }

    fun defaultState() {
        _signUp.value = DataResult.Empty
    }
}