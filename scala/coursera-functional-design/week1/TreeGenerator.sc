import java.util.Random

// Tree definition
trait Tree
case class Node(left: Tree, right: Tree) extends Tree
case class Leaf(value: Int) extends Tree

// Generator trait
trait Generator[+T] {
  def generate: T

  def map[U](f: T => U): Generator[U] = new Generator[U] {
    override def generate: U = f(Generator.this.generate)
  }

  def flatMap[U](f: T => Generator[U]): Generator[U] = new Generator[U] {
    override def generate: U = f(Generator.this.generate).generate
  }
}

// Integers generator
val integers = new Generator[Int] {
  val seed = System.currentTimeMillis()
  val rand = new Random(seed)

  override def generate: Int = rand.nextInt()
}

// Boolean generator
val booleans = integers.map(_ >= 0)

// Leaf generator
def leafs: Generator[Leaf] = for {
  x <- integers
} yield Leaf(x)

// Node generator
def nodes: Generator[Node] = for {
  left <- trees
  right <- trees
} yield Node(left, right)

// Tree generator
def trees: Generator[Tree] = for {
  isLeaf <- booleans
  tree <- if (isLeaf) leafs else nodes
} yield tree

val tree = trees.generate
tree.toString

def maxDepth(tree: Tree): Int = tree match {
  case Node(left, right) => (maxDepth(left) + 1).max(maxDepth(right) + 1)
  case Leaf(value) => 1
}

"Depth of the generated tree: " + maxDepth(tree)