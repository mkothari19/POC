package mk.scala.stateful

class Bank {
  private var bal=0.0;
  
  def balace=bal
  def deposit(amount:Double)={
    bal+=amount
  }
  def withDrow(amount:Double)={
    if(amount<bal) bal-=amount  else "Insufficiant balance"
  }
  
}
object Main extends App{
  
  val bank=new Bank
  bank.deposit(1200.50)
 bank.withDrow(1200)
  println(bank.withDrow(500))
  println(bank.balace)
}