package org.apache.spark

/**
  * Created by qiudeyang on 28/07/16.
  */
object TestMap {
  def main(args: Array[String]) {
    val list = List(1,2,3)
    val listToMap = list.map(_*2)
    println(listToMap)
  }

}
