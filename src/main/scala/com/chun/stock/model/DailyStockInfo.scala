
package com.chun.stock.model

import java.io.Serializable

import scala.collection.mutable.ArrayBuffer

class DailyStockInfo(
    var date : String)
  extends Serializable {

  var openPrice : Float = 0
  var closePrice : Float = 0

  var actualVolume : Int = 0
  var avgVolume : Int = 0

  val optionChains : ArrayBuffer[OptionChain] = ArrayBuffer()
}
