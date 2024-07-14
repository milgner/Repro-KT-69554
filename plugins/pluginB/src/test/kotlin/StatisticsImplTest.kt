import arrow.core.nonEmptySetOf
import com.marcusilgner.NumericAggregates
import com.marcusilgner.StatisticsImpl
import io.kotest.core.spec.style.DescribeSpec

class ClientClassImplSpec : DescribeSpec({
    it("does foobar") {
        val statistics = StatisticsImpl()
        statistics.invoke(nonEmptySetOf(NumericAggregates.Min, NumericAggregates.Max))
    }
})