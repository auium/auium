package com.auium.element

import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

interface IElement {

    /**
     * 通过坐标点击元素
     */
    fun tap()

    /**
     * 获取元素文本内容
     */
    fun text(): String

    /**
     * 获取文本元素大小
     */
    fun size(): Dimension

    /**
     * 获取元素的矩形信息
     */
    fun rect(): Rectangle

    /**
     * 获取元素的位置信息
     */
    fun location(): Point

    /**
     * 获取元素的中心位置
     */
    fun center(): Point

    /**
     * 清空元素内容
     */
    fun clear()

    /**
     * 输入内容
     * @param keys 需要输入的内容
     */
    fun sendKeys(keys: String)

}