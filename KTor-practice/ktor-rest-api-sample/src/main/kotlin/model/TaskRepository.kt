package com.example.model

object TaskRepository{
    private val tasks = mutableListOf(
        Task("Cleaning", "Clean the house", Priority.Low),
        Task("Grocery Shopping", "Buy groceries for the week", Priority.Medium),
        Task("Shopping", "Buy groceries for the week", Priority.Medium),
        Task("Laundry", "Do laundry", Priority.High)
    )

    fun allTasks(): List<Task> = tasks.toList()

    fun taskByPriority(priority: Priority): List<Task> = tasks.filter { it.priority == priority }
    fun taskByName(name: String): Task? = tasks.find{it.name.equals(name,ignoreCase = true)}

    fun addTask(task: Task){
        if(taskByName(task.name) != null)
        {
            throw IllegalStateException("Task with name ${task.name} already exists")
        }
        tasks.add(task)
    }
    fun removeTask(taskName: String): Boolean{
        return tasks.removeIf{ it.name == taskName }
    }
}