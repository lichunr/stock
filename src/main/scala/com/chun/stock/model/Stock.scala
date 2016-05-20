
package com.chun.stock.model

import java.io.{FileOutputStream, ObjectOutputStream, Serializable}

import scala.io.Source

import com.chun.stock.reader.{OIReader, VolReader}

class Stock (
    var symbol : String)
  extends Serializable {
  
  var exchange : String = null
  var dailyStockInfoMap : Map[String, DailyStockInfo] = Map()
  var realTimeStockInfo : RealTimeStockInfo = null

  private def getDailyStockInfo(date : String) : DailyStockInfo = {
    var dailyStockInfo : DailyStockInfo = null;
    try {
      dailyStockInfo = dailyStockInfoMap(date)
    } catch {
      case e: Exception =>
        dailyStockInfo = new DailyStockInfo(date)
        dailyStockInfoMap += (date -> dailyStockInfo)
    }
    dailyStockInfo
  }

  def importOIData(record : OIRecord) : Unit = {
    val dailyStockInfo = getDailyStockInfo(record.date)
    val optionChain = new OptionChain(record.strikePrice, record.expireDate,
      record.callOpenInterest, record.putOpenInterest)
    dailyStockInfo.optionChains += optionChain
  }

  def importVolData(record : VolumeRecord) : Unit = {
    val dailyStockInfo = getDailyStockInfo(record.date)
    dailyStockInfo.avgVolume = record.avgVolume
    dailyStockInfo.actualVolume = record.actualVolume
  }
}

object StockApp {
  private var stockMap : Map[String, Stock] = Map()

  private def getStock(symbol : String) : Stock = {
    var stock : Stock = null
    try {
        stock = stockMap(symbol)
    } catch {
      case e: Exception =>
        stock = new Stock(symbol)
        stockMap += (symbol -> stock)
    }
    stock
  }

  private def appendOIInfo(filename : String) : Unit = {
    val file_source = Source.fromFile(filename)
    val reader = new OIReader(file_source)
    reader.next match {
      case Some(record) =>
        val stock = getStock(record.symbol)
        stock.importOIData(record)
      case None =>
    }
  }

  private def appendVolInfo(filename : String) : Unit = {
    val file_source = Source.fromFile(filename)
    val reader = new VolReader(file_source)
    reader.next match {
      case Some(record) =>
        val stock = getStock(record.symbol)
        stock.importVolData(record)
      case None =>
    }
  }

  private def outputStock(path : String, stock : Stock) : Unit = {
    val output = new ObjectOutputStream(new FileOutputStream(path + "/" + stock.symbol + ".data"));
    output.writeObject(stock)
    output.close()
  }

  def main(args: Array[String]) {
    appendOIInfo("/Users/chun/program/stock/src/oi.txt")
    appendVolInfo("/Users/chun/program/stock/src/vol.txt")
    val stock = stockMap("GOOG")
    outputStock("/Users/chun/program/stock/src", stock)
  }
}
