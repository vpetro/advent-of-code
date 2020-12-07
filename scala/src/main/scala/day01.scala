package aoc

object Day01 extends App {

  val filename = "/Users/petrov/code/advent-of-code-2020/data/2020/day/1/input"

  def partA(input: List[Int], goal: Int): Option[(Int, Int)] = {
    val inputSet: Set[Int] = input.toSet

    input.dropWhile(i => !inputSet.contains((goal - i))).headOption match {
      case Some(value) => Option((value, goal - value))
      case None => None
    }
  }

  def partB(current: List[Int], input: List[Int], goal: Int): Option[(Int, Int, Int)] = current match {
    case (x::xs) =>
      partA(input, goal - x) match {
        case Some(v) => Some((x, v._1, v._2))
        case None => partB(xs, input, goal)
      }
    case Nil => None
  }
    
  val input = Input.read(filename)
  val resultA  = partA(input, 2020)
  println(resultA)
  println(resultA.map(r => r._1 * r._2))
  val resultB = partB(input, input, 2020)
  println(resultB)
  println(resultB.map(r => r._1 * r._2 * r._3))

}

