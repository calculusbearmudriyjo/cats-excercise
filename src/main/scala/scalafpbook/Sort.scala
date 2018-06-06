package scalafpbook

import scala.annotation.tailrec

object Sort {
  def sorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    @tailrec
    def loop(xs: Array[A]): Boolean = {
      if(xs.length <= 1) true
      else if (ordered(xs.head, xs.tail.head)) loop(xs.tail)
      else false
    }
    loop(as)
  }
}
