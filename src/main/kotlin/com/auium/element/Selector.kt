package com.auium.element

/**
 * 定位选择器
 * @param using 定位方式
 */
sealed class Selector(val using: String) {

    data class Id(val id: String) : Selector("id")
    data class Name(val name: String) : Selector("name")
    data class Type(val type: String) : Selector("class name")
    data class XPath(val xpath: String) : Selector("xpath")
    data class Predicate(val predicate: String) : Selector("predicate string")
    data class ClassChain(val classChain: String) : Selector("class chain")
    data class NameContains(val nameContains: String) : Selector("class chain")

}
