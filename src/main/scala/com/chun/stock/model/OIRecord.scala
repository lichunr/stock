
package com.chun.stock.model

import java.io.Serializable

class OIRecord (
    var date : String,
    var symbol : String,
    var expireDate : String,
    var strikePrice : Float,
    var callOpenInterest : Int,
    var putOpenInterest : Int)
  extends Serializable {
}
