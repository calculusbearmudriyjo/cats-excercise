package typeclasses

object Monoids {

  def associativeLaw[A](x: A, y: A, z: A)(implicit monoid: Monoid[A]): Boolean = {
    monoid.combine(z,monoid.combine(x,y)) == monoid.combine(x, monoid.combine(y,z))
  }

  def identityLaw[A](x:A)(implicit monoid: Monoid[A]): Boolean = {
    monoid.combine(monoid.empty, x) == x && monoid.combine(x, monoid.empty) == x
  }

  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) = monoid
  }

  object ConjuctionBooleanMonoid extends Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
    override def empty: Boolean = true
  }

  object DisjunctionBooleanMonoid extends Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
    override def empty: Boolean = false
  }

  object EitherBooleanMonoid extends Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (!x && y) || (!y && x)
    override def empty: Boolean = false
  }

  object XnorBooleanMonoid extends Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (!y || x)
    override def empty: Boolean = true
  }

  class UnionSetMonoid[A] extends Monoid[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
    override def empty: Set[A] = Set.empty
  }

  class IntersectionSetMonoid[A] extends Semigroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
  }

  class DifferenceSetMonoid[A] extends Monoid[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = (x diff y) union (y diff x)
    override def empty: Set[A] = Set.empty
  }
}
