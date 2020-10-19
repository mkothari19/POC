package mk.spark.kafka.sstream
import mk.spark.utils.Context
object CityTemperature extends Context {
  
  val datastream=spark.readStream.text("")
  
}