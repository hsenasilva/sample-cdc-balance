package hsenasilva.com.github.cdc.balance

import org.apache.kafka.connect.source.SourceRecord
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import java.util.*
import java.util.function.Consumer
import java.util.function.Function


@SpringBootConfiguration
@EnableAutoConfiguration
class ChangeDataConsumer(private val valueSerializer: Function<SourceRecord, ByteArray>?,
                          private val keySerializer: Function<SourceRecord, ByteArray>?): Consumer<SourceRecord> {


    var keyValue: Map<Any, Any> = HashMap()
    var recordList: List<SourceRecord> = ArrayList()

    override fun accept(sourceRecord: SourceRecord) {

        recordList.plus(sourceRecord)
        val payload = valueSerializer?.apply(sourceRecord)
        val key = keySerializer?.apply(sourceRecord)
        keyValue.plus(key to payload)

        println("[CDC Event]: $sourceRecord")
    }

}