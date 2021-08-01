package com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.registerinteractor

import com.google.firebase.auth.AuthResult
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.model.Register
import com.mendozasolutions.cleanarchitectureloginfirebase.vo.Resource

interface SignUpInteractor {

    suspend fun signUpWithEmailAndPassword(register : Register) : Resource<AuthResult>
}