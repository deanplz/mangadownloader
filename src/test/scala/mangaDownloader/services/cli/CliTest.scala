package Cli

import mangaDownloader.services.cli.Cli
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.MockitoSugar

class CliTest extends AnyWordSpec with Matchers with MockitoSugar {

  val cli = new Cli()


  // happpy paths
  "cli prints whatever we give it" should {
    "given manganame parse it to (string, int, int)" in {
      assert(cli.parseInput(List("bleach")) === Right(("bleach", 0, 0)))
    }
    "given manganame and number of chapters, parse to (string, int)" in {
      assert(cli.parseInput(List("bleach", "10")) === Right(("bleach", 10, 0)))
    }
    "take 3 parameters and parse them to (string, int, int)" in {
      assert(cli.parseInput(List("bleach", "1", "10")) === Right(("bleach", 1, 10)))
    }

    // error paths
//    "bad formats return error" in {
//      val cli = new Cli(List("bleach", "d", "1"))
//      val cli2 = new Cli(List("bleach", "1", "d"))
//      val cli3 = new Cli(List("bleach", "2", "1"))
//      val cli4 = new Cli(List("bleach", "d", "f"))
//      val cli5 = new Cli(List("bleach", "-1", "2"))
//      val cli6 = new Cli(List("bleach", "-1"))
//      val cli7 = new Cli(List("bleach", "1", "-2"))
//      val cli8 = new Cli(List("bleach", "1", "5"))
//      val cli9 = new Cli(List("bleach", "1", "2", "5"))
//      val cli10 = new Cli(List("1", "bleach"))
//      val cli11 = new Cli(List("1", "bleach", "2"))
//      val cli12 = new Cli(List("1", "2", "bleach"))
//      val cli13 = new Cli(List("1", "2", "3"))
//    }

  }
}