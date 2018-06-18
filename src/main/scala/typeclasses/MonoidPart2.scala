package typeclasses

import cats.instances.option._
import cats.Monoid
import cats.instances.int._

object MonoidPart2 {

  case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid = new Monoid[Order] {
    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
    override def empty: Order = Order(0,0)
  }

  def add[A](items: List[A])(implicit m: Monoid[A]): A = items.foldLeft(m.empty)(m.combine)

  def main(args: Array[String]): Unit = {
    println(add(List(1,2,3)))
    println(add(List(Some(1),Some(2), None, Some(3), None)))
    println(add(List(Some(1),Some(2), None, Some(3), None)))
    println(add(List(Order(1.0,1.0), Order(2.0, 2.0))))
    println(add(List(Some(Order(1.0,1.0)), Some(Order(2.0, 2.0)), None)))
  }
}
