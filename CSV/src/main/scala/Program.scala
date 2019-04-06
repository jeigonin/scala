import java.text.SimpleDateFormat

import simplePackage._

import scala.io.Source
import java.time.format.DateTimeFormatter
import java.text.DateFormat;

object Program {
  def main(args: Array[String]): Unit = {

    val path = "/Users/jeremyigonin/Documents/Hemera/scala/CSV/src/main/scala/simplePackage/CsvFile.csv";
    val array = readCSV(path);
    array.foreach(f = o => whichType(o.toString));
    array.foreach(f = o => print(o.getClass +" "+ o +"\n"));
    //    print(array);
  }

  def whichType(value : String)= {
    val valuetest = value.split(',').toList;
    if(valuetest.lengthCompare(3)== true){
      new Cat(valuetest(0), valuetest(1),valuetest(2).toInt);
    }
    if(valuetest.lengthCompare(4) ==true  && valuetest(2).toInt >=400){
      new Person(valuetest(0), valuetest(1), valuetest(2).toInt, valuetest(3).toInt);
    }
    if(valuetest.lengthCompare(4) == true  && valuetest(2).toInt <=400){
      new Car(valuetest(0), valuetest(1),valuetest(2).toInt, valuetest(3).toInt);
    }
    if(valuetest.lengthCompare(2) == true && valuetest(1).contains(";")){
      new Actor(valuetest(0), valuetest(1).split(";").toSeq);
    }
    if(valuetest.lengthCompare(2) == true && valuetest(0).contains(";")){
      val df :DateFormat = new SimpleDateFormat("dd/MM/yyyy");
      new Film(valuetest(0).split(";").toSeq, df.parse(valuetest(1)) );
    }

//    valuetest match {
//      case valuetest.length == 3 => new Cat(valuetest(0), valuetest(1),valuetest(2).toInt);
//      case valuetest.length == 4 => new Person(valuetest(0), valuetest(1),valuetest(2).toInt, valuetest(3).toInt);
//    }
//    value match {
//      case Cat(name, race, age) => print(value);//new Cat(name, race, age);
//      case Person(firstName, lastName, salary, numberOfChildren) => print(value);//new Person(firstName, lastName, salary, numberOfChildren);
//      case Car(brand, countryOfBirth, maxSpeed,speeds) => print(value);//new Car(brand, countryOfBirth, maxSpeed,speeds);
//      case Film(mainActor, dateOfRelease) => print(value);//new Film(mainActor, dateOfRelease);
//      case Actor(name, filmsPlayed) => print(value);//new Actor(name, filmsPlayed);
   // }
  }

  def readCSV(path:String) : Array[Any] = {
    Source.fromFile(path)
      .getLines()
      .toArray
  }

}
