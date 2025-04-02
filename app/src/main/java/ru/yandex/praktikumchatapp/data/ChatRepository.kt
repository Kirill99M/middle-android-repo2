package ru.yandex.praktikumchatapp.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retryWhen

class ChatRepository(
    private val api: ChatApi = ChatApi()
) {
    fun getReplyMessage(initialDelay: Long = 100L): Flow<String> {
        var currentDelay = initialDelay
        return api.getReply().retryWhen { _, attempt ->
            delay(currentDelay)
            currentDelay *= (attempt + 1)
            true
        }
    }
}