package io.github.takayuky.fluent.refined

import eu.timepit.refined.api.Refined
import io.github.takayuky.fluent.FluentEncoder

trait FluentEncoderRefinedInstances {
  implicit def fluentEncoderForR[A, P](implicit A: FluentEncoder[A]): FluentEncoder[Refined[A, P]] =
    FluentEncoder.create { rap => A.encode(rap.value) }
}
