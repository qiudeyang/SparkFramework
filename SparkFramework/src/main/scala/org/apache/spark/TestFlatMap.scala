package org.apache.spark

/**
  * Created by qiudeyang on 28/07/16.
  */
object TestFlatMap {
  def main(args: Array[String]) {
    val list = List("Hello,world","Scala,Java")
    val result = list.flatMap(_.split(","))
    println(result)
  }

}
