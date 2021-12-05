package org.kiworkshop.learningfpinkotlin.c3

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Practice18Of3 : ShouldSpec({
    context("2 초과인 실수 n이 주어졌을 때") {
        should("n의 제곱근을 2로 나눈 값이 1보다 작아지는 수를 구한다") {
            listOf(
                SqrtAndDivideTestDate(1.0, 0.5),
                SqrtAndDivideTestDate(2.0, kotlin.math.sqrt(2.0) / 2),
                SqrtAndDivideTestDate(16.0, kotlin.math.sqrt(2.0) / 2),
            ).forEach { (n, expected) ->
                val result = sqrtAndDivide(n)

                result shouldBe expected
            }
        }
    }
})

private fun sqrtAndDivide(n: Double): Double {
    return trampoline(sqrt(n))
}

private fun sqrt(n: Double): Bounce<Double> {
    return More { divide2(kotlin.math.sqrt(n)) }
}

private fun divide2(n: Double): Bounce<Double> {
    val result = n / 2
    if (result < 1) {
        return Done(result)
    }
    return More { sqrt(result) }
}