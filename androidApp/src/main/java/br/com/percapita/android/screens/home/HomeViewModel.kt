package br.com.percapita.android.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.percapita.model.Report
import br.com.percapita.repository.ReportRepository
import br.com.percapita.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * @project PerCapita
 * @author Daniel Augusto on 15/02/2023
 **/
class HomeViewModel(
    private val repository: ReportRepository = ReportRepository.instance
) : ViewModel() {
    private val _report: MutableStateFlow<DataResult<Report>> = MutableStateFlow(DataResult.Empty)
    val report: StateFlow<DataResult<Report>> = _report

    init {
        val date = LocalDate.now().toString()
        report(date)
    }

    private fun report(date: String) = viewModelScope.launch {
        repository.report(date).collectLatest {
            _report.value = it
        }
    }
}