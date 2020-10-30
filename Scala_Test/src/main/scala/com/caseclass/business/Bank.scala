package com.caseclass.business

import scala.util.Try

object Bank {
  
  def discount(amount:Double,per:Int):Try[Double]=Try{
    amount-(amount*per)/100
  }
   val data=Map("10"->"Maneesh","20"->"Rahul","30"->"Payal")
 
   def showEmployee(em:Option[String])= em match{
    case Some(name)=>name
    case None=>"?"
  }
   
  
  def main(args: Array[String]): Unit = {
   println(showEmployee( data get "10" ))
  }
}