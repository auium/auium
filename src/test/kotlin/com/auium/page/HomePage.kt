package com.auium.page

import com.auium.element.Selector
import com.auium.pageobject.Page

class HomePage : Page("home","首页") {

    fun category() = find(Selector.NameContains("Category"))
    fun me() = find(Selector.Name("Me"))
    fun setting() = find(Selector.Name("Setting"))

}