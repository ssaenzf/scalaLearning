package scalaDesingPatterns.creational.builderPattern.typeSafe

sealed trait BookConstraint
sealed trait HasTitle extends BookConstraint
sealed trait HasAuthor extends BookConstraint

class Book(val title: String, val author: String, val price: Double)

class BookBuilder[SatisfiedConstraint <: BookConstraint] protected (val title: String, val author: String, val price: Double){
  protected def this() = this("", "", 0.0)

  def setTitle(title: String): BookBuilder[HasTitle] = {
    new BookBuilder[HasTitle](title, this.author, this.price)
  }

  def setAuthor(author: String)(implicit ev: SatisfiedConstraint =:= HasTitle): BookBuilder[HasAuthor] = {
    new BookBuilder[HasAuthor](this.title, author, this.price)
  }

  def setPrice(price: Double): BookBuilder[SatisfiedConstraint] = {
    new BookBuilder[SatisfiedConstraint](this.title, this.author, price)
  }

  def build()(implicit ev: SatisfiedConstraint =:= HasAuthor): Book = new Book(this.title, this.author, this.price)

}

object BookBuilder {
  def apply() = new BookBuilder
}

object Test extends App {
  val bookHobbit: Book = BookBuilder()
    .setTitle("The Hobbit")
    .setAuthor("J.R.R. Tolkien")
    .setPrice(10.5)
    .build()

  System.out.println(s"The book ${bookHobbit.title} written by ${bookHobbit.author} got a price of ${bookHobbit.price} euros")

  val bookNarnia: Book = BookBuilder()
    .setPrice(12.0)
    .setTitle("The Chronicles of Narnia")
    .setAuthor("C. S. Lewis")
    .build()

  System.out.println(s"The book ${bookNarnia.title} written by ${bookNarnia.author} got a price of ${bookNarnia.price} euros")
}