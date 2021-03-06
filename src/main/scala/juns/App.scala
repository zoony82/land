package juns

import scala.collection.mutable.Map
import scala.io.Source
import java.io.File
import java.io.PrintWriter
import scala.xml.{Elem, Node}
import org.apache.spark


/**
 * @author ${user.name}
 */
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    println( "land anslysis start!" )

    // road service key
    val serviceKey: String = Source.fromFile("C:\\Users\\INSAM\\IdeaProjects\\land\\src\\main\\scala\\juns\\service_key.txt").mkString

    // road raw data from api
    val api: ApiExplorer = new ApiExplorer()

    val realSet: Array[Int] = (201701 to 201712).toArray
    var resMap: Map[String, String] = Map()

    realSet.foreach { v =>
      val real: String =api.getRealEstate("11110", v.toString(), serviceKey)
      //<거래금액>    66,500</거래금액><건축년도>2007</건축년도><년>2018</년><법정동> 필운동</법정동><아파트>두레엘리시안</아파트><월>5</월><일>11~20</일><전용면적>84.74</전용면적><지번>174-1</지번><지역코드>11110</지역코드><층>6</층></item><item>
      val xmlRaw: Elem = scala.xml.XML.loadString(real)

      for{
        item: Node <- xmlRaw \\ "item"
        aptName: Node <- item \ "아파트"
        money: Node <- item \ "거래금액"
        month: Node <- item \ "월"
        day: Node <- item \ "일"
      } yield {
        //      println("아파트 명칭" + aptName + "거래 금액" + money)
        resMap += (v.toString() + aptName.toString().replace("아파트","") + month.toString().replace("월","") + day.toString().replace("일","") -> money.toString().replace("거래금액",""))
      }
      println("iter : " + v)
    }

    val writer: PrintWriter = new PrintWriter(new File("APT_Transaction_Price.csv"))

    resMap.foreach(v => writer.write(v._1.replace("<>",",").replace("</>","")+v._2.replace("<>",",").replace("</>","").replace(" ","") + "\n"))
    writer.close()

    // Data preprocessing with spark core lib
    // analysis data


    // save nosql

    // visual

  }

}
