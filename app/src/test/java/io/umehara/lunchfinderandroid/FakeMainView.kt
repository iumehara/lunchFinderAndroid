package io.umehara.lunchfinderandroid

class FakeMainView: MainView {
    var setRowArguments: List<Restaurant>? = null
    override fun setRow(restaurants: List<Restaurant>) {
        setRowArguments = restaurants
    }
}