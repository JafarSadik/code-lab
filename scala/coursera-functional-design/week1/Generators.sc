import java.util.Random

// Random numbers generator
{
  trait Generator[+T] {
    def generate: T
  }

  val integers = new Generator[Int] {
    val seed = System.currentTimeMillis()
    val rand = new Random(seed)

    override def generate: Int = rand.nextInt()
  }

  for (idx <- 1 to 10) yield integers.generate

  val pairs = new Generator[(Int, Int)] {
    def generate = (integers.generate, integers.generate)
  }
}

/*
  Can we avoid boilerplate required by the above generator? Ideally, we would like to write :
  val booleans = for(x <- integers) yield x > 0

  def pairs[T, U](t: Generator[T], u: Generator[U])=
      for (x <- t, y <- u) yield (x, y)

  The Scala compiler expands for-expressions in terms of map, flatMap  and lazy variant of filter.
  For this reason, we can improve the above code by implementing map and flatMap methods.
*/ {
  trait Generator[+T] {
    def generate: T

    def map[S](f: T => S): Generator[S] = new Generator[S] {
      def generate: S = f(Generator.this.generate)
    }

    def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
      def generate = f(Generator.this.generate).generate
    }
  }

  val integers = new Generator[Int] {
    val seed = System.currentTimeMillis()
    val rand = new Random(seed)

    override def generate: Int = rand.nextInt()
  }

  (for (x <- integers) yield x > 0).generate

  def pairs[T, U](t: Generator[T], u: Generator[U]) =
    for (x <- t; y <- u) yield (x, y)

  pairs(integers, integers).generate

  // Generator that always returns a single value
  def single[T](x: T): Generator[T] = new Generator[T] {
    def generate: T = x
  }

  single(4).generate

  // Generates random number in ranch
  def choose(lo: Int, high: Int): Generator[Int] =
    for (x <- integers) yield lo + x.abs % (high - lo)

  choose(1, 100).generate

  // Choose on of provided variable arguments
  def oneOf[T](xs: T*): Generator[T] =
    for (idx <- choose(0, xs.length)) yield  xs(idx)

  oneOf(1, 5, 10, 123).generate
}