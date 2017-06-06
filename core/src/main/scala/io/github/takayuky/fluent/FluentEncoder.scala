package io.github.takayuky.fluent

/**
 * A type class to provide a 'FluentAcceptable' representation to write logs with FluentLogger.
 * Inspired by 'Show' type class in cats.
 */
trait FluentEncoder[-A] {
  def encode[B <: A](v: B): FluentAcceptable
}

object FluentEncoder {
  def create[A](f: A => FluentAcceptable): FluentEncoder[A] = new FluentEncoder[A] {
    override def encode[B <: A](v: B): FluentAcceptable = f(v)
  }

  def id[A]: FluentEncoder[A] = create(ConcreteFluentAcceptable.apply)

  def any[A](f: A => Any): FluentEncoder[A] = create(f andThen ConcreteFluentAcceptable.apply)
}

trait ToFluentAcceptable {
  implicit def toFluentAcceptable[A](v: A)(implicit instance: FluentEncoder[A]): FluentAcceptable = instance.encode(v)
}
