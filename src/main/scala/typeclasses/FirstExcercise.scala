package typeclasses

class FirstExcercise {
  final case class Cat(name: String, age: Int, color: String)

  trait Printable[A] {
    def format(a:A): String
  }

  object PrintableInstances {
    implicit val formatInt: Printable[Int] = {
      (a: Int) => a.toString
    }

    implicit val formatString: Printable[String] = {
      (a: String) => a
    }

    implicit val formatCat: Printable[Cat] = {
      (a: Cat) => s"${a.name} is a ${a.age} year-old ${a.color} cat."
    }
  }

  object Printable {

    implicit def format[A](a: A)(implicit printable: Printable[A]): String = {
      printable.format(a)
    }

    implicit def print[A](a: A)(implicit printable: Printable[A]): Unit = {
      println(format(a))
    }
  }

  object PrintableSyntax {
    implicit class PrintOps[A](a: A) {
      def format(implicit printable: Printable[A]): String = {
        printable.format(a)
      }

      def print(implicit printable: Printable[A]): Unit = {
        println(printable.format(a))
      }
    }
  }
}
