
package com.chun.stock.reader

import com.chun.stock.model.OIRecord

class OIReader (
    file : scala.io.Source)
  extends AbstractReader(file) {

  def next() : Option[OIRecord] = {
    if (!lines.hasNext) {
      return None
    }
    val parts = lines.next().split('\t') map {_.trim()}
    val record = new OIRecord(parts(0), parts(1), parts(2),
      parts(3).toFloat, parts(4).toInt, parts(5).toInt)
    return Some(record)
  }
}
