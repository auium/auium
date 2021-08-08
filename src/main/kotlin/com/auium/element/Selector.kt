package com.auium.element


sealed class Selector(val using: String) {

    data class Id(val id: String) : Selector("id")
    data class Name(val name: String) : Selector("name")
    data class Type(val type: String) : Selector("class name")
    data class XPath(val xpath: String) : Selector("xpath")
    data class Predicate(val predicate: String) : Selector("predicate string")
    data class ClassChain(val classChain: String) : Selector("class chain")
    data class NameContains(val nameContains: String): Selector("class chain")

}
