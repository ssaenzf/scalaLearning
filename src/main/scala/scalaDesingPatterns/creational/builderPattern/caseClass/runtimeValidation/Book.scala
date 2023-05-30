package scalaDesingPatterns.creational.builderPattern.caseClass.runtimeValidation

case class Book(title: String = "", author: String = "", price: Double = 0.0) {
  require(title != "", "A title it's required for the book")
  require(author != "", "An author it's required for the book")
}

object Test extends App {
  val book: Book = new Book("The Hobbit", "J.R.R. Tolkien", 10.5)
  System.out.println(s"The book ${book.title} written by ${book.author} got a price of ${book.price} euros")
  val anonymousBook: Book = new Book(title = "Book X", price = 10.5)
  System.out.println(s"The book ${anonymousBook.title} written by ${anonymousBook.author} got a price of ${anonymousBook.price} euros")
}
