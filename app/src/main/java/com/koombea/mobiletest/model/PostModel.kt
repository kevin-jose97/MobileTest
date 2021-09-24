package com.koombea.mobiletest.model

import com.google.gson.annotations.SerializedName

data class PostModel (
    @SerializedName("id") var postId : Int,
    @SerializedName("date") var postDate : String,
    @SerializedName("pics") var postPics : List<String>)