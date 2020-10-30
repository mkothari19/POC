package com.caseclass

class Companion {
  var name:Option[String]=None
  var age:Option[Int]=None
  
  override def toString():String=s"$name $age"
}

object Companion{
  
  def apply(name:Option[String]):Companion={
    val c=new Companion
    c.name=name
    c
  }
  
  def apply(name:Option[String],age:Option[Int]):Companion={
    val c=new Companion
    c.age=age
    c.name=name
    c
  }
}