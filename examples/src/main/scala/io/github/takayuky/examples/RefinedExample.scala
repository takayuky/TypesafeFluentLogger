package io.github.takayuky.examples

import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.numeric._
import eu.timepit.refined.numeric.Interval.Closed
import io.github.takayuky.fluent._
import io.github.takayuky.fluent.refined._
import shapeless.Nat._

object RefinedExample {
  def main(args: Array[String]): Unit = {
    val tag = "basic_example"
    val host = "localhost"
    val port = 24224

    val logger = TypesafeFluentLoggerFactory.getLogger(tag, host, port)

    val int: Int Refined Positive = 1
    val double: Double Refined Closed[_0, _1] = 0.5
    val string: String Refined NonEmpty = "hello"

    logger.log("success", Map(
      "Int" -> int,
      "Double" -> double,
      "String" -> string,
      "Option" -> Option(int)
    ), 0L)
  }
}
