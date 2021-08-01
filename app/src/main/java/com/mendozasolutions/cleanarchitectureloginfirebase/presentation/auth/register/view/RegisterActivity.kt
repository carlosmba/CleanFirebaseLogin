package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mendozasolutions.cleanarchitectureloginfirebase.CleanFirebaseLoginApp
import com.mendozasolutions.cleanarchitectureloginfirebase.R
import com.mendozasolutions.cleanarchitectureloginfirebase.base.BaseActivity
import com.mendozasolutions.cleanarchitectureloginfirebase.databinding.ActivityRegisterBinding
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.registerinteractor.SignUpInteractorImpl
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.main.view.MainActivity
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.RegisterContract
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.model.Register
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.register.presenter.RegisterPresenter
import javax.inject.Inject

class RegisterActivity : BaseActivity(), RegisterContract.View {
    private lateinit var mBinding : ActivityRegisterBinding
    @Inject lateinit var presenter : RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as CleanFirebaseLoginApp).getAppComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        presenter = RegisterPresenter(SignUpInteractorImpl())
        presenter.attachView(this)
        mBinding.btnSignUp.setOnClickListener { signUp() }

    }

    override fun showProgressBar() {
        mBinding.btnSignUp.visibility = View.GONE
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mBinding.progressBar.visibility = View.GONE
        mBinding.btnSignUp.visibility = View.VISIBLE
    }


    override fun showErrorEmail() {
        mBinding.tilEmail.error = getString(R.string.message_error_email)
    }

    override fun hideErrorEmail() {
        mBinding.tilEmail.error = null
    }

    override fun showErrorPass() {
        mBinding.tilPass.error = getString(R.string.message_error_pass)
        mBinding.tilPassTwo.error = getString(R.string.message_error_pass)
    }

    override fun hideErrorPass() {
        mBinding.tilPass.error = null
        mBinding.tilPassTwo.error = null
    }

    override fun showErrorRegister(message : String?) {
        toast(message!!)
    }

    override fun signUp() {
        val register = Register(mBinding.etFullName.text.toString().trim(),
            mBinding.etEmail.text.toString().trim(), mBinding.etPass.text.toString().trim())
        val pass2 = mBinding.etPassTwo.text.toString().trim()

        if(presenter.checkNotEmptyFields(
                mBinding.tilFullName,
                mBinding.tilEmail,
                mBinding.tilPass,
                mBinding.tilPassTwo) && presenter.checkValidEmail(register.email)
                    && presenter.checkPassMatch(register.pass, pass2)){
            presenter.signUp(register)
        }
    }

    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}