package mangaDownloader.services.downloader.crawler

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.MockitoSugar
import mangaDownloader.services.downloader.imageDownloader.ImageDownloader

class ImageDownloaderSpec extends AnyWordSpec with Matchers with MockitoSugar {

  val id = new ImageDownloader()

//  "the download function" should {
//    "create a new image in the resources folder" in {
//
//      1 to 10 {
//        index => id.downloadFile("https://i4.imggur.net/one-piece/973/one-piece-13434697.jpg", ("973", index))
//
//        }
//      res shouldBe "asd"
//      res2 shouldBe "bsd"
//    }
//
//    "fail when there are no chapters in the html" in {
//      id.download("") shouldBe 3
//    }
//  }


}
