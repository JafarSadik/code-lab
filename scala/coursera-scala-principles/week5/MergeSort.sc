// Merge sort that makes use of implicit parameters and tuples.
object MergeSort {
  def msort[T](list: List[T])(implicit ordering: Ordering[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xsTail, y :: ysTail) =>
        if (ordering.lt(x, y)) x ::merge(xsTail, ys)
        else y :: merge(xs, ysTail)
    }

    val middle = list.length / 2
    if (middle == 0) list
    else {
      val (xs, ys) = list splitAt middle
      merge(msort(xs), msort(ys))
    }
  }

  msort(List(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0))
}
