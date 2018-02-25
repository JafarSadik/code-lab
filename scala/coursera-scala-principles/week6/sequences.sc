/* Seq is a base class for all indexed (Vector, String, NumericRage, Range) and
 linear sequences (List, Stream, Queue, Stack)*/
val listSeq: Seq[Int] = List(1, 2, 3)
val charSeq: Seq[Char] = "aaa" // or "aaa".seq
Array(1, 2, 3, 4).toSeq

/* Lists (covered mostly in the previous lessons)
 pros: well suited to algorithms that access head and tail elements as it's very efficient
 cons: not as easy to process in parallel, weak memory locality for bulk operations*/
List(1, 2, 3, 4).groupBy(_ % 2)
List(1, 2, 3) :: List(4, 5)     // adding two lists
List(1, 2, 3) :+ 4              // appending element to list
List(1, 2, 3) ::: List(4)
List(1, 2, 3) ++ List(4)
0 +: List(1, 2, 3)              // prepending element to list
0 :: List(1, 2 ,3)

/* Vectors has more evenly balanced access pattern than list. They are stored as a very shallow trees.
 Vector up to size 32 is stored as a list. If it becomes larger then each of 32 elements points to
 a vector of size 32. Next representation will change once again increasing capacity to 32 x 32 x 32 = 2^15
 when vector size grows above 32 x 32.
 pros: efficient indexed access, easy to process in parallel, better memory locality
       improves performance of bulk operations such as map, reduce, fold, filter
 cons: accessing head and tail much slower than for lists */
val idx = 32 * 32 * 32
val vector32x32x32 = Vector.range(0, idx + 1)
val list32x32x32 = List.range(0, idx + 1)
// Compare indexed access for vector and list
timed {vector32x32x32(idx)} + s" ms to access $idx element of a vector 1000 times"
timed {list32x32x32(idx)} + s" ms to access $idx element of a list 1000 times"
// Compare mapping elements (vector has bigger advantage for large collections)
timed{vector32x32x32.map(_ + 1)} + s" ms to map elements of a vector 1000 times"
timed{list32x32x32.map(_ + 1)} + s" ms to map elements of a list 1000 times"

Vector(1, 2 ,3) ++ Vector(4, 5) // adding two vectors
Vector(1, 2 ,3) :+ 4            // append element
0 +: Vector(1, 2 ,3)            // prepend element

// poor man's benchmark ...
def timed(calc: =>Any, repeat: Int = 1000): Long = {
  val startTime = System.currentTimeMillis()
  for (_ <- 0 to repeat) calc
  System.currentTimeMillis() - startTime
}

/* Arrays and Strings support the same operations as sequences and they can be implicitly
 converted to sequences when needed.  */
"abcdefghij" map(ch => if (ch < 'f') ch.toUpper else ch)
Array(1, 2, 3, 4) map(_ + 1)

/*Range represent a sequence of evenly spaced integers. Their representation is much more
compact that for list as all that needs to be stored is lower band, upper band and step.*/
1 until 15
1 to 15
0 to 100 by 10

// Most methods already covered for lists are applicable for all sequences
1 until 100 contains 100
1 to 100 contains 100
Vector(1, 2, 3) forall(_ < 10)
Vector(1, 2, 3) contains 2
Vector(1, 2, 3) exists (_ > 2)
1 to 100 zip "abc"
"abcdefgh" flatMap(List('.', _))         //.a.b.c.d.e.f.g.h
"abcdefgh".toList.flatMap(List('.', _)) //List(., a, ., b, ., c, ., d, ., e, ., f, ., g, ., h)
"abcdefgh".max

def scalarProduct(xs: Seq[Double], ys: Seq[Double]): Double =
  (xs zip ys).map{case(x, y) => x * y}.sum
scalarProduct(Vector(1.0, 2.0, 3.0), Vector(1.0, 2.0, 3.0))