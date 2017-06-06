package io.github.takayuky.fluent

import io.github.takayuky.fluent.basic._
import org.fluentd.logger.scala.FluentLogger
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import scala.collection.Map

class FluentEncoderBasicSpec extends FlatSpec with MockFactory {

  class Setup {
    val underlyingMock = mock[FluentLogger]
    val logger = TypesafeFluentLogger(underlyingMock)

    val label = "label"
    val time = 0L
  }

  behavior of "Basic instances"

  it should "correctly encode basic instances" in new Setup {
    val data = Map[String, FluentAcceptable](
      "Boolean" -> true,
      "Int" -> 1,
      "Long" -> 1L,
      "Double" -> 1d,
      "Float" -> 1f,
      "String" -> "hello",
      "BigInt" -> BigInt(1),
      "BigDecimal" -> BigDecimal(1),
      "Some" -> Some(1),
      "None" -> (None: Option[String])
    )
    (underlyingMock.log(_: String, _: Map[String, Any], _:Long)).expects(label, data.mapValues(_.v), time)

    logger.log(label, data, time)
  }
}
