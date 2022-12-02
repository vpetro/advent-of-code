package org.vpetro

import better.files.File

object App {
    def main(args: Array[String]): Unit = {

    val part = Integer.parseInt(args(0))
    val inputFile = File(s"src/main/resources/${part}.txt")
    val result = Problem.solve(inputFile)
    println(result)

  }
}


object Problem {
  def solve(inputFile: File) = {

    val plays = inputFile.lineIterator.map(line => line.split(" ").toList match {
      case (x::y::Nil) => (x, y)
      case _ => ???
    })

    (plays.map { value =>
      val (p1, p2) = value
      calculate2(p1, p2)
    }).sum
  }

  def calculate1(p1: String, p2: String): Int = {
      // draw
      if (p1 == "A" && p2 == "X") { 1 + 3 }
      else if (p1 == "B" && p2 == "Y") { 2 + 3 }
      else if (p1 == "C" && p2 == "Z") { 3 + 3 }
      // other
      else if (p1 == "A" && p2 == "Y") { 2 + 6 }
      else if (p1 == "A" && p2 == "Z") { 3 + 0 }
      else if (p1 == "B" && p2 == "X") { 1 + 0 }
      else if (p1 == "B" && p2 == "Z") { 3 + 6 }
      else if (p1 == "C" && p2 == "X") { 1 + 6 }
      else if (p1 == "C" && p2 == "Y") { 2 + 0 }
      else ???
    }

  def calculate2(p1: String, p2: String): Int = {
      // A = Rock (1)
      // B = Paper (2)
      // C = Scissors (3)
      // X - lose (0), Y - draw (3) - Z - win (6)

      if (p1 == "A" && p2 == "X") { 3 + 0 }
      else if (p1 == "B" && p2 == "Y") { 2 + 3 }
      else if (p1 == "C" && p2 == "Z") { 1 + 6 }
      else if (p1 == "A" && p2 == "Y") { 1 + 3 }
      else if (p1 == "A" && p2 == "Z") { 2 + 6 }
      else if (p1 == "B" && p2 == "X") { 1 + 0 }
      else if (p1 == "B" && p2 == "Z") { 3 + 6 }
      else if (p1 == "C" && p2 == "X") { 2 + 0 }
      else if (p1 == "C" && p2 == "Y") { 3 + 3 }
      else ???
    }

}

