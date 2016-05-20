
package com.chun.stock.reader

import com.chun.stock.model.VolumeRecord

class VolReader (
    file : scala.io.Source)
  extends AbstractReader(file) {

  def next() : Option[VolumeRecord] = {
    if (!lines.hasNext) {
      return None
    }
    val parts = lines.next().split('\t') map {_.trim()}
    val record = new VolumeRecord(parts(0), parts(1), parts(2).toInt,
      parts(3).toInt, parts(4).toFloat)
    return Some(record)
  }
}
