package io.github.takayuky.fluent

trait FluentEncoderBasicInstances {
  implicit val fluentEncoderForBoolean:    FluentEncoder[Boolean]    = FluentEncoder.id
  implicit val fluentEncoderForInt:        FluentEncoder[Int]        = FluentEncoder.id
  implicit val fluentEncoderForLong:       FluentEncoder[Long]       = FluentEncoder.id
  implicit val fluentEncoderForDouble:     FluentEncoder[Double]     = FluentEncoder.id
  implicit val fluentEncoderForFloat:      FluentEncoder[Float]      = FluentEncoder.id
  implicit val fluentEncoderForString:     FluentEncoder[String]     = FluentEncoder.id
  implicit val fluentEncoderForBigInt:     FluentEncoder[BigInt]     = FluentEncoder.id
  implicit val fluentEncoderForBigDecimal: FluentEncoder[BigDecimal] = FluentEncoder.id

  implicit def fluentEncoderForOption[A](implicit A: FluentEncoder[A]): FluentEncoder[Option[A]] =
    FluentEncoder.any { oa => oa.map(A.encode(_).v) }
}
