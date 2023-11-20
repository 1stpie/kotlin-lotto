package study.lotto.domain

class LottoGameResult private constructor(
    val statistics: Map<PrizeGrade, Int>,
    val earningsRate: Double
) {
    companion object {
        fun getResult(lottoes: BuyingLottoes, winningLotto: Lotto, bonusNumber: LottoNumber): LottoGameResult {
            val totalLottoes = lottoes.getAll()
            val statistics = buildStatistics(totalLottoes.getPrizes(winningLotto, bonusNumber))
            val earningsRate = calculateEarningsRate(
                calculateTotalPrize(statistics),
                totalLottoes.size
            )

            return LottoGameResult(statistics, earningsRate)
        }
        private fun calculateEarningsRate(totalPrize: Long, lottoCount: Int): Double {
            val totalSpent = lottoCount * Lotto.PRICE_PER_TICKET
            return totalPrize.toDouble() / totalSpent
        }

        private fun buildStatistics(matchCounts: List<PrizeGrade>): Map<PrizeGrade, Int> {
            val statistics = PrizeGrade.values()
                .filter { it >= PrizeGrade.GRADE_5 }
                .associateWith { 0 }
                .toMutableMap()

            matchCounts.forEach {
                statistics.computeIfPresent(it) { _, v -> v + 1 }
            }

            return statistics
        }

        private fun calculateTotalPrize(statistics: Map<PrizeGrade, Int>): Long {
            return statistics.entries.sumOf { (prizeGrade, matchCount) ->
                prizeGrade.prizeAmount * matchCount
            }
        }
    }
}
