package com.happymeerkat.motivated.domain.repository

import com.happymeerkat.motivated.data.models.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {

    fun getAllReminders(): Flow<List<Reminder>>

    suspend fun insertReminder(reminder: Reminder)

    suspend fun deleteReminder(reminder: Reminder)
}