package com.OverSadBoy.samurairise.dagger

import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.model.Model
import com.OverSadBoy.samurairise.presenter.Presenter
import dagger.Module
import dagger.Provides
import org.junit.runner.RunWith

@Module
class PresenterModule {
    @Provides
    fun providePresenter(model: Model?): Presenter? {
        return Presenter(model)
    }
}