package function

import scala.xml.Elem

object xmlParsing {

  //https://dzone.com/articles/working-with-xml-in-scala

  val xml: Elem = <greet>Hello</greet>

  xml.getClass

  //use the XPath query:
  val xmlDoc = <symbols>
    <symbol ticker="Cisco" ><units>100</units></symbol>
    <symbol ticker="Sandisk" ><units>315</units></symbol>
  </symbols>

  val children = xmlDoc\"symbol"
  val grandChildren = xmlDoc\\"units"


  val document = <languages>
    <language>Scala</language>
    <language>Java</language>
    <language>C++</language>
    <language>Kotlin</language>
  </languages>

  val progLan = document\"language"

  // iterate through the NodeSeq and print the node using text:
  progLan.foreach(v => println(v.text))

  // child method to get all the children of the root element:
  val progLan_2 = document.child

  // extract the attributes from XML elements
  val progLan_3 = <languages>
     <language platform = "jvm">Scala</language>
     <language platform = "jvm">Java</language>
     <language platform = "clr">C#</language>
     </languages>

  val attr = progLan_3 \\ "@platform"

  attr.foreach(v => println(v))

  // takes the path as a parameter and loads the XML file into memory.
  import scala.xml._
  val xmlFile = XML.load("path")
}
