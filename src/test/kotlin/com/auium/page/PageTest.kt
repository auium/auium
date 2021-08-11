package com.auium.page

import kotlin.test.Test

class PageTest {

    private val homePage by lazy { HomePage() }
    private val settingPage by lazy { SettingPage() }

    @Test
    fun homePageTest() {
        homePage.category()?.tap()
        homePage.me()?.tap()
        homePage.setting()?.text()
        homePage.setting()?.tap()
        settingPage.address()?.tap()
        settingPage.add()?.tap()
        settingPage.firstName()?.sendKeys("Hello")
        settingPage.lastName()?.sendKeys("World!")
    }

}