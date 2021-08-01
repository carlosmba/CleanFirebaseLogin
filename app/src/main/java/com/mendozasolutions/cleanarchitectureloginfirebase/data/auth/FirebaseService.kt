package com.mendozasolutions.cleanarchitectureloginfirebase.data.auth

import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.model.User

interface FirebaseService {
    interface FirebaseCallback{
        fun onSuccess()
        fun onFailure()
    }

    fun signInWithEmailAndPassword(user : User, listener : FirebaseCallback )

}