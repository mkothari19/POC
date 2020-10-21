package mk.scala.collection

object Collections {
  def main(args: Array[String]): Unit = {
    /*
     * Seq with For and Foreach
     */
    val seq=Seq("1","2","3","4")
    var c=0;
    for(i<-seq) println(s"$i")
    seq.foreach(println)
    /*
     * List with For and Foreach
     */ 
    val list=List("MK","RK","PK")
    for(i<-list) println(i)
    list.foreach(println)
     /*
     * Map with For and Foreach
     */ 
    val map=Map("Maths"->1,"Physics"->2,"Bio"->4)
    
    for((books,id)<-map) println(s"$books = $id")
    
    map.foreach{case(books,id)=> println(s"$books =${id}") }
  }
}