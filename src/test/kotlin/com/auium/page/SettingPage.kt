package com.auium.page

import com.auium.element.Selector.ClassChain
import com.auium.element.Selector.NameContains
import com.auium.pageobject.Page

class SettingPage : Page("me", "个人中心") {

    fun address() = find(NameContains("Address Book"))
    fun add() = find(NameContains("Add a shipping address"))
    fun firstName() = find(ClassChain("**/XCUIElementTypeTextField[`name == \"*First Name\"`]"))
    fun lastName() = find(ClassChain("**/XCUIElementTypeTextField[`name == \"*Last Name\"`]"))

}