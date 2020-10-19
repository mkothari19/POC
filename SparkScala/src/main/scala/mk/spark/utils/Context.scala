package mk.spark.utils

import org.apache.spark.sql.SparkSession

trait Context {
  lazy val spark=SparkSession.builder().appName("Spark-Demo")
     .master("local[*]")
     .config("spark.es.nodes","localhost")
    .config("spark.es.port","9200")
  .getOrCreate()
  
}