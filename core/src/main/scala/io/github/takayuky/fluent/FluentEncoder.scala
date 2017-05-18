package io.github.takayuky.fluent

/**
 * A type class to provide a 'FluentAcceptable' representation to write logs with FluentLogger.
 * Inspired by 'Show' type class in cats.
 */
trait FluentEncoder[A] {
  def encode(v: A): FluentAcceptable
}

object FluentEncoder {
  def id[A]: FluentEncoder[A] =  new FluentEncoder[A] {
    override def encode(v: A): FluentAcceptable = ConcreteFluentAcceptable(v)
  }
}

trait ToFluentAcceptable {
  implicit def toFluentAcceptable[A](v: A)(implicit instance: FluentEncoder[A]): FluentAcceptable = instance.encode(v)
}
