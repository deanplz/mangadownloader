package Cli

import mangaDownloader.services.cli.Cli
import mangaDownloader.services.cli.Cli._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.MockitoSugar

class CliTest extends AnyWordSpec with Matchers with MockitoSugar {

  val cli = new Cli()

  // happy paths
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
    "cli prints an error message when not given a number for the second parameter" in {
      assert(cli.parseInput(List("bleach", "d", "1")) === Left(CliError("Did not provide correct second parameter, it must be a chapter number")))
    }
    "cli prints an error message when not given a number for the third parameter" in {
      assert(cli.parseInput(List("bleach", "1", "d")) === Left(CliError("Did not provide correct third parameter, it must be a chapter number")))
    }
    "cli prints an error message when the second parameter is higher than the third one" in {
      assert(cli.parseInput(List("bleach", "2", "1")) === Left(CliError("Third parameter must be lower than the second one")))
    }
    "cli prints an error message when the second and third parameters are not numbers" in {
      assert(cli.parseInput(List("bleach", "d", "f")) === Left(CliError("Did not provide correct second and third parameter, they must be chapter numbers")))
    }
    "cli prints an error message when the second parameter is a negative number" in {
      assert(cli.parseInput(List("bleach", "-1", "2")) === Left(CliError("The second parameter cannot be a negative number")))
    }
    "cli prints an error message when the third parameter is a negative number" in {
      assert(cli.parseInput(List("bleach", "1", "-2")) === Left(CliError("The third parameter cannot be a negative number")))
    }
    "cli prints an error message when there are more than three parameters given" in {
      assert(cli.parseInput(List("bleach", "1", "2", "5")) === Left(CliError("There cannot be more than three parameters")))
    }
    "cli prints an error message when the first parameter is a number and the second is not" in {
      assert(cli.parseInput(List("1", "bleach")) === Left(CliError("The first parameter must be the name of the manga and the second parameter must be a chapter number")))
    }
    "cli prints an error message when the first parameter is a number and the second one is not" in {
      assert(cli.parseInput(List("1", "bleach", "2")) === Left(CliError("The first parameter must be the name of the manga and the second parameter must be a chapter number")))
    }
    "cli prints an error message when the first parameter is a number and the third one is not" in {
      assert(cli.parseInput(List("1", "2", "bleach")) === Left(CliError("The first parameter must be the name of the manga and the third parameter must be a chapter number")))
    }
    "cli prints an error message when the first parameter is a number" in {
      assert(cli.parseInput(List("1", "2", "3")) === Left(CliError("The first parameter must be the name of the manga")))
    }
  }
}