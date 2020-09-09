package com.kgql

import com.expedia.graphql.annotations.GraphQLDescription
import org.koin.dsl.module
import reactor.core.publisher.Flux
import java.time.Duration
import kotlin.random.Random

class UserRepository {
    fun getUsername(id: Int): String  {
        return when (id) {
            1 -> "admin"
            else -> "user"
        }
    }
}

val userModule = module {
    single { UserRepository() }
}

class UserQuery(private val repository: UserRepository) {
    fun username(id: Int): String {
        return repository.getUsername(id)
    }
}

data class Count(val value: Int, val value2: Int)

class CounterSubscription {
    @GraphQLDescription("Returns a random number every second")
    fun counter(): Flux<Count> = Flux.interval(Duration.ofSeconds(1)).map { Count(Random.nextInt(), Random.nextInt()) }
}
