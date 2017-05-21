package io.github.takayuky

package object fluent extends ToFluentAcceptable {
  object basic extends FluentEncoderBasicInstances
  object list extends FluentEncoderListInstances
}
