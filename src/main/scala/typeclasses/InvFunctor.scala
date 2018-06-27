package typeclasses

object InvFunctor {
  trait Codec[A] {
    self =>
    def encode(value: A): String
    def decode(value: String): A
    def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
      override def encode(value: B): String = self.encode(enc(value))
      override def decode(value: String): B = dec(self.decode(value))
    }
  }

  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)
  def decode[A](value: String)(implicit c: Codec[A]): A =
    c.decode(value)

  implicit val stringCodec: Codec[String] = {
    new Codec[String] {
      def encode(value: String) = value
      def decode(value: String) = value
    }
  }

  implicit def codecBox[A](implicit b: Codec[A]): Codec[Box[A]] = b.imap(x => Box(x), x => x.value)

  case class Box[A](value: A)

  def main(args: Array[String]): Unit = {
    implicit val codecBool: Codec[Boolean] = stringCodec.imap(_.toBoolean, _.toString)
    implicit val codecDouble: Codec[Double] = stringCodec.imap(_.toDouble, _.toString)

    println(encode(true))
    println(decode[Boolean]("true"))
    println(encode(2.2))
    println(decode[Double]("2.2"))

    println(encode(123.4))
    // res0: String = 123.4
    println(decode[Double]("123.4"))
    // res1: Double = 123.4
    println(encode(Box(123.4)))
    // res2: String = 123.4
    println(decode[Box[Double]]("123.4"))
    // res3: Box[Double] = Box(123.4)
    println(encode(Box(true)))
    // res2: String = 123.4
    println(decode[Box[Boolean]]("false"))
  }
}
