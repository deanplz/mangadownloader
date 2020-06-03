package mangaDownloader
import mangaDownloader.services.cli.Cli
import mangaDownloader.services.cli.Cli.CliSuccess
import mangaDownloader.services.downloader.crawler.Crawler
import mangaDownloader.services.downloader.crawler.Crawler.CrawlerSuccess
import mangaDownloader.services.downloader.imageDownloader.ImageDownloader

object Main {

  def main(args: Array[String]): Unit = {
    val cli = new Cli
    cli.parseInput(args.toList) match {
      case Right(CliSuccess(a, b, c)) => {
        val crawler = new Crawler
        crawler.images(a) match {
          case CrawlerSuccess(totalChapters, imagesUrls) => {
            val id = new ImageDownloader


            imagesUrls foreach {
              case (image, chapter, page) => {
                println(s"image link: $image belonging to chapter $chapter")
                id.downloadFile(image, (a, chapter, page))
              }

            }


          }

        }

      }
    }

  }
}
