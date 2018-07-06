package fsspeedrun.first

import fsspeedrun.first.Ord.Compare

object Syntax {
  object Eq {
    implicit class cmpOps[T](val x: T) extends AnyVal {
      def ===(y: T)(implicit rationalize: Eq[T]): Boolean = rationalize.===(x, y)
    }
  }

  object Ord {
    implicit class ordOps[T](val x: T) extends AnyVal {
      def compare(y: T)(implicit ord: Ord[T]): Compare = ord.compare(x,y)
    }
  }
}
