package com.example.androidproject.chatdetail

data class ChatItem (
    val time: String,
    val senderId: String,
    val message: String
){
    constructor():this("","","")
}