package com.overSadBoy.samurairise.dagger.component

import com.overSadBoy.samurairise.dagger.ViewModelFactory
import com.overSadBoy.samurairise.dagger.module.AppModule
import com.overSadBoy.samurairise.dagger.module.ViewModelModule
import com.overSadBoy.samurairise.view.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            ViewModelModule::class
        ]
)
interface AppComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(viewModelFactory: ViewModelFactory)
}