package mangaDownloader
import mangaDownloader.services.cli.Cli
import mangaDownloader.services.cli.Cli.CliSuccess
import mangaDownloader.services.downloader.crawler.Crawler

object Main {

  def main(args: Array[String]): Unit = {
    val cli = new Cli
    cli.parseInput(args.toList) match {
      case Right(CliSuccess(a, b, c)) => {
        val crawler = new Crawler
        println(crawler.images(a))
      }
    }

  }
}
