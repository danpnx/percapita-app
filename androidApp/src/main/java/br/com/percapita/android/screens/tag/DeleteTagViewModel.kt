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

class DeleteTagViewModel(
    private val repository: TagRepository = TagRepository.instance
): ViewModel() {

    private val _deleteTag: MutableStateFlow<DataResult<Tag>> = MutableStateFlow(DataResult.Empty)
    val deleteTag: StateFlow<DataResult<Tag>> = _deleteTag

    fun deleteTag(tagName: String, id: String) = viewModelScope.launch {
        val delete = Tag(tagName = tagName, id = id)

        repository.deleteTag(delete).collectLatest {
            _deleteTag.value = it
        }
    }
}