package fsspeedrun.first

trait Eq[T] {
  def === (e1: T, e2: T): Boolean
}

object Eq {
  def compareList[T](implicit cmp: Eq[T]): Eq[List[T]] = {
    (first, second) => {
      if (first.size != second.size) false
      else {
        first.indices.foldLeft(true)((acc, i) => if (cmp.===(first(i), second(i))) acc else false)
      }
    }
  }
}