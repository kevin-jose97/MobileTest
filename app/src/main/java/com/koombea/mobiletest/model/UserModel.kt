package com.koombea.mobiletest.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName( "uid") var userId : String,
    @SerializedName("name") var userName: String,
    @SerializedName("email") var userEmail: String,
    @SerializedName("profile_pic") var userProfilePic: String,
    @SerializedName("posts") var userPosts:List<PostModel>)