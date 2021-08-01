package com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.logininteractor

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.model.User
import com.mendozasolutions.cleanarchitectureloginfirebase.vo.Resource
import kotlinx.coroutines.tasks.await

class SignInInteractorImpl() : SignInInteractor {

    private val TAG = "SignInInteractorImpl"
    override suspend fun signInWithEmailAndPassword(user: User) : Resource<AuthResult> {
        return try{
            val data = FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email, user.password).await()
              Resource.Success(data)
        }catch(e : Exception){
            Resource.Failure(e)
        }
    }

}

/**/