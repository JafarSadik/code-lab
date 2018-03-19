/* For a water pouring problem, we start with a set of empty glasses with a predefined capacity and a target
capacity that needs to be met by at least one of glasses. A solution is a sequence of moves (Fill,
Empty, Pour) that results in a glass filled with a predefined amount of water */
class WaterPouring(glassCapacities: Vector[Int]) {
  require(glassCapacities.nonEmpty, "Specify capacity of at least one glass")

  // States
  type State = Vector[Int]
  val initialState: State = glassCapacities.map(x => 0)

  // Available glasses <0, N)
  val glasses = glassCapacities.indices

  // Moves: Empty, Fill and Pour water between glasses
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    override def change(state: State) = state updated(glass, 0)
  }

  case class Fill(glass: Int) extends Move {
    override def change(state: State) = state updated(glass, glassCapacities(glass))
  }

  case class Pour(from: Int, to: Int) extends Move {
    override def change(state: State) = {
      val pourAmount = state(from) min (glassCapacities(to) - state(to))
      state updated(from, state(from) - pourAmount) updated(to, state(to) + pourAmount)
    }
  }

  // Paths as reverse ordered history of state changes
  class Path(history: List[Move], val endState: State) {
    // Calculate end state based on history (removed as it's more efficient to memorize current state)
    //def endState: State = (history foldRight initialState)(_ change _)

    // A sequence of all possible paths constructed by extending the current path with a single valid move
    def nextPaths: Seq[Path] = moves map extend

    // Extend the path with a single move
    private def extend(move: Move) = new Path(move :: history, move change endState)

    /* Establish possible moves based on the available glasses:
      - can empty any non-empty glass
      - can fill any non-full glass
      - can pour water between different glasses if target glass is not full and source glass is not empty */
    private def moves(): Seq[Move] =
      (for (glass <- glasses; if endState(glass) > 0) yield Empty(glass)) ++
      (for (glass <- glasses; if endState(glass) < glassCapacities(glass)) yield Fill(glass)) ++
      (
        for {
          fromGlass <- glasses; toGlass <- glasses
          if fromGlass != toGlass
          if endState(fromGlass) > 0
          if endState(toGlass) < glassCapacities(toGlass)
        } yield Pour(fromGlass, toGlass)
      )

    override def toString = (history.reverse mkString " ") + " --> " + endState + "\n"
  }

  // Initial path contains an empty history
  val initialPath = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- path.nextPaths
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, explored ++ (more map (_.endState)))
    }

  // Set of all possible paths
  val pathSets = from(Set(initialPath), Set(initialState))

  def solutions(targetWaterLevel: Int): Stream[Path] =
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains targetWaterLevel
    } yield path
}

new WaterPouring(glassCapacities = Vector(4, 7, 1, 20))
  .solutions(targetWaterLevel = 17)
  .take(10).toList

