package org.antmobile.livedata

import android.app.Application
import org.antmobile.livedata.case1.Case1ViewModel
import org.antmobile.livedata.case2.Case2ViewModel
import org.antmobile.livedata.case3.Case3ViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                module {
                    viewModel { Case1ViewModel() }
                    viewModel { Case2ViewModel() }
                    viewModel { Case3ViewModel() }
                }
            )
        }
    }
}
