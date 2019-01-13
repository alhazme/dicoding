package me.alhaz.submission2.view.base

interface BaseView<T> {

    // For showing alert
    fun showAlert(message: String?)

    // For showing progress dialog
    fun showProgress()

    // For hiding progress dialog
    fun hideProgress()

}