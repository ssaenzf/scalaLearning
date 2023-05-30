package scalaDesingPatterns.creational.builderPattern.simple

class Book(val title: String, val author: String, val price: Double) {
  def getTitle(): String = this.title
  def getAuthor(): String = this.author
  def getPrice(): Double = this.price
}

class BookBuilder {
  private var title = ""
  private var author = ""
  private var price = 0.0

  def setTitle(title: String): BookBuilder = {
    this.title = title
    this
  }

  def setAuthor(author: String): BookBuilder = {
    this.author = author
    this
  }

  def setPrice(price: Double): BookBuilder = {
    this.price = price
    this
  }

  def build(): Book = new Book(this.title, this.author, this.price)
}

object Test extends App {
  val book: Book = new BookBuilder()
    .setTitle("The Hobbit")
    .setAuthor("J.R.R. Tolkien")
    .setPrice(10.5)
    .build()

  System.out.println(s"The book ${book.getTitle()} written by ${book.getAuthor()} got a price of ${book.getPrice()} euros")
}