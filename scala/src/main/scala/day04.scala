package aoc

import better.files.File

object Day04 extends App {

  val testFilename = "/Users/petrov/code/advent-of-code-2020/data/2020/day/4/test_input"
  val fullFilename = "/Users/petrov/code/advent-of-code-2020/data/2020/day/4/input"


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
