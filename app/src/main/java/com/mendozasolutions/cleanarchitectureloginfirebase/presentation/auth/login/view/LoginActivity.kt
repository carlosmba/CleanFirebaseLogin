package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.mendozasolutions.cleanarchitectureloginfirebase.CleanFirebaseLoginApp
import com.mendozasolutions.cleanarchitectureloginfirebase.R
import com.mendozasolutions.cleanarchitectureloginfirebase.base.BaseActivity
import com.mendozasolutions.cleanarchitectureloginfirebase.databinding.ActivityLoginBinding
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.logininteractor.SignInInteractorImpl
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.LoginContract
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.di.LoginComponent
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.model.User
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.login.presenter.LoginPresenter
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.passwordrecover.view.PasswordRecoverActivity
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.main.view.MainActivity
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.view.RegisterActivity
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {
    @Inject lateinit var presenter : LoginPresenter
    var loginComponent : LoginComponent? = null
    private lateinit var mBinding : ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        loginComponent = (applicationContext as CleanFirebaseLoginApp).getAppComponent()?.loginComponent()?.create()
        loginComponent?.inject(this)
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        presenter.attachView(this)
        mBinding.btnSignIn.setOnClickListener { signInWithEmailAndPass() }
        mBinding.tvRegister.setOnClickListener { navigateToRegister() }
        mBinding.tvSignInRecoverEmail.setOnClickListener { navigateToPasswordRecover() }

    }

    override fun showProgressBar() {
        mBinding.btnSignIn.visibility = View.GONE
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mBinding.progressBar.visibility = View.GONE
        mBinding.btnSignIn.visibility = View.VISIBLE
    }



    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToPasswordRecover() {
        startActivity(Intent(this, PasswordRecoverActivity::class.java))
    }

    override fun hideKeyboard() {
        val imn = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(mBinding.root.windowToken, 0)

    }

    override fun showErrorAuth(message : String?) {
        toast(message!!)
    }

    override fun showErrorEmail() {
        mBinding.tilEmail.error = getString(R.string.message_empty_email)
    }

    override fun showErrorPass() {
        mBinding.tilPass.error = getString(R.string.message_empty_password)
    }

    override fun hideErrorEmail() {
        mBinding.tilEmail.error = null
    }

    override fun hideErrorPass() {
        mBinding.tilPass.error = null
    }


    private fun signInWithEmailAndPass(){
        val user = User(mBinding.etEmail.text.toString().trim(), mBinding.etPass.text.toString().trim())
        if(presenter.validateCredentials(user)){
            presenter.signIn(user)
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    fun navigateToRegister(){
        startActivity(Intent(this, RegisterActivity::class.java))
    }


}