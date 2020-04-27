package mangaDownloader.services.crawler

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.MockitoSugar

// import BleachFirstChapterHtml.firstChapterHtml

class CrawlerSpec extends AnyWordSpec with Matchers with MockitoSugar {

	val crawler = new Crawler()

  "the crawler " should {
  	"give us the number of chapters from the html" in {
val testHtml =
	"""
		<div id="chapterlist">
	    <table id="listing">
	        <tr class="table_head">
	            <th class="leftgap">Chapter Name</th>
	            <th>Date Added</th>
	        </tr>
	        <tr>
	            <td>
	                <div class="chico_manga"></div>
	                <a href="/one-piece/1">One Piece 1</a> : Romance Dawn</td>
	            <td>07/06/2009</td>
	        </tr>
	        <tr>
	            <td>
	                <div class="chico_manga"></div>
	                <a href="/one-piece/2">One Piece 2</a> : They Call Him Strawhat Luffy</td>
	            <td>07/06/2009</td>
	        </tr>
	        <tr>
	            <td>
	                <div class="chico_manga"></div>
	                <a href="/one-piece/3">One Piece 3</a> : Pirate Hunter Zoro Enters</td>
	            <td>07/06/2009</td>
	        </tr>
	        <tr>
	            <td>
		<div class="chico_manga"></div>
	"""


  		assert(crawler.crawlToChapterTotal(testHtml) === 3)
  	}
  	"fail when there are no chapters in the html" in {}
  	"give us the source of the images from the html" in {}

  }
}