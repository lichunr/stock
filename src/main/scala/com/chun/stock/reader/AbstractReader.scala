
package com.chun.stock.reader

abstract class AbstractReader (
    private var file : scala.io.Source)
  extends Serializable {

  val lines = file.getLines
}
