package mangaDownloader.services.cli

class Cli() {

import Cli._
  def parseInput(input: List[String]): Either[CliError, (String, Int, Int)] = input match {
    case List(x) => Right((x, 0, 0))
    case List(x, y) => Right((x, y.toInt, 0))
    case List(x, y, z) => Right((x, y.toInt, z.toInt))
    case _ => Left(CliError("bad input"))
  }

}

object Cli {
  sealed trait CliResult
  case object CliSuccess extends CliResult
  case class CliError(msg: String) extends Exception with CliResult
}
