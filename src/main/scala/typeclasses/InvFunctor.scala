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

  def main(args: Array[String]): Unit = {
    implicit val codec: Codec[Boolean] = stringCodec.imap(_.toBoolean, _.toString)

    println(encode(true))
    println(decode[Boolean]("true"))
  }
}
