package org.vpetro.advent2022

import better.files.File

object Main {

  def main(args: Array[String]): Unit = {
    val numBestElves = Integer.parseInt(args(0))
    val fileIter = File(args(1)).lineIterator
    val result = Day01.solve(fileIter, numBestElves)
    println(result.sum)
  }
}

object Day01 {
  def solve(iter: Iterator[String], numBestElves: Int): Vector[Int] = {
    val result = iter.foldLeft(Vector.empty[Vector[Int]])((acc, elem) => {
      if (elem == "") {
          acc :+ Vector.empty[Int]
      } else {
        val e = Integer.parseInt(elem)
        if (acc.takeRight(1).flatten.isEmpty) {
          acc :+ Vector(e)
        } else {
          acc.dropRight(1) :+ acc.takeRight(1).flatMap(_.map(_ + e))
        }
      }
    })
    result.filter(!_.isEmpty).map(_.sum).sorted.takeRight(numBestElves)
  }

}

