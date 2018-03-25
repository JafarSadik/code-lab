import scala.annotation.tailrec

// A discrete event simulation performs actions given by a user at a given moment
trait Simulation {
  // Type defines action to be performed
  type Action = () => Unit

  // Event is Action performed at a given time
  case class Event(time: Int, action: Action)

  private var events: List[Event] = List()
  private var curtime = 0

  // simulated time in form of integer
  def currentTime: Int = curtime

  // registers an action to be performed after a certain delay relative to the current time
  def afterDelay(delay: Int)(action: => Unit): Unit = {
    val event = Event(currentTime + delay, () => action)
    events = insert(events, event)
  }

  def insert(agenda: List[Event], event: Event): List[Event] = agenda match {
    case first :: rest if first.time <= event.time => first :: insert(rest, event)
    case _ => event :: agenda
  }

  // perform simulation until no more actions waiting
  def runSimulation(): Unit = loop()

  // The event handling loop works until there are actions to perform
  @tailrec
  private def loop(): Unit = events match {
    case first :: rest =>
      events = rest
      curtime = first.time
      first.action()
      loop()
    case Nil =>
  }
}
