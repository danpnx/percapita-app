package br.com.percapita.android.screens.tag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.payload.TagList
import br.com.percapita.repository.TagRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TagScreenViewModel(
    private val repository: TagRepository = TagRepository.instance
): ViewModel() {

    init {
        getAllTags()
    }

    private val _tag = MutableStateFlow<DataResult<TagList>?>(null)
    val tag: StateFlow<DataResult<TagList>?> = _tag

    fun getAllTags() = viewModelScope.launch {
        repository.getAllTags().collectLatest {
            _tag.value = it
        }
    }
}