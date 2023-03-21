package com.example.jetmusic.data

class PlayList(
    private val urls: List<String>,
    var repeatMode: RepeatMode = RepeatMode.NONE
) {
    private val indices: MutableList<Int> = urls.indices.toMutableList()
    private var sorted: Boolean = true
    var cursor = 0

    fun next() {
        if (this.indices.size > 0) {
            this.moveCursor(1)
        }
    }

    fun prev() {
        if (this.indices.size > 0) {
            this.moveCursor(-1)
        }
    }

    private fun moveCursor(step: Int) {
        this.cursor = when (this.repeatMode) {
            RepeatMode.NONE -> this.cursor + step
            RepeatMode.PLAYLIST -> (this.cursor + step) % this.indices.size
            RepeatMode.ONE -> this.cursor
        }
    }

    fun get(): String? {
        return when {
            this.cursor < 0 -> null
            this.cursor >= this.indices.size -> null
            else -> this.urls[this.indices[this.cursor]]
        }
    }

    fun sort() {
        this.indices.sort()
        this.sorted = true
    }

    fun shuffle() {
        this.indices.shuffle()
        this.sorted = false
    }

    fun isSorted(): Boolean {
        return this.sorted
    }

    fun list(): Array<String> {
        return this.indices.map { this.urls[it] }.toTypedArray()
    }

    fun size(): Int {
        return this.urls.size
    }
}