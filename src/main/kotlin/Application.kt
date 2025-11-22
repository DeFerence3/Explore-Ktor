package com.diffy

import com.diffy.routings.configureApplicationBusinessRoute
import com.diffy.routings.configureJsonSaver
import com.diffy.routings.configureSwagger
import di.configureDependencyInjection
import io.ktor.server.application.*
import routings.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDependencyInjection()
    configureRoleRouting()
    configureUser()
    configureFileRouting()
    configureExceptionRouting()
    configureSwagger()
    configureApplicationBusinessRoute()
    configureJsonSaver()
}