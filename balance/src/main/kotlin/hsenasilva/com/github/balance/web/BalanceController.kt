package hsenasilva.com.github.balance.web

import hsenasilva.com.github.balance.domain.Balance
import hsenasilva.com.github.balance.repository.BalanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/balances")
class BalanceController(@Autowired val balanceRepository: BalanceRepository) {


    @GetMapping
    fun getAllBalances(): MutableIterable<Balance> {
        return this.balanceRepository.findAll()
    }

    @PostMapping
    fun createBalance(@Valid @RequestBody parameter: BalanceParameter): Balance {

        this.balanceRepository.findByCustomerId(parameter.customerId)
                .ifPresent { throw ResponseStatusException(HttpStatus.CONFLICT,
                        "This customer already exists, try to use the PUT method: /sample/api/balances/customers/{id}")
                }

        return this.balanceRepository.save(
                Balance(
                        customerId = parameter.customerId,
                        balance = parameter.balance,
                        updatedAt = LocalDateTime.now()
                )
        )
    }

    @PutMapping("/customers/{id}")
    fun updateBalance(@PathVariable(value = "id") customerId: Long, @Valid @RequestBody parameter: BalanceParameter): ResponseEntity<Balance> {

        val balance = this.balanceRepository.findByCustomerId(customerId)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found") }

        return ResponseEntity.ok(
                this.balanceRepository.save(
                        Balance(
                                id = balance.id,
                                customerId = parameter.customerId,
                                balance = parameter.balance,
                                updatedAt = LocalDateTime.now()
                        )
                )
        )
    }

    @DeleteMapping("/customers/{id}")
    fun deleteBalance(@PathVariable(value = "id") customerId: Long): ResponseEntity<Any> {

        this.balanceRepository.findByCustomerId(customerId)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found") }

        this.balanceRepository.deleteAll()

        return ResponseEntity.noContent().build()
    }

}

data class BalanceParameter(val customerId: Long, val balance: BigDecimal) : Serializable