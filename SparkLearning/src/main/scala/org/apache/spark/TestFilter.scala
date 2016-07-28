package org.apache.spark

/**
  * Created by qiudeyang on 28/07/16.
  */
object TestFilter {
  def main(args: Array[String]) {
    val list = List(-2,-1,0,1,2)
    val result = list.filter(_>0)
    println(result)
  }

}
