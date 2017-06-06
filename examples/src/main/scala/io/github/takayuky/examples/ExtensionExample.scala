package io.github.takayuky.examples

import io.github.takayuky.fluent._
import io.github.takayuky.fluent.basic._

object ExtensionExample {
  private[this] final case class User(id: Int, name: String)

  def main(args: Array[String]): Unit = {
    val tag = "basic_example"
    val host = "localhost"
    val port = 24224

    val logger = TypesafeFluentLoggerFactory.getLogger(tag, host, port)

    val user = User(1, "takayuky")

    user2Int(logger, user)
    user2Str(logger, user)
    user2Object(logger, user)
  }

  private[this] def user2Int(logger: TypesafeFluentLogger, user: User): Unit = {
    implicit val fluentEncoderForUser: FluentEncoder[User] = FluentEncoder.create(_.id)

    logger.log("success", Map(
      "User" -> user
    ), 0L)
    // => {"User": 1}
  }

  private[this] def user2Str(logger: TypesafeFluentLogger, user: User): Unit = {
    implicit val fluentEncoderForUser: FluentEncoder[User] = FluentEncoder.create(_.name)

    logger.log("success", Map(
      "User" -> user
    ), 0L)
    // => {"User": "takayuky"}
  }

  private[this] def user2Object(logger: TypesafeFluentLogger, user: User): Unit = {
    implicit val fluentEncoderForUser: FluentEncoder[User] = FluentEncoder.id

    logger.log("success", Map(
      "User" -> user
    ), 0L)
    // => {"User": {"id": 1, "name": "takayuky"} }
  }
}
