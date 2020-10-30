package com.caseclass

trait Employee{
  def name:String
}

case class Bank(name:String="Maneesh",age:Int=80) extends Employee
case class IT(name:String,age:Int) extends Employee


object CaseClassDemo extends App{
 
  val start=executionStart
 
  //apply
 
  val bank=Bank("mayank",50)
  println(bank.name+" = " +bank.age)
  
  //unapply method
  val bank1=Bank("Maneesh",50)
  val it=IT("Deepak",50)
  
  println(printAbleString(bank1))
   println(printAbleString(it))
  
   println(executionStart-start)
  def printAbleString(e:Employee):String= e match{
    case Bank(name,age)=>s"$name = $age"
    case IT(name,age)=>s"$name = $age"
  }
 
  
}