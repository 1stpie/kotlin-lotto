package lottery.domain

object RandomNumbersGenerator : NumbersGenerator {
    private const val MIN_INDEX = 0

    override fun generate(min: Int, max: Int, size: Int): List<Int> {
        val numbers = (min..max).map { it }
        return numbers.shuffled().subList(MIN_INDEX, size).sorted()
    }
}