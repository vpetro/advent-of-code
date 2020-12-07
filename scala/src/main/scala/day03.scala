package aoc

import better.files.File


object Day03 extends App {
  val testFilename = "/Users/petrov/code/advent-of-code-2020/data/2020/day/3/test_input"
  val fullFilename = "/Users/petrov/code/advent-of-code-2020/data/2020/day/3/input"

  val testLineLength = 11
  val lineLength = 31

  val tree = '#'


  def processor(input: File, right: Int, down: Int, lineLength: Int = 31): (Int, Int, Double) =
    input.lineIterator.foldLeft((0, 0, 0d))(
      (z, i) => if (z._2 % down == 0) {
        if (i(z._1 % lineLength) == tree) {
          // println(i + " " + z + " found")
          (z._1 + right, z._2 + 1, z._3 + 1) 
        } else {
          // println(i + " " + z)
          (z._1 + right, z._2 + 1, z._3)
        }
      } else {
        // println("skipping")
        (z._1, z._2 + 1, z._3)
      }
    )

  // val testFile = File(testFilename)
  // val a = processor(testFile, 1, 1, 11)
  // val b = processor(testFile, 3, 1, 11)
  // val c = processor(testFile, 5, 1, 11)
  // val d = processor(testFile, 7, 1, 11)
  // val e = processor(testFile, 1, 2, 11)
  // println(a + " " + b + " " + c + " " + d + " " + e)


  val fullFile = File(fullFilename)
  val a = processor(fullFile, 1, 1, 31)
  val b = processor(fullFile, 3, 1, 31)
  val c = processor(fullFile, 5, 1, 31)
  val d = processor(fullFile, 7, 1, 31)
  val e = processor(fullFile, 1, 2, 31)

  println(a + " " + b + " " + c + " " + d + " " + e)
  println(f"${a._3 * b._3 * c._3 * d._3 * e._3}%.0f")

}
