package io.github.takayuky.fluent

import io.github.takayuky.fluent.basic._
import io.github.takayuky.fluent.list._
import org.fluentd.logger.scala.FluentLogger
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import scala.collection.Map

class FluentEncoderListSpec extends FlatSpec with MockFactory {

  class Setup {
    val underlyingMock = mock[FluentLogger]
    val logger = TypesafeFluentLogger(underlyingMock)

    val label = "label"
    val time = 0L
  }

  behavior of "List instances"

  it should "correctly encode list instances" in new Setup {
    val data = Map[String, FluentAcceptable](
      "List" -> List(1,2,3),
      "Vector" -> Vector("hello", "world"),
      "Set" -> Set[Option[Double]](Some(1d), None)
    )
    (underlyingMock.log(_: String, _: Map[String, Any], _:Long)).expects(label, data.mapValues(_.v), time)

    logger.log(label, data, time)
  }
}
