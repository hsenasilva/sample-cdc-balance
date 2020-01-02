package hsenasilva.com.github.balance.domain

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "TB_BALANCE", schema = "dbo")
data class Balance(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        val id: Long? = null,

        @Column(name = "id_customer", nullable = false)
        val customerId: Long,

        @Column(name = "vl_balance", nullable = false)
        val balance: BigDecimal,

        @Column(name = "dh_updated_at")
        val updatedAt: LocalDateTime

): Serializable