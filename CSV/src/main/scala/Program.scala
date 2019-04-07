import java.text.SimpleDateFormat

import simplePackage._

import scala.io.Source
import java.time.format.DateTimeFormatter
import java.text.DateFormat

import com.oracle.javafx.jmx.json.JSONFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import java.io.{File, FileOutputStream}
import com.github.agourlay.json2Csv.Json2Csv


object Program {
  def main(args: Array[String]): Unit = {

    //Partie 1

//    val path = "/Users/jeremyigonin/Documents/Hemera/scala/CSV/src/main/scala/simplePackage/CsvFile.csv";
//    val array = readCSV(path);
//    val array2 = array.map(o => parseType(o.toString))
//    array2.foreach(f = o => print(o.getClass +" "+ o +"\n"));

    //Partie 2 :


    //Partie 3 :
    val output = new FileOutputStream("result-json.csv")
    Json2Csv.convert(new File(args(0)), output) match {
      case Right(nb) => println(s"$nb CSV lines written to 'result-json.csv'")
      case Left(e)  => println(s"Something bad happened $e")
    }
  }


  def csvFileToJson(filePath: String): String = {
    val inputCsvFile = new File(filePath)

    // if the csv has header, use setUseHeader(true)
    val csvSchema = CsvSchema.builder().setUseHeader(true).build()
    val csvMapper = new CsvMapper()

    // java.util.Map[String, String] identifies they key values type in JSON
    val readAll = csvMapper
      .readerFor(classOf[java.util.Map[String, String]])
      .`with`(csvSchema)
      .readValues(inputCsvFile)
      .readAll()

    val mapper = new ObjectMapper()

    // json return value
    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll)
  }



//  def ojbToJson(value: Any): Unit ={
//    if(value.getClass ==  simplePackage.Cat){
//      {
//        "name" : value.name,
//        "race" : value.race,
//        "age" : value.age,
//      }
//    }
//    if(value.getClass ==  simplePackage.Person){
//      {
//        "firstName" : value.firstName,
//        "lastName" : value.lastName,
//        "salary" : value.salary,
//        "numberOfChildren" : value.numberOfChildren,
//      }
//    }
//    if(value.getClass ==  simplePackage.Car){
//      {
//        "brand" : value.brand,
//        "countryOfBirth" : value.countryOfBirth,
//        "maxSpeed" : value.maxSpeed,
//        "speeds" : value.speeds,
//      }
//    }
//    if(value.getClass ==  simplePackage.Actor){
//      {
//        "name" : value.name,
//        "filmsPlayed" : value.filmsPlayed,
//      }
//    }
//    if(value.getClass ==  simplePackage.Film){
//      {
//        "mainActors" : value.mainActors,
//        "dateOfRelease" : value.dateOfRelease,
//      }
//    }
//  }

  def parseType(value : String)= {
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
