package scalaDesingPatterns.structural.delegate.inheritanceProblems.dummyPolymorphism

abstract class Animal {
  val name: String
  var location: Location
  def movDirection(x: Int, y: Int) = {
    this.location = Location(this.location.x + x, this.location.y +y)
  }
  def makeSound(burkSound: BurkSound): Unit
  def toString: String
}

case class Location(x: Int, y: Int)

abstract class AnimalSound {
  val obSound: String
}
class BurkSound extends AnimalSound {
  val obSound: String = "BurkSound"
}

class Dog(override val name: String, override var location: Location) extends Animal {
  override def makeSound(burkSound: BurkSound) = System.out.println(s"Dog ${name} made ${burkSound.obSound}")
  override def toString: String = s"Dog ${name} on location ${location.x}, ${location.y}"
}

class Fox(override val name: String, override var location: Location) extends Animal {
  override def makeSound(burkSound: BurkSound) = System.out.println(s"Fox ${name} made ${burkSound.obSound}")
  override def toString: String = s"Fox ${name} on location ${location.x}, ${location.y}"
}

object TestPolymorphism extends App {
  val dog: Dog = new Dog("Scooby Doo", Location(4, 2))
  val fox: Fox = new Fox("Robin Hood", Location(6, 4))
  val listAnimals: List[Animal] = List[Animal](dog, fox)
  listAnimals.foreach[Unit](animal => {
    System.out.println(animal.toString)
    animal.makeSound(new BurkSound)
  })
}