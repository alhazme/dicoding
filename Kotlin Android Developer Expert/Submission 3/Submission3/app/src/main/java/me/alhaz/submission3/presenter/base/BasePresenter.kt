package me.alhaz.submission3.presenter.base

interface BasePresenter<T> {

    // Inject view of presenter
    fun takeView(view: T)

    // Delete data view
    fun dropView()

}