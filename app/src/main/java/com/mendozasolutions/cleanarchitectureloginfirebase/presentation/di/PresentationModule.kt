package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.di

import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.logininteractor.SignInInteractor
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.logininteractor.SignInInteractorImpl
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.passwordrecoverinteractor.PasswordRecoverInteractor
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.passwordrecoverinteractor.PasswordRecoverInteractorImpl
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.registerinteractor.SignUpInteractor
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.registerinteractor.SignUpInteractorImpl
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.LoginContract
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.presenter.LoginPresenter
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.passwordrecover.presenter.PasswordRecoverPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {


    @Provides
    fun providesSignInInteractor() : SignInInteractor = SignInInteractorImpl()

    @Provides
    fun providesSignUpInteractor() : SignUpInteractor = SignUpInteractorImpl()

    @Provides
    fun providesPasswordRecoverInteractor() :PasswordRecoverInteractor = PasswordRecoverInteractorImpl()

}