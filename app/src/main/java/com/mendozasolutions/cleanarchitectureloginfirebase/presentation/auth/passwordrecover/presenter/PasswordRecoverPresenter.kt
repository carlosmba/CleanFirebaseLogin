package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.passwordrecover.presenter

import android.util.Log
import androidx.core.util.PatternsCompat
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.passwordrecoverinteractor.PasswordRecoverInteractor
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.passwordrecover.PasswordRecoverContract
import com.mendozasolutions.cleanarchitectureloginfirebase.vo.Resource
import kotlinx.coroutines.*
import javax.inject.Inject

class PasswordRecoverPresenter @Inject constructor(private val passwordRecoverInteractor : PasswordRecoverInteractor) : PasswordRecoverContract.PasswordRecoverPresenter {
    private val TAG = "PasswordRecoverPresenter"
    private var view : PasswordRecoverContract.PasswordRecoverView? = null
    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    override fun attachView(viewPasswordRecover : PasswordRecoverContract.PasswordRecoverView) {
        view = viewPasswordRecover
    }


    override fun sendPasswordRecover(email: String) {
        scope.launch {
            view?.showProgress()

            when(val result = passwordRecoverInteractor.sendPasswordResetEmail(email)){
                is Resource.Success -> {
                    if(view != null){
                        Log.i(TAG, "Envio Exitoso")
                        view?.hideProgress()
                        view?.showMessageSend()
                        view?.previousActivity()
                    }
                }
                is Resource.Failure -> {
                    view?.hideProgress()
                    view?.showAuthError(result.throwable.message)
                }
            }


        }

    }

    override fun checkValidEmail(email: String): Boolean {
        return !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyEmail(email: String): Boolean {
        return email.isEmpty()
    }

    override fun onDestroy() {
        view = null
        scope.cancel()
    }
}