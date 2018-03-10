// Case classes are Scala's preferred way to define complex data.
// Sample representation of JSON in Scala
abstract class JSON {
  override def toString: String = show

  def show: String = this match {
    case JNull => "null"
    case JStr(str) => "\"" + str + "\""
    case JNum(num) => num.toString
    case JSeq(elems) => elems.map(_.toString).mkString("[", ", ", "]")
    case JObj(bindings) => bindings
      .map{case(key, value) => key + ": " + value.toString}
      .mkString("{", ", ", "}")
  }
}

case class JSeq(elem: List[JSON]) extends JSON
case class JObj(bindings: Map[String, JSON]) extends JSON
case class JNum(num: Double) extends JSON
case class JStr(str: String) extends JSON
case class JBool(b: Boolean) extends JSON
case object JNull extends JSON

JStr("abc")
JNum(123)
JNull
JSeq(List(JStr("abc"), JNum(123), JNull))
JObj(Map("a" -> JNum(1), "ab" -> JNum(2), "abc" -> JNum(3)))
JObj(Map(
  "firstName" -> JStr("John"),
  "address" -> JObj(Map(
    "streetAddress" -> JStr("21 Old Street"),
    "postalCode" -> JNum(2123)
  )),
  "phoneNumbers" -> JSeq(List(
    JStr("212 555 4321"),
    JStr("212 555 4322")
  ))
))


