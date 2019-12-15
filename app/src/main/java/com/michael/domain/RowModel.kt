package com.michael.domain

import com.google.gson.annotations.SerializedName

class RowModel {
    var title: String? = null
    var description: String? = null
    @SerializedName("imageHref")
    var image_href: String? = null
} 