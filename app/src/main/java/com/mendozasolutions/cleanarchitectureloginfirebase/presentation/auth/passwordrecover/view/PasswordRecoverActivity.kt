package com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.passwordrecover.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mendozasolutions.cleanarchitectureloginfirebase.CleanFirebaseLoginApp
import com.mendozasolutions.cleanarchitectureloginfirebase.R
import com.mendozasolutions.cleanarchitectureloginfirebase.base.BaseActivity
import com.mendozasolutions.cleanarchitectureloginfirebase.databinding.ActivityPasswordRecoverBinding
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.passwordrecoverinteractor.PasswordRecoverInteractor
import com.mendozasolutions.cleanarchitectureloginfirebase.domain.auth.interactor.passwordrecoverinteractor.PasswordRecoverInteractorImpl
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.passwordrecover.PasswordRecoverContract
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.auth.passwordrecover.presenter.PasswordRecoverPresenter
import javax.inject.Inject

class PasswordRecoverActivity : BaseActivity(), PasswordRecoverContract.PasswordRecoverView {
    @Inject lateinit var presenter : PasswordRecoverPresenter
    private lateinit var mBinding : ActivityPasswordRecoverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as CleanFirebaseLoginApp).getAppComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        mBinding = ActivityPasswordRecoverBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        presenter.attachView(this)
        mBinding.btnRecoverEmail.setOnClickListener { sendEmailRecover() }

    }


    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        mBinding.btnRecoverEmail.visibility = View.GONE
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mBinding.progressBar.visibility = View.GONE
        mBinding.btnRecoverEmail.visibility = View.VISIBLE
    }

    override fun showAuthError(message: String?) {
        toast(message!!)
    }


    override fun showMessageSend() {
        toast(getString(R.string.send_message_email))
    }

    override fun sendEmailRecover() {
        val email = mBinding.etRecoverEmail.text.toString().trim()

        when {
            presenter.checkEmptyEmail(email) -> {
                mBinding.tilRecoverEmail.error = getString(R.string.message_empty_email)
            }
            presenter.checkValidEmail(email) -> {
                mBinding.tilRecoverEmail.error = getString(R.string.message_error_email)
            }
            else -> {
                mBinding.tilRecoverEmail.error = null
                presenter.sendPasswordRecover(email)
            }
        }

    }

    override fun previousActivity() {
        onBackPressed()
    }


}