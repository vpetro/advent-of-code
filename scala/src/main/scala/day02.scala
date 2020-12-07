package aoc

import better.files.File

object Day02 extends App {

  case class UnvalidatedLine(start: Int, end: Int, letter: String, password: String)

  sealed trait ValidatedLine
  case class ValidLine(start: Int, end: Int, letter: String, password: String) extends ValidatedLine
  case class InvalidLine(start: Int, end: Int, letter: String, password: String) extends ValidatedLine

  object ValidatedLine {
    def validateA(line: UnvalidatedLine): ValidatedLine = {
      val count = line.password.count(_ == line.letter(0)) 
      if (line.start <= count && count <= line.end)
        ValidLine(line.start, line.end, line.letter, line.password)
      else
        InvalidLine(line.start, line.end, line.letter, line.password)
    }

    def validateB(line: UnvalidatedLine): ValidatedLine = {
      val passwordLength = line.password.length
      if (line.start > passwordLength || line.end > passwordLength) {
        InvalidLine(line.start, line.end, line.letter, line.password)
      } else {
        val c = line.letter(0)
        (line.password(line.start-1), line.password(line.end-1)) match {
          case (`c`, `c`) => InvalidLine(line.start, line.end, line.letter, line.password)
          case (`c`, _) => ValidLine(line.start, line.end, line.letter, line.password)
          case (_, `c`) => ValidLine(line.start, line.end, line.letter, line.password)
          case _ => InvalidLine(line.start, line.end, line.letter, line.password)
        }
      }
    }
  }

  val filename = "/Users/petrov/code/advent-of-code-2020/data/2020/day/2/input"

  
  println(File(filename).lineIterator.map(parse) 
    .toList.flatten.map(ValidatedLine.validateB).count {
      case a: ValidLine => true
      case _ => false
  })


  import scala.util.matching.Regex
  def parse(line: String) = {
    val r = new Regex("""(\d+)-(\d+) (\w): (\w+)""")
    r.findFirstMatchIn(line) match {
      case Some(m) => Some(UnvalidatedLine(m.group(1).toInt, m.group(2).toInt, m.group(3), m.group(4)))
      case None => None
    }
  }


}
