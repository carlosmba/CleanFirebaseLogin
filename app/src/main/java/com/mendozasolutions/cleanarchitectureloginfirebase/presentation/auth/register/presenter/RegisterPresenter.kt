package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.presenter

import androidx.core.util.PatternsCompat
import com.google.android.material.textfield.TextInputLayout
import com.mendozasolutions.cleanarchitectureloginfirebase.R
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.registerinteractor.SignUpInteractor
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.RegisterContract
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.model.Register
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.view.RegisterActivity
import com.mendozasolutions.cleanarchitectureloginfirebase.vo.Resource
import kotlinx.coroutines.*
import javax.inject.Inject

class RegisterPresenter @Inject constructor(private val interactor : SignUpInteractor
) : RegisterContract.Presenter {

    private var view : RegisterActivity? = null
    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    override fun attachView(view : RegisterActivity) {
        this.view = view
    }

    override fun validateCredentials(email : String, pass1 : String, pass2 : String): Boolean {
        var isValid = true
        if(!checkValidEmail(email)){
            view?.showErrorEmail()
            isValid = false
        }else{
            view?.hideErrorEmail()
        }

        if(!checkPassMatch(pass1, pass2)){
            view?.showErrorPass()
            isValid = false
        }else{
            view?.hideErrorPass()
        }

        return isValid

    }

    override fun checkNotEmptyFields(vararg fields: TextInputLayout) : Boolean{
        var isValid = true
        for(field in fields){
            if(field.editText?.text.toString().trim().isEmpty()){
                field.error = view?.getString(R.string.message_empty_error)
                isValid = false
            }else{
                field.error = null
            }
        }
        return isValid
    }

    override fun checkValidEmail(email: String): Boolean {
        var isValidEmail = true
        if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            view?.showErrorEmail()
            isValidEmail = false
        }else{
            view?.hideErrorEmail()
        }
        return isValidEmail
    }


    override fun checkPassMatch(pass1: String, pass2: String): Boolean {
       var isMatch = true
        if(pass1 != pass2){
           view?.showErrorPass()
            isMatch = false
       }else{
           view?.hideErrorPass()
        }
        return isMatch
    }

    override fun signUp(register: Register) {
        scope.launch{
            view?.showProgressBar()
            when(val result = interactor.signUpWithEmailAndPassword(register)){
                is Resource.Success -> {
                    if(view != null){
                        view?.hideProgressBar()
                        view?.navigateToMain()
                    }
                }
                is Resource.Failure -> {
                    view?.hideProgressBar()
                    view?.showErrorRegister(result.throwable.message)
                }
            }



        }

    }

    override fun onDestroy() {
        view = null
        scope.cancel()
    }



}