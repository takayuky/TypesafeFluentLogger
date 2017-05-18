package io.github.takayuky.fluent

import org.fluentd.logger.scala.{FluentLogger, FluentLoggerFactory}

final case class TypesafeFluentLogger(underlying: FluentLogger) extends AnyVal {
  def log(label: String, data: Map[String, FluentAcceptable], timestamp: Long): Unit = {
    val validatedData = data.mapValues(_.v)
    underlying.log(label, validatedData, timestamp)
  }
}

object TypesafeFluentLoggerFactory {
  def getLogger(tag: String, host: String, port: Int): TypesafeFluentLogger =
    TypesafeFluentLogger(FluentLoggerFactory.getLogger(tag, host, port))
}
