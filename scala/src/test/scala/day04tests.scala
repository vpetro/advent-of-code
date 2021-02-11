
package aoc

import org.scalatest.funsuite.AnyFunSuite
import fastparse.{parse, Parsed}

import Day04._

class BirthYearSpec extends AnyFunSuite {

  test("1920 should validate") {
    val result = BirthYear.fromString("1920")
    assert(result.nonEmpty)
    assertResult(result.map(_.value))(Option(1920))
  }

  test("2002 should validate") {
    val result = BirthYear.fromString("2002")
    assert(result.nonEmpty)
    assertResult(result.map(_.value))(Option(2002))
  }

  test("2021 should not validate") {
    val result = BirthYear.fromString("2021")
    assert(result.isEmpty)
  }

}

class ExpirationYearSpec extends AnyFunSuite {
  test("2020 should validate") {
    val result = ExpirationYear.fromString("2020")
    assert(result.nonEmpty)
    assertResult(result.map(_.value))(Option(2020))
  }

  test("2030 should validate") {
    val result = ExpirationYear.fromString("2030")
    assert(result.nonEmpty)
    assertResult(result.map(_.value))(Option(2030))
  }

  test("2031 should not validate") {
    val result = ExpirationYear.fromString("2031")
    assert(result.isEmpty)
  }
}

class HeightSpec extends AnyFunSuite {

  test("20in should parse") {
    assert { 
      parse("20in", Day04.Parsing.parseHeight(_)) match  {
        case Parsed.Success(("20", "in"), _) => true
        case _ => false
      }
    }
  }

  test("20cm should parse") {
    assert {
      parse("20cm", Day04.Parsing.parseHeight(_)) match {
        case Parsed.Success(("20", "cm"), _) => true
        case _ => false
      }
    }
  }

  test("30i not parse") {
    assert {
      parse("30i", Day04.Parsing.parseHeight(_)) match {
        case Parsed.Failure(_, _, _) => true
        case _ => false
      }
    }
  }

  test("150cm should validate") {
    val height = Height.fromString("150cm")
    assert(height.nonEmpty) 
    assertResult(height.map(_.value))(Option(150))
  }

  test("177cm should validate") {
    val height = Height.fromString("177cm")
    assert(height.nonEmpty) 
    assertResult(height.map(_.value))(Option(177))
  }

  test("193cm should validate") {
    val height = Height.fromString("193cm")
    assert(height.nonEmpty) 
    assertResult(height.map(_.value))(Option(193))
  }

  test("20in should validate") {
    val height = Height.fromString("20in")

    assert(height.isEmpty)
  }

  test("30m should not validate") {
    val height = Height.fromString("30m")
    assert(height.isEmpty) 
  }


}

class HairColorSpec extends AnyFunSuite {

  test("#123abc should parse") {
    assert {
      parse("#123abc", Day04.Parsing.parseHairColor(_)) match {
        case Parsed.Success("123abc", _) => true
        case _ => false
      }
    }
  }

  test("123abc should not parse") {
    assert {
      parse("123abc", Day04.Parsing.parseHairColor(_)) match {
        case Parsed.Failure(_, _, _) => true
        case _ => false
      }
    }
  }

  test("123abcd should not parse") {
    assert {
      parse("123abcd", Day04.Parsing.parseHairColor(_)) match {
        case Parsed.Failure(_, _, _) => true
        case _ => false
      }
    }
  }


}

class EyeColorSpec extends AnyFunSuite {
  test("brn should parse") {
    assert {
      parse("brn", Day04.Parsing.parseEyeColor(_)) match {
        case Parsed.Success("brn", _) => true
        case _ => false
      }
    }
  }

  test("wat should not parse") {
    assert {
      parse("wat", Day04.Parsing.parseEyeColor(_)) match {
        case Parsed.Failure(_, _, _) => true
        case _ => false
      }
    }
  }
}

class PassportIdSpec extends AnyFunSuite {
  test("000000001 should parse") {
    assert {
      parse("000000001", Day04.Parsing.parsePassportId(_)) match {
        case Parsed.Success("000000001", _) => true
        case _ => false
      }
    }
  }

  test("0123456789 should not parse") {
    assert {
      parse("0123456789", Day04.Parsing.parsePassportId(_)) match {
        case Parsed.Failure(_, _, _) => true
        case Parsed.Success("0123456789", _) => false
        case _ => false
      }
    }
  }
}


