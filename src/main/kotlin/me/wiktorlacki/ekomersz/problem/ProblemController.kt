package me.wiktorlacki.ekomersz.problem

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/problems")
class ProblemController(private val problemService: ProblemService) {


    @GetMapping("/{problemId}")
    fun get(@PathVariable problemId: Long, authentication: Authentication): ResponseEntity<DetailedProblemDTO> {
        val response = problemService.getContextAwareByProblemId(authentication.name, problemId)

        return ResponseEntity.ok(response)
    }
}