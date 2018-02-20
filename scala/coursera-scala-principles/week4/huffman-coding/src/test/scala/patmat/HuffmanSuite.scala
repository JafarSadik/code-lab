package patmat

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatest.junit.JUnitRunner
import patmat.Huffman._


@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
  val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)

  test("weight of a larger tree") {
    assert(weight(t1) === 5)
  }

  test("chars of a larger tree") {
    assert(chars(t2) === List('a', 'b', 'd'))
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
  }

  test("decode and encode a very short text should be identity") {
    assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
  }

  test("calculate frequency of characters") {
    times(Nil) should be(empty)
    times(List('a', 'b', 'c', 'd', 'e')) should contain allOf(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1))
    times(List('a', 'b', 'a', 'c', 'b', 'a', 'b', 'c', 'a', 'd')) should contain allOf(('a', 4), ('b', 3), ('c', 2), ('d', 1))
  }

  test("make ordered leaf list") {
    makeOrderedLeafList(Nil) should be(empty)
    makeOrderedLeafList(List(('a', 100))) should equal(List(Leaf('a', 100)))
    makeOrderedLeafList(List(('a', 4), ('d', 1), ('b', 3), ('c', 2))) should equal(
      List(Leaf('d', 1), Leaf('c', 2), Leaf('b', 3), Leaf('a', 4))
    )
  }

  test("singleton list contains exactly one element") {
    singleton(List(Leaf('a', 1))) should be(true)
    singleton(Nil) should be(false)
    singleton(List(Leaf('a', 1), Leaf('b', 2))) should be(false)
  }

  test("combine should not affect a list with less than two elements") {
    val singletonList = List(Leaf('a', 1))
    combine(singletonList) should be theSameInstanceAs singletonList
  }

  test("combine two leaves and insert at position such that the ordering by weights is preserved") {
    val (leafA, leafB, leafC) = (Leaf('a', 2), Leaf('b', 3), Leaf('c', 4))
    combine(List(leafA, leafB)) should contain only Fork(leafA, leafB, List('a', 'b'), 5)
    combine(List(leafA, leafB, leafC)) should equal(List(leafC, Fork(leafA, leafB, List('a', 'b'), 5)))
  }

  test("combine leaf with fork and insert at position such that the ordering by weights is preserved") {
    val (leafC, leafD) = (Leaf('c', 4), Leaf('d', 8))
    val forkAB = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    combine(List(leafC, forkAB)) should contain only Fork(leafC, forkAB, List('c', 'a', 'b'), 9)
    combine(List(leafC, forkAB, leafD)) should equal(List(leafD, Fork(leafC, forkAB, List('c', 'a', 'b'), 9)))
  }

  test("combine two forks and insert at position such that the ordering by weights is preserved") {
    val leafE = Leaf('e', 12)
    val (forkAB, forkCD) = (Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Fork(Leaf('c', 4), Leaf('d', 6), List('c', 'd'), 10))
    combine(List(forkAB, forkCD)) should contain only Fork(forkAB, forkCD, List('a', 'b', 'c', 'd'), 15)
    combine(List(forkAB, forkCD, leafE)) should equal(List(leafE, Fork(forkAB, forkCD, List('a', 'b', 'c', 'd'), 15)))
  }

  test("create simplest Huffman tree") {
    createCodeTree(List('a', 'a', 'a', 'a')) should equal(Leaf('a', 4))
  }

  test("create Huffman tree for 2 character alphabet") {
    createCodeTree(List('a', 'a', 'a', 'a', 'b', 'b')) should equal(Fork(Leaf('b', 2), Leaf('a', 4), List('b', 'a'), 6))
  }

  test("create Huffman tree for 3 character alphabet") {
    val (leafA, leafB, leafC) = (Leaf('a', 3), Leaf('b', 2), Leaf('c', 1))
    val forkCB = Fork(leafC, leafB, List('c', 'b'), 3)
    assert(createCodeTree(List('a', 'a', 'a', 'b', 'b', 'c')) === Fork(forkCB, leafA, List('c', 'b', 'a'), 6))
  }

  test("encode & decode empty string") {
    assert(encode(Leaf('a', 1))(Nil) === Nil)
  }

  test("encode & decode string with 2 letter alphabet") {
    assertEncodeDecodeText("aaaabbbbbbbbbbbbbbbbbbbbb")
  }

  test("encode & decode string with 3 letter alphabet") {
    assertEncodeDecodeText("aaaaaaaaaaaaaaaaaabbbbcc")
  }

  test("encode & decode string with a full alphabet") {
    assertEncodeDecodeText("The quick brown fox jumps over the lazy dog")
  }

  test("make use of lookup table to encode character") {
    val (a$code, b$code, c$code) = (List(1, 1, 1), List(1, 1, 0), List(1, 0, 0))
    val codeTable: CodeTable = List(('a', a$code), ('b', b$code), ('c', c$code))
    codeBits(codeTable)('a') should equal(a$code)
    codeBits(codeTable)('b') should equal(b$code)
    codeBits(codeTable)('c') should equal(c$code)
  }

  test("convert code tree to lookup table") {
    convert(createCodeTree("aaab".toList)) should contain allOf(code('b', 0), code('a', 1))
    convert(createCodeTree("abbb".toList)) should contain allOf(code('a', 0), code('b', 1))
    convert(createCodeTree("aaaabbbcc".toList)) should contain allOf(code('a', 0), code('c', 1, 0), code('b', 1, 1))
  }

  test("encode & decode string with a full alphabet using a coding table") {
    val text = "The quick brown fox jumps over the lazy dog".toList
    val huffmanTree = createCodeTree(text)
    val encodedText = quickEncode(huffmanTree)(text)
    assert(decode(huffmanTree, encodedText) === text)
  }

  private def code(args: AnyVal*): CodeTableEntry = args.toList match {
    case (char:Char) :: (bits:List[Int]) => (char, bits)
  }

  private def assertEncodeDecodeText(text: String): Unit = {
    val chars = text.toList
    val codeTree = createCodeTree(chars)
    val encodedText = encode(codeTree)(chars)
    assert(decode(codeTree, encodedText) === chars)
  }
}
