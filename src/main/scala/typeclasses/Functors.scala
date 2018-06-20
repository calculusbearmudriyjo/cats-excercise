package typeclasses

import cats.Functor
import scala.language.higherKinds

object Functors {
  sealed trait Tree[+A]

  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  final case class Leaf[A](value: A) extends Tree[A]

  implicit val functorCustomTree = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Branch(l, r) => Branch(map(l)(f), map(r)(f))
      case Leaf(v) => Leaf(f(v))
    }
  }

  object CustomTreeOps {
    implicit class mapOps[A](a: Tree[A]) {
      def map[B](f: A => B)(implicit functor: Functor[Tree]): Tree[B] = {
        functor.map(a)(f)
      }
    }
  }
  def main(args: Array[String]): Unit = {
    import CustomTreeOps._

    println(functorCustomTree.map(Branch(Leaf(1), Branch(Leaf(2), Leaf(3))))(x => x + 1))
    println(Branch(Leaf(1), Branch(Leaf(2), Leaf(3))).map(x => x + 1))
  }
}
