package br.com.percapita.android.screens.tag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.model.Tag
import br.com.percapita.repository.TagRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CreateTagViewModel(
    private val repository: TagRepository = TagRepository.instance
): ViewModel() {

    private val _registerTag: MutableStateFlow<DataResult<Tag>> = MutableStateFlow(DataResult.Empty)
    val registerTag: StateFlow<DataResult<Tag>> = _registerTag

    fun registerTag(tagName: String, id: String?) = viewModelScope.launch {
        val register = Tag(tagName = tagName, id = id ?: "")

        repository.registerTag(register).collectLatest {
            _registerTag.value = it
        }
    }
}