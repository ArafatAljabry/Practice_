package com.example.model

import kotlinx.serialization.Serializable

enum class Priority {
    Low,
    Medium,
    High,
    Vital
}
@Serializable
data class Task(
    val name: String,
    val description: String,
    val priority: Priority = Priority.Low
)

fun Task.taskAsRow() =
    """
        <td>$name</td><td>$description</td><td>$priority</td>
    """.trimIndent()

fun List<Task>.taskAsTable() = this.joinToString(
    prefix    = "<table rules=\"all\">",
    postfix   = "</table>",
    separator = "\n",
    transform = Task::taskAsRow
)