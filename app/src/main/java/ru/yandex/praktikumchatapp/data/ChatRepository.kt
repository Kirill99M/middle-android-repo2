package ru.yandex.praktikumchatapp.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retryWhen

class ChatRepository(
    private val api: ChatApi = ChatApi()
) {
    private var currentDelay = 100L
    fun getReplyMessage(): Flow<String> {
        return api.getReply().retryWhen { _, attempt ->
            delay(currentDelay)
            currentDelay *= (attempt + 1)
            true
        }
    }
}