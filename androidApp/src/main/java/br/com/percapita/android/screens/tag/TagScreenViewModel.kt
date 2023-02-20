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

class TagScreenViewModel(
    private val repository: TagRepository = TagRepository.instance
): ViewModel() {

    init {
        getAllTags()
    }

    private val _tag = MutableStateFlow<DataResult<List<Tag>>>(DataResult.Empty)
    val tag: StateFlow<DataResult<List<Tag>>> = _tag

    fun getAllTags() = viewModelScope.launch {
        repository.getAllTags().collectLatest {
            _tag.value = it
        }
    }
}