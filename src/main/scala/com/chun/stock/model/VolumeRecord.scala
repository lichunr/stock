
package com.chun.stock.model

import java.io.Serializable

class VolumeRecord (
    var date : String,
    var symbol : String,
    var avgVolume : Int,
    var actualVolume : Int,
    var price : Float)
  extends Serializable {
}
