package com.auium.driver

import com.auium.element.Element
import com.auium.element.Selector
import com.auium.remote.MobileCommand
import com.auium.remote.SelectorRequest
import com.auium.remote.Wildcard
import com.auium.remote.convert
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * 用于存储Session和Element信息
 */
val wildcards: ConcurrentMap<Wildcard, String> by lazy { ConcurrentHashMap() }

/**
 * 查找元素
 * @param selector 定位信息
 * @return Element信息
 */
fun Driver.findElement(selector: Selector): Element? {
    val element = execute(MobileCommand.FIND_ELEMENT, wildcards, selector.toRequest()).convert<Element>()
    return if (element?.elementId.isNullOrBlank()) null else element
}

/**
 * 输入内容
 * @param keys 需要输入字符串
 */
fun Driver.sendKeys(keys: String) {
    execute(MobileCommand.INPUT, wildcards, mapOf("value" to keys.splitToSequence("")))
}

/**
 * 获取页面源码
 * @return String Str
 */
fun Driver.source(): String {
    val response = execute(MobileCommand.GET_PAGE_SOURCE)
    return "${response.value}"
}

/**
 * 将定位方式转换为请求参数
 * @return SelectorRequest
 */
fun Selector.toRequest(): SelectorRequest {
    return when (val selector = this) {
        is Selector.Id -> SelectorRequest(selector.using, selector.id)
        is Selector.Type -> SelectorRequest(selector.using, selector.type)
        is Selector.XPath -> SelectorRequest(selector.using, selector.xpath)
        is Selector.Predicate -> SelectorRequest(selector.using, selector.predicate)
        is Selector.ClassChain -> SelectorRequest(selector.using, selector.classChain)
    }
}