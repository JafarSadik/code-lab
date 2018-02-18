def isort[T <% Ordered[T]](list: List[T]): List[T] = list match {
  case head :: tail => insert(isort(tail), head)
  case Nil => Nil
}

def insert[T <% Ordered[T]](list: List[T], newElem: T): List[T] = list match {
  case firstElem :: tail =>
    if (newElem <= firstElem) newElem :: list
    else firstElem :: insert(tail, newElem)
  case Nil => List(newElem)
}

isort(List(3, 2, 1))
isort(List("Spain", "Portugal", "United Kingdom", "Poland"))
