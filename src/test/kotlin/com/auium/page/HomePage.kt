package com.auium.page

import com.auium.element.Selector.Name
import com.auium.element.Selector.NameContains
import com.auium.pageobject.Page

class HomePage : Page("home","首页") {

    init {
        logger.info { "初始化首页" }
    }

    fun category() = find(NameContains("Category"),1)
    fun me() = find(Name("Me"))
    fun setting() = find(Name("Setting"))

}