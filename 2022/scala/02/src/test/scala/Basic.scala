package org.vpetro

import org.scalatest.funsuite.AnyFunSuite

import Problem.calculate1
import Problem.calculate2

class BasicTest extends AnyFunSuite {
  test("sanity test should pass") {
    assert(true)
  }

  test("solve should be implemented") {
    assert(true)
  }
}


class SampleInputTest extends AnyFunSuite {
  test("sample input should produce 15") {
    assert(calculate1("A", "Y") == 8)
    assert(calculate1("B", "X") == 1)
    assert(calculate1("C", "Z") == 6)
  }
} 

class SampleInputTest2 extends AnyFunSuite {
  test("sample input should produce 15") {
    assert(calculate2("A", "Y") == 4)
    assert(calculate2("B", "X") == 1)
    assert(calculate2("C", "Z") == 7)
  }

}
