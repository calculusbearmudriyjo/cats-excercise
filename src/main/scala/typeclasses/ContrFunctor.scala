package typeclasses

object ContrFunctor {

  trait Printable[A] {
    self =>
    def format(value: A): String
    def contraMap[B](func: B => A): Printable[B] = (value: B) => self.format(func(value))
  }

  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  implicit val stringPrintable: Printable[String] = (value: String) => "\"" + value + "\""
  implicit val booleanPrintable: Printable[Boolean] = (value: Boolean) => if(value) "yes" else "no"

  implicit val boxStringPrintable: Printable[Box[String]] = stringPrintable.contraMap((x: Box[String]) => x.value)
  implicit val boxBooleanPrintable: Printable[Box[Boolean]] = booleanPrintable.contraMap((x: Box[Boolean]) => x.value)

  final case class Box[A](value: A)

  def main(args: Array[String]): Unit = {
    println(format("hello"))
    println(format(true))
    println(format(Box("hello world")))
    println(format(Box(true)))
  }

}
