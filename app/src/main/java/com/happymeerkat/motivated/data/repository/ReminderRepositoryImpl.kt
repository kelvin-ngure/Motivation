package com.happymeerkat.motivated.data.repository

import com.happymeerkat.motivated.data.datasources.dao.ReminderDao
import com.happymeerkat.motivated.data.models.Reminder
import com.happymeerkat.motivated.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao
): ReminderRepository {
    override fun getAllReminders(): Flow<List<Reminder>> {
        return reminderDao.getAllReminders()
    }

    override suspend fun getActiveReminders(currentTime: Long): List<Reminder> {
        return reminderDao.getActiveReminders(currentTime)
    }

    override suspend fun insertReminder(reminder: Reminder) {
        reminderDao.insertReminder(reminder)
    }

    override suspend fun deleteReminder(reminder: Reminder) {
        reminderDao.deleteReminder(reminder)
    }
}