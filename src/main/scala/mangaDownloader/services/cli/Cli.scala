package mangaDownloader.services.cli

class Cli() {

  import Cli._
  def parseInput(input: List[String]): InputResult = {
    val errorMsg = "Bad Input"
    def applyRegex(s: String) = s.matches("""[0-9]+""")
    input match {
      case List(x) =>
        Right(CliSuccess(x, 0, 0))
      case List(x, y) =>
        if(applyRegex(y))
          Right(CliSuccess(x, y.toInt, 0))
        else
          Left(CliError(errorMsg))
      case List(x, y, z) =>
        if (applyRegex(y) && applyRegex(z))
          Right(CliSuccess(x, y.toInt, z.toInt))
        else
          Left(CliError(errorMsg))
      case _ =>
        Left(CliError(errorMsg))
    }
  }
}

object Cli {
  sealed trait CliResult
  case class CliSuccess(mangaName: String, mangaFrom: Int, mangaTo: Int) extends CliResult
  case class CliError(msg: String) extends Exception with CliResult
  type InputResult = Either[CliError, CliSuccess]

}
