package org.vpetro

import better.files.File

object App {
    def main(args: Array[String]): Unit = {

    val part = Integer.parseInt(args(0))
    val inputFile = File(args(1))
    Problem.solve(part, inputFile)

  }
}

object Problem {
  def solve(part: Int, inputFile: File) = {
    println(s"Excecuting ${part} with file: ${inputFile}")
  }

}
