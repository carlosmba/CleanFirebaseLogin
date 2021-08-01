package com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.passwordrecoverinteractor

import com.google.firebase.auth.FirebaseAuth
import com.mendozasolutions.cleanarchitectureloginfirebase.vo.Resource
import kotlinx.coroutines.tasks.await

class PasswordRecoverInteractorImpl : PasswordRecoverInteractor {
    override suspend fun sendPasswordResetEmail(email : String) : Resource<Void> {
        return try{
            val data = FirebaseAuth.getInstance().sendPasswordResetEmail(email).await()
            Resource.Success(data)
        }catch (e : Exception){
            Resource.Failure(e)
        }
    }
}