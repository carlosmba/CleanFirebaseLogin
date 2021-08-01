package com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.registerinteractor

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.model.Register
import com.mendozasolutions.cleanarchitectureloginfirebase.vo.Resource
import kotlinx.coroutines.tasks.await

class SignUpInteractorImpl() : SignUpInteractor {
    override suspend fun signUpWithEmailAndPassword(register: Register) : Resource<AuthResult> {
        val auth = FirebaseAuth.getInstance()
        return try{
            val data = auth.createUserWithEmailAndPassword(register.email, register.pass).await()
            val profileUpdates = userProfileChangeRequest {
                displayName = register.fullName
            }
            auth.currentUser?.updateProfile(profileUpdates)?.await()
            Resource.Success(data)
        }catch (e : Exception){
            Resource.Failure(e)
        }


    }
    /**/

}