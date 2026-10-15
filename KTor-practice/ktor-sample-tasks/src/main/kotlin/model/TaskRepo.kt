package com.example.model


 object TaskRepo {
     val tasks = mutableListOf(
         Task("cleaning", "Clean the house", Priority.Low),
         Task("gardening", "Mow the lawn", Priority.Medium),
         Task("shopping", "Buy the groceries", Priority.High),
         Task("painting", "Paint the fence", Priority.Medium)
     )
     fun allTasks(): List<Task> = tasks.toList()

     fun tasksByPriority(priority: Priority) = tasks.filter{it.priority == priority}

     fun taskByName(name: String) = tasks.find{it.name.equals(name,ignoreCase = true)}

     fun addTask(task: Task) {
         if(taskByName(task.name) != null) {
             throw IllegalStateException("Task with name ${task.name} already exists")
         }
         tasks.add(task)
     }
 }

