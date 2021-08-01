package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login

import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.model.User

interface LoginContract {

    interface View{
        fun showProgressBar()
        fun hideProgressBar()
        fun navigateToMain()
        fun navigateToPasswordRecover()
        fun hideKeyboard()
        fun showErrorAuth(message : String?)
        fun showErrorEmail()
        fun showErrorPass()
        fun hideErrorEmail()
        fun hideErrorPass()

    }


    interface Presenter{
        fun attachView(viewLogin : LoginContract.View)
        fun checkEmptyField(data: String) : Boolean
        fun validateCredentials(user : User) : Boolean
        fun onDestroy()
        fun isViewAttach() : Boolean
        fun signIn(user : User)

    }

}