
package com.chun.stock.model

import java.io.Serializable

class OptionChain (
    var strikePrice : Float,
    var expireDate : String,
    var callOpenInterest : Int,
    var putOpenInterest : Int)
  extends Serializable {
}
