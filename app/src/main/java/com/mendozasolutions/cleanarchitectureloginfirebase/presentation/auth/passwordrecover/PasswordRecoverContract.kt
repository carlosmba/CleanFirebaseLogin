package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.passwordrecover

interface PasswordRecoverContract {

    interface PasswordRecoverView{
        fun showProgress()
        fun hideProgress()
        fun showAuthError(message : String?)
        fun showMessageSend()
        fun sendEmailRecover()
        fun previousActivity()
    }

    interface PasswordRecoverPresenter{
        fun attachView(viewPasswordRecover : PasswordRecoverContract.PasswordRecoverView)
        fun sendPasswordRecover(email : String)
        fun checkValidEmail(email : String) : Boolean
        fun checkEmptyEmail(email: String) : Boolean
        fun onDestroy()
    }


}