package stringAddCalculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import stringAddCalculator.customDelimiter.DoubleAsteriskDelimiter
import stringAddCalculator.customDelimiter.DoubleSlashAndNewLineDelimiter

class StringAddCalculatorTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "1,2:3 = 6",
            "3:2:6 = 11",
            "1,5,6 = 12",
        ],
        delimiter = '=',
    )
    fun `구분자를 기준으로 분리한 숫자의 합을 반환한다`(expression: String, expect: Int) {
        val calculator = StringAddCalculator(expression)

        calculator.calculate() shouldBe expect
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "//;\n1;2;3",
            "//>\n3,2>1",
            "//@\n1@2,3",
        ],
    )
    fun `DoubleSlash 와 NewLine 사이에 오는 문자를 커스텀 구분자로 가질 수 있다`(expression: String) {
        val customDelimiters = listOf(DoubleSlashAndNewLineDelimiter())
        val calculator = StringAddCalculator(expression, customDelimiters)

        calculator.calculate() shouldBe 6
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "1;3;6**; = 10",
            "3,5>6**> = 14",
            "7@2,3**@ = 12",
        ],
        delimiter = '=',
    )
    fun `DoubleAsterisk 뒤에 오는 문자를 커스텀 구분자로 가질 수 있다`(expression: String, expect: Int) {
        val customDelimiters = listOf(DoubleAsteriskDelimiter())
        val calculator = StringAddCalculator(expression, customDelimiters)

        calculator.calculate() shouldBe expect
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "1,2,-4 = 7",
            "?,2;4 = 3",
        ],
        delimiter = '=',
    )
    fun `숫자가 아니거나 음수가 들어오면 에러가 발생한다`(expression: String, expect: Int) {
        assertThrows<RuntimeException>(ErrorCode.NOT_POSITIVE_NUMBER_ERROR.msg) {
            StringAddCalculator(
                expression,
            )
        }
    }
}