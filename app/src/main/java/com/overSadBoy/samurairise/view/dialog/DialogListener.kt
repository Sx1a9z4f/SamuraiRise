package com.overSadBoy.samurairise.view.dialog

import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

interface DialogListener {
    open fun positiveClick()
    open fun negativeClick()
}