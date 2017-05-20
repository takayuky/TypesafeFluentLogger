package io.github.takayuky.examples

import io.github.takayuky.fluent._
import io.github.takayuky.fluent.list._

object ListExample {
  def main(args: Array[String]): Unit = {
    val tag = "basic_example"
    val host = "localhost"
    val port = 24224

    val logger = TypesafeFluentLoggerFactory.getLogger(tag, host, port)

    logger.log("success", Map(
      "List" -> List(1,2,3),
      "Option" -> Some(List("hello"))
    ), 0L)
  }
}
