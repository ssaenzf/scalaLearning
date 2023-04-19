package scalaDesingPatterns.structural.delegate

class EC2ConDelegator(private val id: Int) {
  private val delegate = new EC2ConDelegate
  def connect: String = delegate.connect(id)
}

private[delegate] class EC2ConDelegate {
  def connect(id: Int): String = s"singleton EC2Con connection connection with session id ${id} established"
}

object Test extends App {
  val ec2Con = new EC2ConDelegator(1)
  System.out.println(ec2Con.connect)
}