package com.example.todolist.db

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MyTypeConverter {

    @TypeConverter
    fun fromLocalDate(value: LocalDate): String {
        return value.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @TypeConverter
    fun toLocalDate(value: String): LocalDate {
        return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE)
    }

}