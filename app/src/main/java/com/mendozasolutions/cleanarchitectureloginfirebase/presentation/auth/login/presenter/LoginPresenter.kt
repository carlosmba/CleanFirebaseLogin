package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.presenter

import android.util.Log
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.logininteractor.SignInInteractor
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.LoginContract
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.di.ActivityScope
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.model.User
import com.mendozasolutions.cleanarchitectureloginfirebase.vo.Resource
import kotlinx.coroutines.*
import javax.inject.Inject
@ActivityScope
class LoginPresenter @Inject constructor(var interactor : SignInInteractor ) : LoginContract.Presenter{
    private val TAG = "LoginInteractor"
    private var view : LoginContract.View? = null
    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    override fun attachView(viewLogin: LoginContract.View) {
        view = viewLogin
    }


    override fun checkEmptyField(data : String) : Boolean{
        return data.isEmpty()
    }

    override fun validateCredentials(user : User): Boolean {
        var isValid = true

        if(user.email.isEmpty()){
            view?.showErrorEmail()
            isValid = false
        }else{
            view?.hideErrorEmail()
        }

        if(user.password.isEmpty()){
            view?.showErrorPass()
            isValid = false
        }else{
            view?.hideErrorPass()
        }

        return isValid
    }


    override fun onDestroy() {
        view = null
        scope.cancel()
    }

    override fun isViewAttach() : Boolean {
        return view != null
    }


    override fun signIn(user: User) {
        scope.launch{
            view?.hideKeyboard()
            view?.showProgressBar()

            when(val result = interactor.signInWithEmailAndPassword(user)){
                is Resource.Success -> {
                    if(isViewAttach()){
                        view?.hideProgressBar()
                        view?.navigateToMain()
                    }
                }
                is Resource.Failure -> {
                    view?.hideProgressBar()
                    view?.showErrorAuth(result.throwable.message)
                }
            }
        }

    }


}