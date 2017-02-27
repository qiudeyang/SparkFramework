package org.apache.spark

/**
  * Created by qiudeyang on 28/07/16.
  */
object TestList {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("testlist").setMaster("local")
    val sc = new SparkContext(conf)
    val list = List(List("hello","world"),List("scala","java"))
    println(list.head)
    println(list.tail)
    println(list(1).head)
    println(list(0).tail)
  }

}
