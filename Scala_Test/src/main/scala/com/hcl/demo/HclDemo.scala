package com.hcl.demo

import java.util.Scanner

object HclDemo {
  
  def printNumber(n:Int,sep:String):String = {
    var list = List[Int]()
    for (i<- 1 to n ) yield list:+=i
    list.mkString(sep)
  }
  
  def calculateAOT(base:Double,height:Double) = (base*height)/2
  
 def main(args: Array[String]): Unit = {
     println("Code that prints the numbers 1,2,3,4,5,6,7,8,9,10 \n Please input number \n")
    val input =new Scanner(System.in)
    println("OUT PUT="+printNumber(input.nextInt(),","))
   println("\nInput to calulate area of a triangle \n Please enter base and hight\n")
    val scanner =new Scanner(System.in)
    val base =scanner.nextDouble()
    val hight=scanner.nextDouble()
    println("Area of a triangle="+calculateAOT(base,hight))
 } 
  
}