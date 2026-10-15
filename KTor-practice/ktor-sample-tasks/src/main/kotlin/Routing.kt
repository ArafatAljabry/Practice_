package com.example
import com.example.model.*

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.http.content.staticResources
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticResources("/task-ui","task-ui")
route("/tasks"){

            post{
                val fromContent = call.receiveParameters()

                val params = Triple(
                    fromContent["name"] ?: "",
                    fromContent["priority"] ?: "",
                    fromContent["description"] ?: ""
                )

                if(params.toList().any{ it.isEmpty()})
                {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
                try{
                    val priority = Priority.valueOf(params.second)
                    TaskRepo.addTask(Task(params.first, params.third, priority))

                    call.respond(HttpStatusCode.NoContent)
                }catch(ex: IllegalArgumentException){
                    call.respond(HttpStatusCode.BadRequest)
                }catch(ex: IllegalStateException){
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            get {
                val tasks = TaskRepo.allTasks()
                call.respondText(
                    contentType = ContentType.parse("text/html"),
                    text = tasks.taskAsTable()
                )
            }

            get("/bypriority/{priority?}")
            {
                val priorityAsText = call.parameters["priority"]
                if(priorityAsText == null)
                {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                try{
                    val priority = Priority.valueOf(priorityAsText)
                    val tasks = TaskRepo.tasksByPriority(priority)

                    if(tasks.isEmpty())
                    {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }

                    call.respondText(
                        contentType = ContentType.parse("text/html"),
                        text = tasks.taskAsTable()
                    )
                }catch(ex: IllegalArgumentException)
                {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            get("/byname/{name?}")
            {
                val start = System.nanoTime()
                val name = call.parameters["name"]
                val task = TaskRepo.taskByName(name?:"")
                println("Lookup took ${(System.nanoTime() - start) / 1_000_000.0} ms")

                if(task == null)
                {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                try {
                    call.respondText(
                        contentType = ContentType.parse("text/html"),
                        text = task.taskAsRow()
                    )
                    call.respond(HttpStatusCode.NoContent)
                }catch(ex: IllegalArgumentException)
                {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}
