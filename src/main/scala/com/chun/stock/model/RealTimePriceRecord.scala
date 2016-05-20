
package com.chun.stock.model

import java.io.Serializable

class RealTimePriceRecord (
    var date : String,
    var time : String,
    var symbol : String,
    var price : Float,
    var useless1 : String,
    var useless2 : String,
    var avgVolume : Int,
    var actualVolume : Int)
  extends Serializable {
}
