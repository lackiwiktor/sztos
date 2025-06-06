package me.wiktorlacki.stos.test

import jakarta.persistence.*
import me.wiktorlacki.stos.problem.Problem
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@Table(name = "tests")
@EntityListeners(AuditingEntityListener::class)
class Test(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @ManyToOne(optional = false)
    @JoinColumn(name = "problem_id")
    val problem: Problem,

    @Column(nullable = false, updatable = false)
    val points: Int,

    @Column(nullable = false, columnDefinition = "TEXT")
    val input: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    val output: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private var createdAt: Instant? = null,

    @Column(name = "updated_at", nullable = false, updatable = false)
    @LastModifiedDate
    private var updatedAt: Instant? = null
)