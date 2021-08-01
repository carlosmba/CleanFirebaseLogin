package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.di

import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.di.LoginComponent
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.di.SubcomponentsModule
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.view.LoginActivity
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.passwordrecover.view.PasswordRecoverActivity
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.view.RegisterActivity
import dagger.Component

@Component(modules = [PresentationModule::class, SubcomponentsModule::class])
interface PresentationComponent {
    fun loginComponent() : LoginComponent.Factory
    fun inject(passwordRecoverActivity : PasswordRecoverActivity)
    fun inject(registerActivity : RegisterActivity)
}