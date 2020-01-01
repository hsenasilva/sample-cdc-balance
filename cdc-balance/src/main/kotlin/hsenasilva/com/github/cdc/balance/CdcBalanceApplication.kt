package hsenasilva.com.github.cdc.balance

import cdc.autoconfigure.CdcAutoConfiguration
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@AutoConfigureBefore(CdcAutoConfiguration::class)
class CdcBalanceApplication

fun main(args: Array<String>) {
	runApplication<CdcBalanceApplication>(*args)
}
