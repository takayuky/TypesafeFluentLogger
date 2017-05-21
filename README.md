## TypesafeFluentLogger

[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)

Fluent Logger with type safety and extensibility. You can no longer pass uninteded values to loggers.

### What's wrong with [fluent-logger-scala](https://github.com/fluent/fluent-logger-scala)

```Scala
case class ShouldNotBePassedToLoggers(value: String)
val shouldNotBePassedToLoggers = ShouldNotBePassedToLoggers("don't pass me to loggers")

val logger: org.fluentd.logger.scala.FluentLogger
logger.log("log", Map("danger" -> shouldNotBePassedToLoggers)) // You could pass literally anything.
```


### Usage

```Scala
import io.github.takayuky.fluent._
import io.github.takayuky.fluent.basic._

val logger: TypesafeFluentLogger = TypesafeFluentLoggerFactory(tag, host, port)

// TypesafeFluentLogger knows how to handle these types
logger.log("success", Map(
  "Boolean" -> true,
  "Int" -> 1,
  "Long" -> 1L,
  "Double" -> 1d,
  "Float" -> 1f,
  "String" -> "hello",
  "BigInt" -> BigInt(1),
  "BigDecimal" -> BigDecimal(1),
  "Option" -> Some(1)
), 0L)


case class ShouldNotBePassedToLoggers(value: String)
val shouldNotBePassedToLoggers = ShouldNotBePassedToLoggers("don't pass me to loggers")

// Compile error. You can't pass values which TypesafeFluentLoggerFactory doesn't know how to handle.
logger.log("fail", Map("danger" -> shouldNotBePassedToLoggers), 0L)
```
