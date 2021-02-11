package aoc

import better.files.File
import scala.util.Try
import scala.util.matching.Regex

import fastparse._


object Day04 extends App {

  val testFilename = "/Users/petrov/code/advent-of-code-2020/data/2020/day/4/test_input"
  val fullFilename = "/Users/petrov/code/advent-of-code-2020/data/2020/day/4/input"


  object Parsing {
    import fastparse._, NoWhitespace._
    def parseHeight[_:P] =
      P(CharIn("0-9").rep(min=1, max=3).! ~ ("in" | "cm").!)

    def parseHairColor[_:P] =
      P("#" ~ (CharIn("0-9a-f").rep(6).!))

    def parseEyeColor[_:P] =
      P(StringIn("amb", "blu", "brn", "gry", "grn", "hzl", "oth").rep(1).!)

    def parsePassportId[_:P] =
      P(CharsWhileIn("0-9").rep(1).!.filter(_.length == 9))
  }

  sealed abstract class BirthYear private (val value: Int)
  sealed abstract class IssueYear private (val value: Int)
  sealed abstract class ExpirationYear private (val value: Int)
  sealed abstract class Height private (val value: Int, val unit: String)
  sealed abstract class HairColor private (val value: String)
  sealed abstract class EyeColor private (val value: Int)
  sealed abstract class PassportId private (val value: Int)
  sealed abstract class CountryId private (val value: Int)


  object BirthYear {
    def fromString(value: String): Option[BirthYear] = {
      Try(value.toInt)
        .toOption
        .flatMap(intValue => if (intValue >= 1920 && intValue <= 2002) Option(new BirthYear(intValue) {} ) else None)
    }
  }

  object IssueYear {
    def fromString(value: String): Option[IssueYear] = {
      Try(value.toInt)
        .toOption
        .flatMap(intValue => if (intValue >= 2010 && intValue <= 2020) Option(new IssueYear(intValue) {} ) else None)
    }
  }

  object ExpirationYear {
    def fromString(value: String): Option[ExpirationYear] = {
      Try(value.toInt)
        .toOption
        .flatMap(intValue => if (intValue >= 2020 && intValue <= 2030) Option(new ExpirationYear(intValue) {} ) else None)
    }
  }

  object Height {
    private def validateInches(value: Int) =
      if (value >= 59 && value <= 76) Some(new Height(value, "in") {}) else None

    private def validateCentimeters(value: Int) =
      if (value >= 150 && value <= 193) Some(new Height(value, "cm") {}) else None


    def fromString(value: String): Option[Height] = {

      parse(value, Parsing.parseHeight(_)) match {
        case Parsed.Success((number, "in"), _) => Try(number.toInt).toOption.flatMap(validateInches)
        case Parsed.Success((number, "cm"), _) => Try(number.toInt).toOption.flatMap(validateCentimeters)
        case _ => None
      }

    }
  }

  object HairColor {
    def fromString(value: String): Option[ExpirationYear] = {
      None
    }
  }



  case class UnvalidatedPassport(
    birthYear: Option[String] = None,
    issueYear: Option[String] = None,
    expirationYear: Option[String] = None,
    height: Option[String] = None,
    hairColor: Option[String] = None,
    eyeColor: Option[String] = None,
    passportId: Option[String] = None,
    countryId: Option[String] = None
  )

  sealed trait ValidatedPassport

  case class InvalidPassport(
    birthYear: Option[String] = None,
    issueYear: Option[String] = None,
    expirationYear: Option[String] = None,
    height: Option[String] = None,
    hairColor: Option[String] = None,
    eyeColor: Option[String] = None,
    passportId: Option[String] = None,
    countryId: Option[String] = None
  ) extends ValidatedPassport

  case class ValidPassport(
    birthYear: String,
    issueYear: String,
    expirationYear: String,
    height: String,
    hairColor: String,
    eyeColor: String,
    passportId: String,
    countryId: Option[String] = None
  ) extends ValidatedPassport


  val expectedFields = List("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")

  def validate(up: UnvalidatedPassport): ValidatedPassport =
    (for {
      byr <- up.birthYear
      iyr <- up.issueYear
      eyr <- up.expirationYear
      hgt <- up.height
      hcl <- up.hairColor
      ecl <- up.eyeColor
      pid <- up.passportId
    } yield ValidPassport(
      birthYear = byr,
      issueYear = iyr,
      expirationYear = eyr,
      height = hgt,
      hairColor = hcl,
      eyeColor = ecl,
      passportId = pid,
      countryId = up.countryId)
    ).getOrElse(
      InvalidPassport(
        up.birthYear,
        up.issueYear,
        up.expirationYear,
        up.height,
        up.hairColor,
        up.eyeColor,
        up.passportId,
        up.countryId
      ))
  
    val (batches, lastBatch) = File(fullFilename).lineIterator.foldLeft((List[String](), "")){
      (r, currentLine) =>
        val (batches, currentBatch) = r
        if (currentLine == "") (currentBatch +: batches, "") else (batches, currentBatch + " " + currentLine)
   }

   val allBatches = (lastBatch +: batches)

   val result = allBatches.map(batch => 
     batch.split(" ").toList.drop(1).foldLeft(UnvalidatedPassport()){ (p, token) => 
         token.split(":").toList match {
           case "byr"::value::Nil => p.copy(birthYear = Option(value)) 
           case "iyr"::value::Nil => p.copy(issueYear = Option(value)) 
           case "eyr"::value::Nil => p.copy(expirationYear = Option(value)) 
           case "hgt"::value::Nil => p.copy(height = Option(value)) 
           case "hcl"::value::Nil => p.copy(hairColor = Option(value)) 
           case "ecl"::value::Nil => p.copy(eyeColor = Option(value)) 
           case "pid"::value::Nil => p.copy(passportId = Option(value)) 
           case "cid"::value::Nil => p.copy(countryId = Option(value)) 
           case _ => p
         }
       }
    ).map(p => validate(p)).count {
     case _: ValidPassport => true
     case _ => false
    }

   println(result)

}
