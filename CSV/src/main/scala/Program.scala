import java.text.SimpleDateFormat

import simplePackage._

import scala.io.Source
import java.time.format.DateTimeFormatter
import java.text.DateFormat;

object Program {
  def main(args: Array[String]): Unit = {

    val path = "/Users/jeremyigonin/Documents/Hemera/scala/CSV/src/main/scala/simplePackage/CsvFile.csv";
    val array = readCSV(path);
    val array2 = array.map(o => whichType(o.toString))
    array2.foreach(f = o => print(o.getClass +" "+ o +"\n"));
  }

  def whichType(value : String)= {
    val valuetest = value.split(',');

    if(valuetest.length == 3){
      new Cat(valuetest(0), valuetest(1),valuetest(2).toInt);
    }
    else if(valuetest.length == 4  && valuetest(2).toInt >=400){
      new Person(valuetest(0), valuetest(1), valuetest(2).toInt, valuetest(3).toInt);
    }
    else if(valuetest.length == 4  && valuetest(2).toInt <=400){
      new Car(valuetest(0), valuetest(1),valuetest(2).toInt, valuetest(3).toInt);
    }
    else if(valuetest.length == 2 && valuetest(1).contains(";")){
      new Actor(valuetest(0), valuetest(1).split(";").toSeq);
    }
    else if(valuetest.length == 2 && valuetest(0).contains(";")){
      val df :DateFormat = new SimpleDateFormat("dd/MM/yyyy");
      new Film(valuetest(0).split(";").toSeq, df.parse(valuetest(1)) );
    }
    else{
      print("ca merde ton truc");
    }
  }

  def readCSV(path:String) : Array[Any] = {
    Source.fromFile(path)
      .getLines()
      .toArray
  }

}
