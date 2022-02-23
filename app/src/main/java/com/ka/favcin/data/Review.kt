package com.ka.favcin.data

class Review (author: String?, content: String?){

    private var author: String? = null
    private var content: String? = null

    fun Review(author: String?, content: String?) {
        this.author = author
        this.content = content
    }

    fun getAuthor(): String? {
        return author
    }

    fun setAuthor(author: String?) {
        this.author = author
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }
}