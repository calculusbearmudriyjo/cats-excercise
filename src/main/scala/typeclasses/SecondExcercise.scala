package typeclasses

import cats.Show
import cats.syntax.show._
import cats.Eq
import cats.syntax.eq._

object SecondExcercise {
  final case class Cat(name: String, age: Int, color: String)

  implicit val formatCat: Show[Cat] = Show.show((a: Cat) => s"${a.name} is a ${a.age} year-old ${a.color} cat.")
  implicit val compareCat: Eq[Cat] = Eq.instance[Cat]((c1, c2) => c1.age == c2.age && c1.color == c2.color)
  implicit val compareOptionCat: Eq[Option[Cat]] = Eq.instance[Option[Cat]]((o1, o2) => (o1, o2) match {
    case (Some(c1), Some(c2)) => c1 === c2
    case (None, None) => true
    case _ => false
  })

  def main(args: Array[String]): Unit = {
    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")
    val cat3 = Cat("Robo-Garfield", 38, "orange and black")

    println(cat1.show)

    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    println(s"cat1  : ${cat1.show} \n" +
      s"cat2  : ${cat2.show} \n" +
      s"is eq : ${cat1 === cat2}\n")
    println(s"cat1  : ${cat1.show} \n" +
      s"cat3  : ${cat3.show} \n" +
      s"is eq : ${cat1 === cat3}\n")

    println("Option cat with empty is eq : " + (optionCat1 === optionCat2))
    println("Option cat with option is eq : " + (optionCat1 === optionCat1))
    println("Option cat with option (diff cat) is eq : " + (optionCat1 === Option(cat2)))
    println("Empty  cat with empty is eq : " + (optionCat2 === optionCat2))
  }
}
