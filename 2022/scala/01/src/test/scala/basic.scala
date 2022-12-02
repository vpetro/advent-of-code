package org.vpetro.advent2022

import org.scalatest.funsuite.AnyFunSuite

import better.files.{Resource, File}
import java.net.URL

class BasicInput extends AnyFunSuite {

  test("Most calories should be 24000") {
    val fileUrl: URL = Resource.getUrl("basic_input.txt")
    println(fileUrl)
    val iter: Iterator[String] = File(fileUrl).lineIterator
    val result = Day01.solve(iter, 1)
    assert(result == Vector(24000))
  }


}

