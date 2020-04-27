package mangaDownloader.services.cli

class Cli() {

  def parseInput(input: List[String]): Either[Throwable, (String, Int, Int)] = input match {
    case List(x) => Right((x, 0, 0))
    case List(x, y) => Right((x, y.toInt, 0))
    case List(x, y, z) => Right((x, y.toInt, z.toInt))
    case _ => Left(new Throwable("bad input"))
  }

}
