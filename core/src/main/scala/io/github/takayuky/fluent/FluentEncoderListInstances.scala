package io.github.takayuky.fluent

trait FluentEncoderListInstances {
  implicit def fluentEncoderForTraversableOnce[A](implicit A: FluentEncoder[A]): FluentEncoder[TraversableOnce[A]] =
    FluentEncoder.any { toa => toa.map(A.encode(_).v) }
}
