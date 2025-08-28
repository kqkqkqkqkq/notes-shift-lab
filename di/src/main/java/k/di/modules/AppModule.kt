package k.di.modules

import k.detail_logic.DetailViewModel
import k.main_logic.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(
            repository = get(),
        )
    }
    viewModel { (id: Long) ->
        DetailViewModel(
            repository = get(),
            id = id,
        )
    }
}