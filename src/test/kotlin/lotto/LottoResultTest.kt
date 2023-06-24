package lotto

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import lotto.domain.LottoRank
import lotto.domain.LottoResult

class LottoResultTest : FunSpec({
    test("결과에 대한 수익률을 계산해 반환한다.") {
        // given
        val lottoResult = LottoResult(mapOf(LottoRank.FOURTH to 1))
        val purchaseAmount = 14000
        val expected = 0.35

        // when
        val actual = lottoResult.calculateProfitRate(purchaseAmount)

        // then
        actual shouldBe expected
    }

})