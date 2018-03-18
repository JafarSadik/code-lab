/* For a water pouring problem, we start with a set of empty glasses with a predefined capacity and a target
capacity that needs to be met by at least one of glasses. A solution is a sequence of moves (Fill,
Empty, Pour) that results in a glass filled with a predefined amount of water */
class WaterPouring(capacity: Vector[Int]) {
  // States
  type State = Vector[Int]
  val initialState: State = capacity.map(x => 0)

  // Available glasses <0, N)
  val glasses = capacity.indices

  // Moves: Empty, Fill and Pour water between glasses
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    override def change(state: State) = state updated(glass, 0)
  }

  case class Fill(glass: Int) extends Move {
    override def change(state: State) = state updated(glass, capacity(glass))
  }

  case class Pour(from: Int, to: Int) extends Move {
    override def change(state: State) = {
      val pourAmount = state(from) min (capacity(to) - state(to))
      state updated(from, state(from) - pourAmount) updated(to, state(to) + pourAmount)
    }
  }

  // Possible moves for the available glasses
  val moves = (for (glass <- glasses) yield Empty(glass)) ++
    (for (glass <- glasses) yield Fill(glass)) ++
    (for (fromGlass <- glasses; toGlass <- glasses; if fromGlass != toGlass) yield Pour(fromGlass, toGlass))

  // Paths as reverse ordered history of state changes
  class Path(history: List[Move], val endState: State) {
    //def endState: State = (history foldRight initialState)(_ change _)
    def extend(move: Move) = new Path(move :: history, move change endState)
    override def toString = (history.reverse mkString " ") + " --> " + endState + "\n"
  }

  // Initial path contains an empty history
  val initialPath = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves map path.extend
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, explored ++ (more map (_.endState)))
    }

  // Set of all possible paths
  val pathSets = from(Set(initialPath), Set(initialState))

  def solutions(target: Int): Stream[Path] =
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path
}

val problem = new WaterPouring(Vector(4, 7, 9, 11))
problem.solutions(3).take(20).toList
