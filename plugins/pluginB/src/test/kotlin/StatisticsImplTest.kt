import arrow.core.nonEmptySetOf
import com.marcusilgner.FoobarImpl
import io.kotest.core.spec.style.DescribeSpec

class ClientClassImplSpec : DescribeSpec({
    it("does foobar") {
        val statistics = FoobarImpl()
        statistics.invoke(nonEmptySetOf(1, 2, 3))
    }
})
