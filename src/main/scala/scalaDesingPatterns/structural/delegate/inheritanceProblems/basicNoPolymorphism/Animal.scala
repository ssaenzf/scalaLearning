package scalaDesingPatterns.structural.delegate.inheritanceProblems.basicNoPolymorphism

case class Location(x: Int, y: Int)

abstract class AnimalSound {
  val obSound: String
}

class BurkSound extends AnimalSound {
  val obSound: String = "BurkSound"
}

class Dog(val name: String, var location: Location) {
  def movDirection(x: Int, y: Int) = {
    this.location = new Location(this.location.x + x, this.location.y + y)
  }
  def makeSound(burkSound: BurkSound) = System.out.println(s"Dog ${name} made ${burkSound.obSound}")

  override def toString: String = s"Dog ${name} on location ${location.x}, ${location.y}"
}

class Fox(val name: String, var location: Location) {
  def movDirection(x: Int, y: Int) = {
    this.location = Location(this.location.x + x, this.location.y + y)
  }
  def makeSound(burkSound: BurkSound) = System.out.println(s"Fox ${name} made ${burkSound.obSound}")
  override def toString: String = s"Fox ${name} on location ${location.x}, ${location.y}"
}

object TestNonPolymorphism extends App {
  val dog: Dog = new Dog("Scooby Doo", Location(4, 2))
  val fox: Fox = new Fox("Robin Hood", Location(6, 4))
  System.out.println(dog.toString)
  dog.makeSound(new BurkSound)
  System.out.println(fox.toString)
  fox.makeSound(new BurkSound)
}
