package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class Privilege(@SerializedName("st")
                     val st: Int = 0,
                     @SerializedName("flag")
                     val flag: Int = 0,
                     @SerializedName("subp")
                     val subp: Int = 0,
                     @SerializedName("fl")
                     val fl: Int = 0,
                     @SerializedName("fee")
                     val fee: Int = 0,
                     @SerializedName("dl")
                     val dl: Int = 0,
                     @SerializedName("cp")
                     val cp: Int = 0,
                     @SerializedName("preSell")
                     val preSell: Boolean = false,
                     @SerializedName("cs")
                     val cs: Boolean = false,
                     @SerializedName("toast")
                     val toast: Boolean = false,
                     @SerializedName("maxbr")
                     val maxbr: Int = 0,
                     @SerializedName("id")
                     val id: Int = 0,
                     @SerializedName("pl")
                     val pl: Int = 0,
                     @SerializedName("sp")
                     val sp: Int = 0,
                     @SerializedName("payed")
                     val payed: Int = 0)