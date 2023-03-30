package scalaDesingPatterns.creational.factoryPattern.abstractFactory

import scala.collection.mutable

abstract class AbstractFactory {
  var options: Options
  def reader(path: String): Reader
}

class CsvFactory extends AbstractFactory {
  override var options: Options = new CsvOptions
  override def reader(path: String): Reader = CsvReader(path)
}

class JsonFactory extends AbstractFactory {
  override var options: Options = new JsonOptions
  override def reader(path: String): Reader = JsonReader(path)
}

object FactoryProvider {
  private val default: AbstractFactory = new CsvFactory
  private val factories: mutable.HashMap[String, AbstractFactory] = mutable.HashMap("json"->new JsonFactory, "csv"->default)
  def factory = factories.getOrElse(System.getProperty("fileType"), default)
}

abstract class Options {
  def getOptions(): String
}

class CsvOptions extends Options {
  override def getOptions(): String = "csv options specific for csv files"
}

class JsonOptions extends Options {
  override def getOptions(): String = "json options specific for json files"
}

abstract class Reader {
  def open(options: Options): Unit
}

private case class CsvReader(private var path: String) extends Reader {
  override def open(options: Options): Unit = System.out.println(s"csvReader opened csv on path ${path} with ${options.getOptions}")
}

private case class JsonReader(private var path: String) extends Reader {
  override def open(options: Options): Unit = System.out.println(s"jsonReader opened json on path ${path} with ${options.getOptions}")
}

object Test extends App {
  val csvFactory = FactoryProvider.factory
  val options = csvFactory.options
  val reader = csvFactory.reader("path.csv")
  reader.open(options)
}