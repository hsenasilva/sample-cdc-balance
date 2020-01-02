package hsenasilva.com.github.balance.repository

import hsenasilva.com.github.balance.domain.Balance
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BalanceRepository : CrudRepository<Balance, Long> {

    fun findByCustomerId(customerId: Long): Optional<Balance>

}