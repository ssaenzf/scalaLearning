package scalaDesingPatterns.creational.singletonPattern.simple

object EC2Con {
  val id: Int = 1
  def connect: String = s"singleton EC2 connection with session id ${id} stablished"
}

object SimpleClient extends App {
  System.out.println(EC2Con.connect)
}
