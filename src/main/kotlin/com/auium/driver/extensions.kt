package com.auium.driver

import com.auium.element.Element
import com.auium.element.ElementType
import com.auium.element.Selector
import com.auium.remote.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * 用于存储Session和Element信息
 */
val wildcards: ConcurrentMap<Wildcard, String> by lazy { ConcurrentHashMap() }

/**
 * 读取WebDriverAgent服务地址
 */
var driverUrl: String = System.getProperty("driverUrl", "http://localhost:8100")

/**
 * 全局启动
 */
val driver by lazy { Driver(driverUrl) }

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
 * 查找元素
 * @param selector 定位信息
 * @return Elements信息
 */
fun Driver.findElements(selector: Selector): List<Element> {
    return execute(MobileCommand.FIND_ELEMENTS, wildcards, selector.toRequest()).convertElement()
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
        is Selector.Name -> SelectorRequest(selector.using, selector.name)
        is Selector.Type -> SelectorRequest(selector.using, selector.type)
        is Selector.XPath -> SelectorRequest(selector.using, selector.xpath)
        is Selector.Predicate -> SelectorRequest(selector.using, selector.predicate)
        is Selector.ClassChain -> SelectorRequest(selector.using, selector.classChain)
        is Selector.NameContains -> SelectorRequest(selector.using, selector.nameContains.genClassChain())
    }
}

private fun String.genClassChain(): String {
    return "**/${ElementType.Any.completeType}[`name CONTAINS[cd] \"${this}\"`]"
}