package fsspeedrun.first

import fsspeedrun.first.Ord.Compare.{EQ, GT, LT}

final case class Ratio(numerator: Long, denominator: Long)

object Ratio {

  implicit val rationCmp: Eq[Ratio] = (e1: Ratio, e2: Ratio) =>
    (e2.numerator * e1.denominator) == (e1.numerator * e2.denominator)

  implicit val ratioOrd: Ord[Ratio] = new Ord[Ratio]{
    override def compare(x: Ratio, y: Ratio): Ord.Compare = {
      if((x.numerator * y.denominator) > (y.numerator * x.denominator)) GT
      else if ((x.numerator * y.denominator) < (y.numerator * x.denominator)) LT
      else EQ
    }

    override def ===(e1: Ratio, e2: Ratio): Boolean = rationCmp.===(e1,e2)

  }
}
