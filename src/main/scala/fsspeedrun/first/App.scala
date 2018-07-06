package fsspeedrun.first

object App {
  import Syntax.Eq._
  import Syntax.Ord._

  def main(args: Array[String]): Unit = {

    // tc for EQ
    implicit val cmpList = Eq.compareList[Ratio]
    implicit val ordList = Ord.compareList[Ratio]

    println(Ratio(1,2) === Ratio(2,4))
    println(
      List[Ratio](Ratio(1,2), Ratio(2,4)) ===
      List[Ratio](Ratio(1,2), Ratio(2,4)))

    // tc for Ord
    println(Ratio(1,2) compare Ratio(2,4))
    println(Ratio(2,2) compare Ratio(2,4))
    println(Ratio(2,2) compare Ratio(5,4))

    println(
      List[Ratio](Ratio(1,2), Ratio(2,4), Ratio(1,2)) compare
      List[Ratio](Ratio(1,2), Ratio(2,4)))

    println(
      List[Ratio](Ratio(1,2), Ratio(2,4)) compare
      List[Ratio](Ratio(1,2), Ratio(2,4)))

    println(
      List[Ratio](Ratio(1,2), Ratio(2,4)) compare
      List[Ratio](Ratio(1,2), Ratio(2,4), Ratio(1,1)))
  }
}
