package io.github.takayuky.examples

import io.github.takayuky.fluent._
import io.github.takayuky.fluent.basic._

object BasicExample {
  def main(args: Array[String]): Unit = {
    val tag = "basic_example"
    val host = "localhost"
    val port = 24224

    val logger = TypesafeFluentLoggerFactory.getLogger(tag, host, port)

    logger.log("success", Map(
      "Boolean" -> true,
      "Int" -> 1,
      "Long" -> 1L,
      "Double" -> 1d,
      "Float" -> 1f,
      "String" -> "hello",
      "BigInt" -> BigInt(1),
      "BigDecimal" -> BigDecimal(1),
      "Option" -> Some(1)
    ), 0L)
  }
}
