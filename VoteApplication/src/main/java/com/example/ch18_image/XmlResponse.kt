package com.example.ch18_image

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="response")
data class XmlResponse(
    @Element
    val body : myXmlBody
)

@Xml(name="body")
data class myXmlBody(
    @Element
    val items : myXmlItems
)

@Xml(name="items")
data class myXmlItems(
    @Element
    val item : MutableList<myXmlItem>
)

@Xml(name="item")
data class myXmlItem(
    @PropertyElement
    val placeName:String?,
    @PropertyElement
    val addr:String?,
    @PropertyElement
    val floor:String?
) {
    constructor() : this(null, null, null)
}
