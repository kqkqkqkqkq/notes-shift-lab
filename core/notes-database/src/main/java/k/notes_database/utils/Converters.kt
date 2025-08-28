package k.notes_database.utils

import androidx.room.TypeConverter
import k.notes_database.models.PriorityDbo

class Converters {

    @TypeConverter
    fun fromPriority(priority: PriorityDbo?): String? = priority?.name

    @TypeConverter
    fun toPriority(value: String?): PriorityDbo? = value?.let { PriorityDbo.valueOf(it) }
}