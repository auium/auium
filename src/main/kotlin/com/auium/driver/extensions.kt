package com.auium.driver

import com.auium.element.Element
import com.auium.element.Selector
import com.auium.remote.MobileCommand
import com.auium.remote.SelectorRequest
import com.auium.remote.Wildcard
import com.auium.remote.convert
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

val wildcards: ConcurrentMap<Wildcard, String> by lazy { ConcurrentHashMap() }

fun Driver.findElement(selector: Selector): Element? {
    val parameters = selector.toRequest()
    val element = execute(MobileCommand.FIND_ELEMENT, wildcards, parameters).convert<Element>()
    return if (element.elementId.isNullOrBlank()) null else element
}

fun Selector.toRequest(): SelectorRequest {
    return when (val selector = this) {
        is Selector.ClassChain -> SelectorRequest(selector.using, selector.classChain)
        is Selector.Predicate -> SelectorRequest(selector.using, selector.predicate)
        is Selector.XPath -> SelectorRequest(selector.using, selector.xpath)
        is Selector.Id -> SelectorRequest(selector.using, selector.id)
    }
}