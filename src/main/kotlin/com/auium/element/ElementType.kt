package com.auium.element

enum class ElementType(val completeType: String) {

    Any("XCUIElementTypeAny"),
    Cell("XCUIElementTypeCell"),
    Text("XCUIElementTypeStaticText"),
    Input("XCUIElementTypeTextField"),
    Button("XCUIElementTypeButton"),
    Image("XCUIElementTypeImage"),
    Other("XCUIElementTypeOther"),

}
