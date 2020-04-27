package Crawler

import org.scalatest.FunSuite

// import BleachFirstChapterHtml.firstChapterHtml

class CrawlerSpec extends FunSuite {

  test("crawler returns the pages for the first chapter") {
//    assert(Crawler.firstChapterPages(firstChapterHtml) == MangaFirstPageDocument("bleach", 57))
  }

  test("no navbar pages fails gracefully") {
    val text = """<div">some text</div>"""
//    assert(Crawler.firstChapterPages(text) == NoPageMenu)
  }

  test("no page menu fails gracefully") {
    val text = """<div id="selectpage"><select id="pageMenu" name="pageMenu"></div>"""
    println(text)
//    assert(Crawler.firstChapterPages(text) == NoNavbarPages)
  }

//  test("asd") {
//    assert(1 == 2)
//  }
}