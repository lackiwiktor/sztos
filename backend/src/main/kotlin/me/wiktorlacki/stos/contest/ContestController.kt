package me.wiktorlacki.stos.contest

import jakarta.validation.Valid
import me.wiktorlacki.stos.user.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/contests")
class ContestController(
    private val userService: UserService, private val contestService: ContestService
) {

    @GetMapping
    fun all(authentication: Authentication): ResponseEntity<List<ContestDTO>> {
        val user = userService.getByUsername(authentication.name)
        val response = contestService.getAllContextAware(user)

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long, authentication: Authentication): ResponseEntity<DetailedContestDTO> {
        val user = userService.getByUsername(authentication.name)
        val response = contestService.getContextAwareById(user, id)

        return ResponseEntity.ok(response)
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    fun create(
        @RequestBody @Valid request: ContestCreateRequest, authentication: Authentication
    ): ResponseEntity<ContestCreateResponse> {
        val response = contestService.create(authentication.name, request)

        return ResponseEntity.ok(response)
    }

    @PostMapping("/{id}/students")
    @PreAuthorize("hasRole('TEACHER')")
    fun add(
        @PathVariable id: Long, @RequestBody @Valid request: ContestAddUserRequest, authentication: Authentication
    ): ResponseEntity<Void> {
        contestService.addUserToContest(id, authentication.name, request)

        return ResponseEntity.accepted().build()
    }

    @DeleteMapping("/{id}/students")
    @PreAuthorize("hasRole('TEACHER')")
    fun remove(
        @PathVariable id: Long, @RequestBody @Valid request: ContestRemoveUserRequest, authentication: Authentication
    ): ResponseEntity<Void> {
        contestService.removeUserFromContest(id, authentication.name, request)

        return ResponseEntity.noContent().build()
    }

}