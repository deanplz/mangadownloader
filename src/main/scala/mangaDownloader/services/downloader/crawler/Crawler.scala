package mangaDownloader.services.downloader.crawler
import mangaDownloader.services.downloader.crawler.Crawler.{CrawlerError, CrawlerResult, CrawlerSuccess}
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import scala.io.Source

class Crawler() {

  def crawlToChapterTotal(chapterHtml: String) =
    Jsoup.parse(chapterHtml).getElementById("chapterlist") match {
      case pageMenu: Element => {
        pageMenu.getElementsByClass("chico_manga").size
      }
      case _ => 0
    }

  def crawlToTotalImagesForChapter(chapterHtml: String): Int =
    Jsoup
      .parse(chapterHtml)
      .getElementById("selectpage")
      .getElementsByTag("option")
      .size()

  def crawlToImageSource(chapterHtml: String): String = {

    Jsoup.parse(chapterHtml).getElementById("imgholder") match {
      case imgHolder: Element =>
        imgHolder.getElementById("img").attr("src")
      case _ => "no source for images"
    }
  }

  def combine(totalChapters: Int, imagesUrl: List[(String, Int, Int)]): CrawlerResult = (totalChapters, imagesUrl) match {
    case (0, _) => CrawlerError("no chapters")
    case (_, images) if images.filter(x => x._1 == "no source for images").nonEmpty => CrawlerError("no source for images")
    case (_, _) => CrawlerSuccess(totalChapters, imagesUrl)
  }

  def images(mangaName: String) = {
    val firstChapterHtml = Source.fromURL(s"http://www.mangareader.net/$mangaName").mkString
    val totalChapters = 5//crawlToChapterTotal(firstChapterHtml)
    println(s"the total chapters for manga $mangaName is $totalChapters")
    val imagesUrls: List[(String, Int, Int)] = (1 to totalChapters).toList flatMap {
      case chapter => {
        println(s"mangaName is $mangaName, current chapter is $chapter")
        val curr = Source.fromURL(s"http://www.mangareader.net/$mangaName/$chapter").getLines().mkString
        val totalImages = crawlToTotalImagesForChapter(curr)
        1 to totalImages map {
          case currentImage => {
            val currImg = Source.fromURL(s"http://www.mangareader.net/$mangaName/$chapter/$currentImage").getLines().mkString
            (crawlToImageSource(currImg), chapter, currentImage)

          }
        }


      }
    }
    combine(totalChapters, imagesUrls)
  }
}

object Crawler {
  sealed trait CrawlerResult
  case class CrawlerSuccess(totalChapters: Int, imagesUrl: List[(String, Int, Int)]) extends CrawlerResult
  case class CrawlerError(msg: String) extends Exception with CrawlerResult

}
