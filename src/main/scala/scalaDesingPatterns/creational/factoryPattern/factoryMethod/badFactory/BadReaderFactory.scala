package scalaDesingPatterns.creational.factoryPattern.factoryMethod.badFactory

trait Factory {
  def apply(s: String): Reader
}

object BadReaderFactory extends Factory {

  def apply(s: String): Reader = {
    var pos = s.lastIndexOf(".")
    if (pos < 0) {
      pos = 0
    }
    val endsWith = s.substring(pos)
    endsWith match {
      case ".csv" => csvReader(s)
      case ".json" => jsonReader(s)
      case ".parquet" => parquetReader(s)
      case _ => throw new RuntimeException("Unknown file type")
    }
  }
}

abstract class Options {
  def getOptions(): String
}

class csvOptions extends Options {
  override def getOptions(): String = "csv options specific for csv files"
}

class jsonOptions extends Options {
  override def getOptions(): String = "json options specific for json files"
}

class parquetOptions extends Options {
  override def getOptions(): String = "parquet options specific for parquet files"
}

abstract class Reader {
  var path: String
  def open(): Unit
  def options: Options
}

private case class csvReader(var path: String) extends Reader {
  override def open(): Unit = System.out.println(s"csvReader opened csv on path ${path} with ${options.getOptions}")
  override def options = new jsonOptions
}

private case class jsonReader(var path: String) extends Reader {
  override def open(): Unit = System.out.println(s"jsonReader opened json on path ${path} with ${options.getOptions}")
  override def options = new jsonOptions
}

private case class parquetReader(var path: String) extends Reader {
  override def open(): Unit = System.out.println(s"parquetReader opened parquet on path ${path} with ${options.getOptions}")
  override def options = new parquetOptions
}

object Test extends App {
  val csvReader = BadReaderFactory("file.csv")
  csvReader.open()
  val jsonReader = BadReaderFactory("file.json")
  jsonReader.open()
  val parquetReader = BadReaderFactory("file.parquet")
  parquetReader.open()
}
