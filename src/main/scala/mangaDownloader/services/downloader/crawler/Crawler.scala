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

  def images(mangaName: String, chapterBoundary1: Int, chapterBoundary2: Int) = {
    val firstChapterHtml = Source.fromURL(s"http://www.mangareader.net/$mangaName").mkString

    def go2(from: Int, to: Int) = {
      def go (chapter: Int) = {
        println(s"mangaName is $mangaName, current chapter is $chapter")
        val curr = Source.fromURL(s"http://www.mangareader.net/$mangaName/$chapter").getLines().mkString
        println("took image, about to get total images for chapter")
        val totalImages = crawlToTotalImagesForChapter(curr)
        println("got total images for chapter")
        1 to totalImages map {
          case currentImage => {
            println("about to take image from link")
            val currImg = Source.fromURL(s"http://www.mangareader.net/$mangaName/$chapter/$currentImage").getLines().mkString
            println("about to crawl to image source")
            (crawlToImageSource(currImg), chapter, currentImage)
          }
        }
      }
      (from to to).toList flatMap { go(_) }
    }

    (chapterBoundary1, chapterBoundary2) match {
      case (0, 0) => {
        val totalChapters = crawlToChapterTotal(firstChapterHtml)
        println(s"the total chapters for manga $mangaName is $totalChapters")
        combine(totalChapters, go2(1, totalChapters))
      }
      case (x, 0) =>
        combine(x, go2(1, x))
      case (x, y) =>
        combine(y-x, go2(x, y))
    }
  }
}

object Crawler {
  sealed trait CrawlerResult
  case class CrawlerSuccess(totalChapters: Int, imagesUrl: List[(String, Int, Int)]) extends CrawlerResult
  case class CrawlerError(msg: String) extends Exception with CrawlerResult

}
