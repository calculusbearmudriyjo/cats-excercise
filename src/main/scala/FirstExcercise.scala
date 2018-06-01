class FirstExcercise {

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
    }
  }
}
