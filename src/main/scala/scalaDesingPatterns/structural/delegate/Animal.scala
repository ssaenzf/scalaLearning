package scalaDesingPatterns.structural.delegate

trait Animal {
  val name: String
  var location: Location
  def makeSound(): Unit
  def toString: String
}

case class Location(x: Int, y: Int)

class  MovAnimal {
  def movDirection(location: Location, x: Int, y: Int): Location = {
    Location(location.x + x, location.y + y)
  }
}

trait AnimalSound {
  val obSound: String
}

class BurkSound extends AnimalSound {
  val obSound: String = "BurkSound"
}

class SpongeSound extends AnimalSound {
  val obSound: String = "SpongeSound"
}

class Dog(override val name: String, override var location: Location) extends Animal {
  private val burkSound = new BurkSound
  private val movAnimal = new MovAnimal
  override def makeSound() = System.out.println(s"Dog ${name} made ${burkSound.obSound}")
  override def toString: String = s"Dog ${name} on location ${location.x}, ${location.y}"
  def movDirection(x: Int, y: Int): Unit = {this.location = movAnimal.movDirection(location, x, y)}
}

class Fox(override val name: String, override var location: Location) extends Animal {
  private val burkSound = new BurkSound
  private val movAnimal = new MovAnimal
  override def makeSound() = System.out.println(s"Fox ${name} made ${burkSound.obSound}")
  override def toString: String = s"Fox ${name} on location ${location.x}, ${location.y}"
  def movDirection(x: Int, y: Int) = {this.location = movAnimal.movDirection(location, x, y)}
}

class Sponge(override val name: String, override var location: Location) extends Animal {
  private val spongeSound = new SpongeSound
  override def makeSound() = System.out.println(s"Sponge ${name} made ${spongeSound.obSound}")
  override def toString: String = s"Sponge ${name} on location ${location.x}, ${location.y}"
}

object TestPolymorphism extends App {
  val dog: Dog = new Dog("Scooby Doo", Location(4, 2))
  val fox: Fox = new Fox("Robin Hood", Location(6, 4))
  val sponge: Sponge = new Sponge("Bob", Location(8, 6))

  val listAnimals: List[Animal] = List[Animal](dog, fox, sponge)
  listAnimals.foreach[Unit](animal => {
    System.out.println(animal.toString)
    animal.makeSound()
  })
  fox.movDirection(1, 1)
  System.out.println(fox.toString)
}