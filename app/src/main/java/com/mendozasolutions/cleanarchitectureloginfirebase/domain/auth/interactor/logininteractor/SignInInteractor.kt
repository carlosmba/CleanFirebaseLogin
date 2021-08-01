package com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.logininteractor

import com.google.firebase.auth.AuthResult
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.model.User
import com.mendozasolutions.cleanarchitectureloginfirebase.vo.Resource

interface SignInInteractor {

    suspend fun signInWithEmailAndPassword(user : User) : Resource<AuthResult>
}