package io.github.takayuky.fluent.refined

import eu.timepit.refined.api.Refined
import io.github.takayuky.fluent.{FluentAcceptable, FluentEncoder}

trait FluentEncoderRefinedInstances {
  implicit def fluentEncoderForR[A, P](implicit A: FluentEncoder[A]): FluentEncoder[Refined[A, P]] =
    new FluentEncoder[Refined[A, P]] {
      override def encode[B <: Refined[A, P]](rap: B): FluentAcceptable = A.encode(rap.value)
    }
}
