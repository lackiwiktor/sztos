package me.wiktorlacki.ekomersz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EkomerszApplication

fun main(args: Array<String>) {
    runApplication<EkomerszApplication>(*args)
}
