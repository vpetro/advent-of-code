package aoc

import better.files.File

object Input {

  def read(filename: String): List[Int] = {
    val f: File = File(filename)
    f.lineIterator.map(_.toInt).toList
  }
}
