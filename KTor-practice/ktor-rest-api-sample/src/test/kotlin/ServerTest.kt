package com.example

import com.example.model.Priority
import com.example.model.Task
import io.ktor.client.call.body

import io.ktor.client.request.get
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.*

class ServerTest {

    @Test
    fun `test root endpoint`() = testApplication {
        // loads default configuration
        configure()
        // verify server root returns 200
        assertEquals(HttpStatusCode.OK, client.get("/tasks").status)
    }
    @Test
    fun taskCanBeFoundByPriority() = testApplication{
        configure()

        val client = createClient{
            install(ContentNegotiation){
                json()
            }
        }

        val response = client.get("/tasks/by/priority/Medium")
        val results = response.body<List<Task>>()

        assertEquals(HttpStatusCode.OK, response.status)


    }
    @Test
    fun invalidPriorityProduces400() = testApplication {
        configure()

        var response = client.get("/tasks/bypriority/invalid")
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun unusedPriorityProduces404() = testApplication{
        configure()

        var response = client.get("/tasks/bypriority/vital")
        assertEquals(HttpStatusCode.NotFound,response.status)
    }

    @Test
    fun newTaskCanBeAdded() = testApplication {
        configure()
        var client = createClient{
            install(ContentNegotiation){
                json()
            }
        }

        var task = Task("Test","test task", Priority.Low)
        var response1 = client.post("/tasks"){
            header(
                HttpHeaders.ContentType,
                ContentType.Application.Json
            )
            setBody(task)
        }
    }
}
