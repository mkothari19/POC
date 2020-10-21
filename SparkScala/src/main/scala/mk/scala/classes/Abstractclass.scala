package mk.scala.classes

/* 
 * This will not work so abstract class concent is Scala
 * trait Employee(name:String){
  
}*/
abstract class Employee(name:String) {
  //Concrete method
   def printName():Unit=println(name)
   
   // abstract method
   def caculateSalay():Unit
  
}