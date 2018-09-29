package juns

import scala.io.Source
import scala.collection.mutable.Map

/**
 * @author ${user.name}
 */
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    println( "Hello World!" )
    println("concat arguments = " + foo(args))

    // road service key
    val serviceKey = Source.fromFile("C:\\Users\\INSAM\\IdeaProjects\\land\\src\\main\\scala\\juns\\service_key.txt").mkString

    // road raw data from api
    val api = new ApiExplorer()
    val real =api.getRealEstate("11110","201805", serviceKey)
//    println(real)
    //<거래금액>    66,500</거래금액><건축년도>2007</건축년도><년>2018</년><법정동> 필운동</법정동><아파트>두레엘리시안</아파트><월>5</월><일>11~20</일><전용면적>84.74</전용면적><지번>174-1</지번><지역코드>11110</지역코드><층>6</층></item><item>
    val xmlRaw = scala.xml.XML.loadString(real)
    println(xmlRaw)

    var resMap: Map[String, String] = Map()

    for{
      item <- xmlRaw \\ "item"
      aptName <- item \ "아파트"
      money <- item \ "거래금액"
    } yield {
//      println("아파트 명칭" + aptName + "거래 금액" + money)
      resMap += (aptName.toString() -> money.toString())
    }

    resMap.foreach(v => println(v))


    // Data preprocessing with spark core lib
    // analysis data


    // save nosql

    // visual

  }

}
