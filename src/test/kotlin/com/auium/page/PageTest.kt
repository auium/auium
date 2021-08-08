package com.auium.page

import kotlin.test.Test

class PageTest {

    private val page by lazy { HomePage() }

    @Test
    fun homePageTest() {
        page.category()?.tap()
        page.me()?.tap()
        page.setting()?.tap()
    }

}