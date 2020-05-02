package mangaDownloader.services.cli

import mangaDownloader.services.cli.Cli._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.MockitoSugar

class CliTest extends AnyWordSpec with Matchers with MockitoSugar {

  val cli = new Cli()

  // happy paths
  "the parseInput function" should {
    "successfully parse the input when given only the manga name " in {
      cli.parseInput(List("bleach")) shouldBe Right(CliSuccess("bleach", 0, 0))
    }
    "successfully parse the input when given 2 params" in {
      cli.parseInput(List("bleach", "10")) shouldBe Right(CliSuccess("bleach", 10, 0))
    }
    "successfully parse the input when given all 3 params" in {
      cli.parseInput(List("bleach", "1", "10")) shouldBe Right(CliSuccess("bleach", 1, 10))
    }

    // error paths
    "cli prints an error message when not given a number for the second parameter" in {
      cli.parseInput(List("bleach", "d", "1")) shouldBe Left(CliError("Bad Input"))
    }

    //    "cli prints an error message when not given a number for the second parameter" in {
//      cli.parseInput(List("bleach", "d", "1")) shouldBe Left(CliError("Did not provide correct second parameter, it must be a chapter number"))
//    }
//    "cli prints an error message when not given a number for the third parameter" in {
//      cli.parseInput(List("bleach", "1", "d")) shouldBe Left(CliError("Did not provide correct third parameter, it must be a chapter number"))
//    }
//    "cli prints an error message when the second parameter is higher than the third one" in {
//      cli.parseInput(List("bleach", "2", "1")) shouldBe Left(CliError("Third parameter must be lower than the second one"))
//    }
//    "cli prints an error message when the second and third parameters are not numbers" in {
//      cli.parseInput(List("bleach", "d", "f")) shouldBe Left(CliError("Did not provide correct second and third parameter, they must be chapter numbers"))
//    }
//    "cli prints an error message when the second parameter is a negative number" in {
//      cli.parseInput(List("bleach", "-1", "2")) shouldBe Left(CliError("The second parameter cannot be a negative number"))
//    }
//    "cli prints an error message when the third parameter is a negative number" in {
//      cli.parseInput(List("bleach", "1", "-2")) shouldBe Left(CliError("The third parameter cannot be a negative number"))
//    }
//    "cli prints an error message when there are more than three parameters given" in {
//      cli.parseInput(List("bleach", "1", "2", "5")) shouldBe Left(CliError("There cannot be more than three parameters"))
//    }
//    "cli prints an error message when the first parameter is a number and the second is not" in {
//      cli.parseInput(List("1", "bleach")) shouldBe Left(CliError("The first parameter must be the name of the manga and the second parameter must be a chapter number"))
//    }
//    "cli prints an error message when the first parameter is a number and the second one is not" in {
//      cli.parseInput(List("1", "bleach", "2")) shouldBe Left(CliError("The first parameter must be the name of the manga and the second parameter must be a chapter number"))
//    }
//    "cli prints an error message when the first parameter is a number and the third one is not" in {
//      cli.parseInput(List("1", "2", "bleach")) shouldBe Left(CliError("The first parameter must be the name of the manga and the third parameter must be a chapter number"))
//    }
//    "cli prints an error message when the first parameter is a number" in {
//      cli.parseInput(List("1", "2", "3")) shouldBe Left(CliError("The first parameter must be the name of the manga"))
//    }
  }
}