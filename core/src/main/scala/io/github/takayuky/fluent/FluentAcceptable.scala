package io.github.takayuky.fluent

/**
 * We use an "Existential type" here. See http://www.cakesolutions.net/teamblogs/existential-types-in-scala
 */
sealed trait FluentAcceptable {
  type Inner
  val v: Inner
}

final case class ConcreteFluentAcceptable[A](v: A) extends FluentAcceptable { override type Inner = A }
