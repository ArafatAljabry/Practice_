package com.example

import com.example.model.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.SerializationException

fun Application.configureRouting() {
    routing {
        staticResources("static", "static")

        route("/tasks") {

            get{
                val tasks = TaskRepository.allTasks()
                call.respond(tasks)
            }
            get("/byName/{taskname}"){
                val taskName = call.parameters["taskname"]
                if(taskName == null)
                {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                val task = TaskRepository.taskByName(taskName)
                if(task == null)
                {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(task)
            }

            get("/bypriority/{priority}"){
                val priority = call.parameters["priority"]
                if(priority == null)
                {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                try {
                    val prioToText = Priority.valueOf(priority)
                    val tasks = TaskRepository.taskByPriority(prioToText)
                    if(tasks.isEmpty())
                    {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }
                    call.respond(tasks)
                }catch (e: IllegalArgumentException){
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            post{
                try {
                    val task = call.receive<Task>()
                    TaskRepository.addTask(task)
                    call.respond(HttpStatusCode.Created)
                }catch(e: IllegalStateException){
                    call.respond(HttpStatusCode.BadRequest)
                }catch(e: SerializationException){
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            delete("/{taskname}"){
                try{
                    val taskname = call.parameters["taskname"]
                    if(taskname == null)
                    {
                        call.respond(HttpStatusCode.BadRequest)
                        return@delete
                    }
                    if(TaskRepository.removeTask(taskname)){
                        call.respond(HttpStatusCode.NoContent)
                    }
                }catch (e: IllegalStateException){
                    call.respond(HttpStatusCode.BadRequest)
                }catch(e: SerializationException){
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}