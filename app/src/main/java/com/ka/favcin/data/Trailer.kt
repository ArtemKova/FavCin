package com.ka.favcin.data

class Trailer(key: String?, name: String?) {
    private var key: String? = null
    private var name: String? = null

    fun Trailer(key: String?, name: String?) {
        this.key = key
        this.name = name
    }

    fun getKey(): String? {
        return key
    }

    fun setKey(key: String?) {
        this.key = key
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }
}