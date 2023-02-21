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

class EditTagViewModel(
    private val repository: TagRepository = TagRepository.instance
): ViewModel() {

    private val _editTag: MutableStateFlow<DataResult<Tag>> = MutableStateFlow(DataResult.Empty)
    val editTag: StateFlow<DataResult<Tag>> = _editTag

    fun editTag(tagName: String, id: String) = viewModelScope.launch {
        val edit = Tag(tagName = tagName, id = id)

        repository.editTag(edit).collectLatest {
            _editTag.value = it
        }
    }
}