package com.auium.element

enum class ElementType(val completeType: String) {

    Any("XCUIElementTypeAny"),
    Cell("XCUIElementTypeCell"),
    Text("XCUIElementTypeStaticText"),
    Link("XCUIElementTypeLink"),
    Input("XCUIElementTypeTextField"),
    Button("XCUIElementTypeButton"),
    Image("XCUIElementTypeImage"),
    Other("XCUIElementTypeOther"),
    Switch("XCUIElementTypeSwitch"),
    WebView("XCUIElementTypeWebView"),

}
