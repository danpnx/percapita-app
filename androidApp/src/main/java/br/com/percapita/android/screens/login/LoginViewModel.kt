package br.com.percapita.android.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.model.Login
import br.com.percapita.model.ProfileToken
import br.com.percapita.repository.LoginRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository = LoginRepository.instance) : ViewModel() {

    private val _loginState: MutableStateFlow<DataResult<ProfileToken>> = MutableStateFlow(DataResult.Empty)
    val loginState: StateFlow<DataResult<ProfileToken>> = _loginState

    fun login(username: String, password: String) = viewModelScope.launch {
        val login = Login(username, password)

        repository.login(login).collectLatest {
            _loginState.value = it
        }
    }

    fun defaultState() {
        _loginState.value = DataResult.Empty
    }
}