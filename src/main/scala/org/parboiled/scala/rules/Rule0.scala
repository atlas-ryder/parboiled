package org.parboiled.scala.rules

import org.parboiled.matchers._
import java.lang.String
import Rule._

/**
 * A rule which does not affect the parsers value stack.
 */
class Rule0(matcher: Matcher) extends Rule(matcher) {
  def ~[X, Y, Z](other: PopRule3[X, Y, Z]) = new PopRule3[X, Y, Z](append(other))
  def ~[Y, Z](other: PopRule2[Y, Z]) = new PopRule2[Y, Z](append(other))
  def ~[Z](other: PopRule1[Z]) = new PopRule1[Z](append(other))
  def ~(other: PopRuleN3) = new PopRuleN3(append(other))
  def ~(other: PopRuleN2) = new PopRuleN2(append(other))
  def ~(other: PopRuleN1) = new PopRuleN1(append(other))
  def ~[X, Y, Z, R](other: ReductionRule3[X, Y, Z, R]) = new ReductionRule3[X, Y, Z, R](append(other))
  def ~[Y, Z, R](other: ReductionRule2[Y, Z, R]) = new ReductionRule2[Y, Z, R](append(other))
  def ~[Z, R](other: ReductionRule1[Z, R]) = new ReductionRule1[Z, R](append(other))
  def ~(other: Rule0) = new Rule0(append(other))
  def ~[A](other: Rule1[A]) = new Rule1[A](append(other))
  def ~[A, B](other: Rule2[A, B]) = new Rule2[A, B](append(other))
  def ~[A, B, C](other: Rule3[A, B, C]) = new Rule3[A, B, C](append(other))
  def ~[A, B, C, D](other: Rule4[A, B, C, D]) = new Rule4[A, B, C, D](append(other))
  def ~[A, B, C, D, E](other: Rule5[A, B, C, D, E]) = new Rule5[A, B, C, D, E](append(other))
  def ~[A, B, C, D, E, F](other: Rule6[A, B, C, D, E, F]) = new Rule6[A, B, C, D, E, F](append(other))
  def ~[A, B, C, D, E, F, G](other: Rule7[A, B, C, D, E, F, G]) = new Rule7[A, B, C, D, E, F, G](append(other))
  def ~>[R](f: String => R) = new Rule1[R](append(push(exec[String, R](getMatch, f))))
  def ~~>[Z, R](f: Z => R) = new ReductionRule1[Z, R](append(push(exec(stack1(pop), f))))
  def ~~>[Y, Z, R](f: (Y, Z) => R) = new ReductionRule2[Y, Z, R](append(push(exec(stack1(pop), f))))
  def ~~>[X, Y, Z, R](f: (X, Y, Z) => R) = new ReductionRule3[X, Y, Z, R](append(push(exec(stack3(pop), f))))
  def |(other: Rule0) = new Rule0(appendChoice(other))
  def -(upperBound: String): Rule0 = throw new IllegalArgumentException("char range operator '-' only allowed on single character strings")
  protected def withMatcher(matcher: Matcher) = new Rule0(matcher).asInstanceOf[this.type]
}

object Rule0 {
  implicit def toRule(rule: Rule0): org.parboiled.Rule = rule.matcher
}
