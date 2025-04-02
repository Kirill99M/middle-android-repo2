package ru.yandex.praktikumchatapp.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retryWhen

class ChatRepository(
    private val api: ChatApi = ChatApi()
) {
    fun getReplyMessage(currentDelay: Long = 100L): Flow<String> {
        var currentDelayInternal = currentDelay
        return api.getReply().retryWhen { _, attempt ->
            delay(currentDelayInternal)
            currentDelayInternal *= (attempt + 1)
            true
        }
    }
}