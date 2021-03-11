package lotto.domain.ticket

import lotto.domain.LottoNumber
import lotto.domain.result.LottoResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LottoTicketsTest {
    @Test
    fun `로또 티켓 뭉치와 우승 티켓을 비교하여 결과를 산출한다`() {
        //given
        val expectResult = LottoResult()
        expectResult.add(WinningBoard.SIX)
        expectResult.add(WinningBoard.THREE)

        val winningLottoTicket = WinningLottoTicket(listOf(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6)
        ))

        val lottoTickets = LottoTickets(listOf(
            LottoTicket(
                setOf(
                    LottoNumber.of(1),
                    LottoNumber.of(2),
                    LottoNumber.of(3),
                    LottoNumber.of(4),
                    LottoNumber.of(5),
                    LottoNumber.of(6)
                )
            ),
            LottoTicket(
                setOf(
                    LottoNumber.of(1),
                    LottoNumber.of(2),
                    LottoNumber.of(3),
                    LottoNumber.of(43),
                    LottoNumber.of(44),
                    LottoNumber.of(45)
                )
            )
        ))

        //when
        val result = lottoTickets.compare(winningLottoTicket)

        //then
        assertThat(result).isEqualTo(expectResult)
    }
}