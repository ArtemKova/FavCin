package com.ka.favcin.newarch.di

import android.app.Application
import com.ka.favcin.ui.dashboard.DashboardFragment
import com.ka.favcin.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

fun inject(fragment: HomeFragment)
fun inject(fragment: DashboardFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}