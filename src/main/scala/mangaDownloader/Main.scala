package mangaDownloader
import mangaDownloader.services.cli.Cli

object Main {

  def main(args: Array[String]): Unit = {
    val cli = new Cli
    println(cli.parseInput(args.toList))
  }
}
