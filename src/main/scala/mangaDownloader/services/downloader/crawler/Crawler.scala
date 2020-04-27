//package Crawler
//
//import org.jsoup.Jsoup
//import org.jsoup.nodes.Element
// import Config.Config._
//
//sealed trait CrawlerResult
//
//case object NoPageMenu extends CrawlerResult
//case object NoNavbarPages extends CrawlerResult
//
//case class MangaFirstPageDocument(
//  name: String,
//  lastPage: Int
//) extends CrawlerResult
//
//
//object Crawler {
//
//
//  /**
//    * Crawl through the first page of a manga and find how many pages it has
//    * @param inHtml
//    * @return
//    */
//  def firstChapterPages(inHtml: String): CrawlerResult = {
//    Jsoup.parse(inHtml).getElementById("pageMenu") match {
//      case navbarPages: Element =>
//        navbarPages.getElementsByTag("option").size match {
//          case 0 => NoNavbarPages
//          case pages => MangaFirstPageDocument("bleach", pages)
//        }
//      case _ => NoPageMenu
//    }
//  }
//}