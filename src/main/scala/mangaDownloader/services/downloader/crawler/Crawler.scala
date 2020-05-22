package mangaDownloader.services.downloader.crawler
import mangaDownloader.services.downloader.crawler.Crawler.{CrawlerError, CrawlerResult, CrawlerSuccess}
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.collection.immutable
import scala.io.Source

class Crawler() {

  def crawlToChapterTotal(chapterHtml: String) =
    Jsoup.parse(chapterHtml).getElementById("chapterlist") match {
      case pageMenu: Element => {
        pageMenu.getElementsByClass("chico_manga").size
      }
      case _ => 0
    }

  def crawlToImageSource(chapterHtml: String): String = {

    Jsoup.parse(chapterHtml).getElementById("imgholder") match {
      case imgHolder: Element =>
        imgHolder.getElementById("img").attr("src")
      case _ => "no source for images"
    }
  }

  def combine(totalChapters: Int, imagesUrl: List[String]): CrawlerResult = (totalChapters, imagesUrl) match {
    case (0, _) => CrawlerError("no chapters")
    case (_, images) if images.contains("no source for images") => CrawlerError("no source for images")
    case (_, _) => CrawlerSuccess(totalChapters, imagesUrl)
  }

  def images(mangaName: String) = {
    val firstChapterHtml = Source.fromURL(s"http://www.mangareader.net/$mangaName").mkString
    val totalChapters = crawlToChapterTotal(firstChapterHtml)
    println(s"the total chapters for manga $mangaName is $totalChapters")
    val imagesUrls = (1 to totalChapters) map {
      case index => {
        println(s"mangaName is $mangaName, current chapter is $index")
        val curr = Source.fromURL(s"http://www.mangareader.net/$mangaName/$index").getLines().mkString
        crawlToImageSource(curr)
      }
    }
    combine(totalChapters, imagesUrls.toList)
  }
}

object Crawler {
  sealed trait CrawlerResult
  case class CrawlerSuccess(totalChapters: Int, imagesUrl: List[String]) extends CrawlerResult
  case class CrawlerError(msg: String) extends Exception with CrawlerResult

}
