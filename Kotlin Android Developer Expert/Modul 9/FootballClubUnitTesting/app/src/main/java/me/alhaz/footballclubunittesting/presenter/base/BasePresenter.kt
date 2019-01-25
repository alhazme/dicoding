package me.alhaz.footballclubunittesting.presenter.base

interface BasePresenter<T> {

    // Inject view of presenter
    fun takeView(view: T)

    // Delete data view
    fun dropView()

}