package org.apache.spark

/**
  * Created by qiudeyang on 28/07/16.
  */
object TestTuple {
  def main(args: Array[String]):Unit = {
    val conf = new SparkConf().setAppName("testtuple").setMaster("local")
    val sc = new SparkContext(conf)
    val tuple = (1,"hello,world",("Scala","Java"))
    println(tuple._1)
    println(tuple._3._1)
  }

}
