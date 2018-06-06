package scalafpbook

import scala.annotation.tailrec

object Fib {
  def fib(n: Int): Int = {
      @tailrec
      def localFib(current: Int, next: Int, step: Int): Int = {
        if (step == 0) current
        else localFib(next, current + next, step - 1)
    }
    localFib(0, 1, n)
  }
}
