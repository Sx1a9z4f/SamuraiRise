package com.OverSadBoy.samurairise.dagger

import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.presenter.Presenter
import dagger.Component
import org.junit.runner.RunWith

@Component(modules = [PresenterModule::class, ModelModule::class, DBModule::class])
interface PresenterComponent {
    open fun getPresenter(): Presenter?
}