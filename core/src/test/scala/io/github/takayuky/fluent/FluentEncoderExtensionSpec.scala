package io.github.takayuky.fluent

import io.github.takayuky.fluent.basic._
import org.fluentd.logger.scala.FluentLogger
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import scala.collection.Map

class FluentEncoderExtensionSpec extends FlatSpec with MockFactory {
  final case class User(id: Int, name: String)
  final case class Country(code: String, name: String)

  class Setup {
    val underlyingMock = mock[FluentLogger]
    val logger = TypesafeFluentLogger(underlyingMock)

    val label = "label"
    val time = 0L
  }

  behavior of "Custom instances"

  it should "correctly encode custom instances" in new Setup {
    implicit val fluentEncoderForUser: FluentEncoder[User] = FluentEncoder.create(_.name)
    implicit val fluentEncoderForCountry: FluentEncoder[Country] = FluentEncoder.id

    val user = User(1, "takayuky")
    val japan = Country("JP", "Japan")

    val data = Map[String, FluentAcceptable](
      "Int" -> 1,
      "User" -> user,
      "Country" -> japan,
      "Some" -> Some(user),
      "None" -> (None: Option[User])
    )

    val expected = Map[String, Any](
      "Int" -> 1,
      "User" -> user.name,
      "Country" -> japan,
      "Some" -> Some(user.name),
      "None" -> (None: Option[User])
    )
    (underlyingMock.log(_: String, _: Map[String, Any], _:Long)).expects(label, expected, time)

    logger.log(label, data, time)
  }
}
