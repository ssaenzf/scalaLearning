package scalaDesingPatterns.creational.builderPattern.caseClass.noValidation

case class Book(title: String = "", author: String = "", price: Double = 0.0)

object Test extends App {
  val book: Book = new Book("The Hobbit", "J.R.R. Tolkien", 10.5)
  System.out.println(s"The book ${book.title} written by ${book.author} got a price of ${book.price} euros")
}