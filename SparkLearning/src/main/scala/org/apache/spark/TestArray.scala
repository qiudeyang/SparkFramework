package org.apache.spark

/**
  * Created by qiudeyang on 19/07/16.
  */
object TestArray {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("testarray")
    val sc = new SparkContext(conf)
    val array = Array(Array("hello,world"),Array("Scala","Java"))
    println(array(0)(0))
    println(array(1)(0))
  }


}

