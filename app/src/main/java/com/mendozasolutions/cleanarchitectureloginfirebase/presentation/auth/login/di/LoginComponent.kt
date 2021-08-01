package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.di

import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.view.LoginActivity
import dagger.Subcomponent
@ActivityScope
@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create() : LoginComponent
    }

    fun inject(loginActivity : LoginActivity)

}