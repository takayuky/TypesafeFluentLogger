package io.github.takayuky.fluent

trait FluentEncoderListInstances {
  implicit def fluentEncoderForTraversableOnce[A](implicit A: FluentEncoder[A]): FluentEncoder[TraversableOnce[A]] =
    FluentEncoder.create { toa =>
      ConcreteFluentAcceptable(toa.map(A.encode(_).v))
    }
}
