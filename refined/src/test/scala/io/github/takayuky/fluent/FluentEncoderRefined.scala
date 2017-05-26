package io.github.takayuky.fluent

import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.numeric.Interval.Closed
import eu.timepit.refined.numeric.Positive
import io.github.takayuky.fluent.basic._
import io.github.takayuky.fluent.refined._
import org.fluentd.logger.scala.FluentLogger
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import scala.collection.Map
import shapeless.Nat.{_0, _1}

class FluentEncoderRefined extends FlatSpec with MockFactory {

  class Setup {
    val underlyingMock = mock[FluentLogger]
    val logger = TypesafeFluentLogger(underlyingMock)

    val label = "label"
    val time = 0L
  }

  behavior of "Refined instances"

  val int: Int Refined Positive = 1
  val double: Double Refined Closed[_0, _1] = 0.5
  val string: String Refined NonEmpty = "hello"

  it should "correctly encode refined instances" in new Setup {
    val data = Map[String, FluentAcceptable](
      "Int" -> int,
      "Double" -> double,
      "String" -> string,
      "Option" -> Some(int),
      "None" -> (None: Option[String Refined NonEmpty])
    )

    (underlyingMock.log(_: String, _: Map[String, Any], _:Long)).expects(label, data.mapValues(_.v), time)

    logger.log(label, data, time)
  }
}
