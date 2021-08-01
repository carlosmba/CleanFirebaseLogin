package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register

import com.google.android.material.textfield.TextInputLayout
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.model.Register
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.view.RegisterActivity

interface RegisterContract {
    interface View{
        fun navigateToMain()
        fun showProgressBar()
        fun hideProgressBar()
        fun showErrorEmail()
        fun hideErrorEmail()
        fun showErrorPass()
        fun hideErrorPass()
        fun showErrorRegister(message : String?)
        fun signUp()
    }
    interface Presenter{
        fun attachView(view : RegisterActivity)
        fun validateCredentials(email : String, pass1 : String, pass2 : String) : Boolean
        fun checkNotEmptyFields( vararg fields: TextInputLayout) : Boolean
        fun checkValidEmail(email : String) : Boolean
        fun checkPassMatch(pass1 : String, pass2: String) : Boolean
        fun signUp(register : Register)
        fun onDestroy()
    }
}