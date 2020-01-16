package com.overSadBoy.samurairise.dagger.module

import androidx.lifecycle.ViewModel
import com.overSadBoy.samurairise.dagger.ViewModelKey
import com.overSadBoy.samurairise.view.activity.add.AddViewModel
import com.overSadBoy.samurairise.view.activity.alarms.AlarmsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddViewModel::class)
    internal abstract fun addViewModel(viewModel: AddViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlarmsViewModel::class)
    internal abstract fun alarmsViewModel(viewModel: AlarmsViewModel): ViewModel

}