package mk.scala.companion

object CompanionTest {
  def main(args: Array[String]): Unit = {
    val p1=List(Person(Some("Kothari")),Person(Some("Maneesh")))
    println(p1)
     val p2=List(Person(Some("Kothari"),Some(30)),Person(Some("Maneesh"),Some(40)))
    println(p2)
    
    val s=new Student("Maneesh",40)
    val result=Student.unapply(s)
    println(result)
  }
}