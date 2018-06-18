package scalafpbook

import scala.annotation.tailrec

object PatternMatching {

  def tailConst[A](l: List[A]) = l match {
    case _ :: xs => xs
    case _ => List.empty
  }

  @tailrec
  def generalizedTail[A](l: List[A], n: Int):List[A] = l match {
    case _ :: xs if n > 0 => generalizedTail(xs, n -1)
    case x => x
    case _ => List.empty
  }

  @tailrec
  def generalizedTailPred[A](l: List[A], pred: A => Boolean):List[A] = l match {
    case x :: xs if !pred(x) => x :: xs
    case _ :: xs => generalizedTailPred(xs, pred)
    case _ => List.empty
  }

  def initNotConst[A](l: List[A]): List[A] = {
    @tailrec
    def init(l: List[A], acc: List[A]):List[A] = l match {
      case x :: xs if xs.nonEmpty => init(xs, acc :+ x)
      case x :: xs => acc
      case _ => List.empty
    }
    init(l, List())
  }

  def setHead[A](l: List[A], el: A) = l match {
    case _ :: xs => el :: xs
    case _ => List(el)
  }

  def main(args: Array[String]): Unit = {

    val res = List(1,2,3,4,5) match {
      case x :: 2 :: 4 :: _ => x
      case Nil => 42
      case x :: y :: 3 :: 4 :: _ => x + y
      case x :: xs => x + xs.sum
      case _ => 101
    }

    println(res == 3)
    println(generalizedTailPred(List(1,2,3), (x: Int) => x < 3))
    println(tailConst(List(1)))
    println(tailConst(Nil))
    println(initNotConst(List(1,2,3,4)))
  }
}
