package fsspeedrun.first

import fsspeedrun.first.Ord.Compare
import fsspeedrun.first.Ord.Compare.{EQ, GT, LT}


trait Ord[T] extends Eq[T] {
  def compare(x: T, y: T): Compare
}

object Ord {
  sealed trait Compare
  object Compare {
    case object LT extends Compare// less than
    case object EQ extends Compare// equals to
    case object GT extends Compare// greater than
  }

  def compareList[T](implicit ord: Ord[T]): Ord[List[T]] = new Ord[List[T]] {
    import Syntax.Eq._
    override def compare(x: List[T], y: List[T]): Compare = {
      if (x === y) EQ
      else if(x.size > y.size) GT
      else LT
    }

    override def ===(e1: List[T], e2: List[T]): Boolean = e1.size == e2.size
  }
}
