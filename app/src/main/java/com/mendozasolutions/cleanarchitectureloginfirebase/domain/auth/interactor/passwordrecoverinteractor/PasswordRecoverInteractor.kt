package com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.passwordrecoverinteractor

import com.mendozasolutions.cleanarchitectureloginfirebase.vo.Resource

interface PasswordRecoverInteractor {

    suspend fun sendPasswordResetEmail(email : String) : Resource<Void>
}