package mangaDownloader.services.downloader.crawler

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.MockitoSugar
import Crawler.CrawlerError
import Crawler.CrawlerSuccess
class CrawlerSpec extends AnyWordSpec with Matchers with MockitoSugar {

  val crawler = new Crawler()

  "the crawler crawlToChapterTotal" should {
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
                <a href="/one-piece/1">One Piece 1</a> : Romance Dawn
            </td>
            <td>07/06/2009</td>
        </tr>
        <tr>
            <td>
                <div class="chico_manga"></div>
                <a href="/one-piece/2">One Piece 2</a> : They Call Him Strawhat Luffy
            </td>
            <td>07/06/2009</td>
        </tr>
        <tr>
            <td>
                <div class="chico_manga"></div>
                <a href="/one-piece/3">One Piece 3</a> : Pirate Hunter Zoro Enters
            </td>
            <td>07/06/2009</td>
        </tr>
    </table>
</div>
        """.stripMargin

      crawler.crawlToChapterTotal(testHtml) shouldBe 3
    }

    "fail when there are no chapters in the html" in {
      val testHtml =
        "asd"
      crawler.crawlToChapterTotal(testHtml) shouldBe 0
    }
  }

  "the crawler crawlToImageSource" should {
    "give us the source of the images from the html" in {
      val testHtml =
        """
        <td width="100">
            <div id="imgholder"><a href="/one-piece/1/3"><img id="img" width="800" height="1297" src="https://i5.imggur.net/one-piece/1/one-piece-1668161.jpg" alt="One Piece 1 - Page 2" /></a> </div>
        </td>
        """
      crawler.crawlToImageSource(testHtml) shouldBe "https://i5.imggur.net/one-piece/1/one-piece-1668161.jpg"
    }

    "fail when there are no sources for the images from the html" in {
      val testHtml =
        """
        <script type="text/javascript">
            document.write('<scr'+'ipt async id="BB_SLOT_'+BB_r+'_'+BB_ind+'" src="//st.bebi.com/bebi_'+BB_vrsa+'.js"></scr'+'ipt>');
        </script>
        """
      crawler.crawlToImageSource(testHtml) shouldBe "no source for images"
    }
  }

  "the combine function" when {
    "crawlToChapterTotal succeeds" should {
      "crawlToImageSource succeeds" should {
        "return a Success" in {
          crawler.combine(3,List("asd.jpg","bsd.jpg")) shouldBe CrawlerSuccess (3,List("asd.jpg","bsd.jpg"))
        }
      }

      "crawlToImageSource fails" should {
        "fail with no source for images" in {
crawler.combine(3, List("asd.jpg", "no source for images", "bsd.jpg")) shouldBe CrawlerError("no source for images")

        }
      }
    }
    "crawlToChapterTotal fails" when {
      "crawlToImageSource succeeds" should {
        "Fail with no chapters" in {
          crawler.combine(
            0,
            List("asd.jpg","bsd.jpg")
          ) shouldBe CrawlerError ("no chapters")
        }

      }
      "crawlToImageSource fails" should {
        "Fail with no chapters" in {
         crawler.combine (0,List("asd.jpg", "no source for images", "bsd.jpg")) shouldBe CrawlerError ("no chapters")
        }
      }
    }
  }
}


//first case result is zero
//second case no source for images 