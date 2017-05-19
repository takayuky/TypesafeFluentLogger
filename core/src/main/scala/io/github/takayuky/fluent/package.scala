package io.github.takayuky

package object fluent
  extends ToFluentAcceptable
    with FluentEncoderBasicInstances {
  object list extends FluentEncoderListInstances
}
