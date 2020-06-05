package mangaDownloader.services.downloader.imageDownloader

import java.io._
import java.net.{HttpURLConnection, URL}

class ImageDownloader {

  def downloadFile(imageLink: String, mangaIdPage: (String, Int, Int)) {
    println(s"downloading image $imageLink with manga ${mangaIdPage._1} chapter ${mangaIdPage._2} and page ${mangaIdPage._3}")
    val directory = s"src/test/resources/"
    val imageSuffix = ".jpg"
    val url = new URL(imageLink match {
      case s if s.startsWith("https") => s.replaceFirst("https", "http")
      case s if s.startsWith("http") => s
      case s => throw new Exception(s"bad link $s")
    })
    val connection = url
      .openConnection()
      .asInstanceOf[HttpURLConnection]

    connection.setRequestProperty("User-Agent", "Mozilla/5.0")
    connection.setRequestMethod("GET")
    val in: InputStream = connection.getInputStream
    val chapterDirectory = new File(s"$directory/${mangaIdPage._1}/${mangaIdPage._2}").mkdirs()
    val fileToDownloadAs: File = new File(
      s"$directory/${mangaIdPage._1}/${mangaIdPage._2}/${mangaIdPage._3}$imageSuffix"
    )
    val out: OutputStream = new BufferedOutputStream(new FileOutputStream(fileToDownloadAs))
    val byteArray = Stream.continually(in.read).takeWhile(-1 !=).map(_.toByte).toArray
    out.write(byteArray)
  }
}
