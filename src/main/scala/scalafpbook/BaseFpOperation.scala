package scalafpbook

object BaseFpOperation {
    def curry[A,B,C](f: (A,B) => C): A => (B => C) = A => B => f(A,B)
    def uncurry[A,B,C](f: A => B => C): (A,B) => C = (A,B) => f(A)(B)
    def compose[A,B,C](f: B => C, g: A => B): A => C = A => f(g(A))

  def sum(a: Int, b: Int): Int = a + b
  def prn(a: Int): String = s"Int : ${a}"

  def main(args: Array[String]): Unit = {
    println(sum(1,2))

    val curriedSum = curry(sum)
    println(curriedSum(1)(2))

    val uncurriedSum = uncurry(curriedSum)
    println(uncurriedSum(1,2))

    val partSum = curriedSum(1)
    val composedCurriedSumPrint = compose(prn, partSum)

    println(composedCurriedSumPrint(1))
  }
}
