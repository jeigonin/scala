import simplePackage._

object Program {
  def main(args: Array[String]): Unit = {
     var test = "e";
  }

  def whichType[A](value: Option[A])= {
    val err = value.getOrElse("Null")
    value match {
      case Cat(name, race, age) => ""
      case Person(firstName, lastName, salary, numberOfChildren) => ""
      case Car(brand, countryOfBirth, maxSpeed,speeds) => ""
      case Film(mainActor, dateOfRelease) => ""
      case Actor(name, filmsPlayed) => ""
    }
  }

}
