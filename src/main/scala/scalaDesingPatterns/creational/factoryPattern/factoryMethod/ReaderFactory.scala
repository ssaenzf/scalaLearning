package scalaDesingPatterns.creational.factoryPattern.factoryMethod

trait Factory {
  def apply(s: String): Reader
}

object ReaderFactory extends Factory {

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

abstract class Reader {
  var path: String
  def open(): Unit
}

private case class csvReader(var path: String) extends Reader {
  override def open(): Unit = System.out.println(s"csvReader opened csv on path ${path}")
}

private case class jsonReader(var path: String) extends Reader {
  override def open(): Unit = System.out.println(s"jsonReader opened json on path ${path}")
}

private case class parquetReader(var path: String) extends Reader {
  override def open(): Unit = System.out.println(s"parquetReader opened parquet on path ${path}")
}

object Test extends App {
  val csvReader = ReaderFactory("file.csv")
  csvReader.open()
  val jsonReader = ReaderFactory("file.json")
  jsonReader.open()
  val parquetReader = ReaderFactory("file.parquet")
  parquetReader.open()
}
