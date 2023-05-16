package scalaDesingPatterns.creational.singletonPattern.complex

abstract class EC2Con {
  val id: Int
  def connect: String
}

private[complex] class EC2MemOpt extends EC2Con {
  override val id = 1
  override def connect: String = s"singleton EC2MemOpt connection with session id ${id} established"
  def create: Unit = System.out.println(s"singleton EC2MemOpt connection with session id ${id} created")
  def destroy: Unit = System.out.println(s"singleton EC2MemOpt connection with session id ${id} destroyed")
}

private[complex] class EC2ComOpt extends EC2Con {
  override val id = 2
  override def connect: String = s"singleton EC2ComOpt connection with session id ${id} established"
  def create: Unit = System.out.println(s"singleton EC2ComOpt connection with session id ${id} created")
  def destroy: Unit = System.out.println(s"singleton EC2ComOpt connection with session id ${id} destroyed")
}

class EC2MemOptDelegator extends EC2Con {
  override val id = EC2ConnectionFactory.getEC2MemOptIns.id
  override def connect: String = EC2ConnectionFactory.getEC2MemOptIns.connect
}

class EC2ComOptDelegator extends EC2Con {
  override val id = EC2ConnectionFactory.getEC2ComOptIns.id
  override def connect: String = EC2ConnectionFactory.getEC2ComOptIns.connect
}

object EC2ConnectionFactory {
  private[complex] var ec2MemOptIns: Option[EC2MemOpt] = None
  private[complex] var ec2ComOptIns: Option[EC2ComOpt] = None

  def EC2MemOpt: EC2Con = {
    new EC2MemOptDelegator()
  }

  private[complex] def getEC2MemOptIns: EC2Con = {
    if (ec2MemOptIns.isEmpty) {
      this.ec2MemOptIns = Option(new EC2MemOpt)
      this.ec2MemOptIns.get.create
    }
    this.ec2MemOptIns.get
  }

  def destroyEC2MemOptIns= {
    if (this.ec2MemOptIns.nonEmpty) {
      this.ec2MemOptIns.get.destroy
      this.ec2MemOptIns = None
    }
  }

  def EC2ComOpt: EC2Con = {
    new EC2ComOptDelegator()
  }

  private[complex] def getEC2ComOptIns: EC2Con = {
    if (this.ec2ComOptIns.isEmpty) {
      this.ec2ComOptIns = Option(new EC2ComOpt)
      this.ec2ComOptIns.get.create
    }
    this.ec2ComOptIns.get
  }

  def destroyEC2ComOptIns = {
    if (this.ec2ComOptIns.nonEmpty) {
      this.ec2ComOptIns.get.destroy
      this.ec2ComOptIns = None
    }
  }
}

object ComplexClient extends App {
  System.out.println("Creating first connector")
  val ec2MemOptIns1: EC2Con = EC2ConnectionFactory.EC2MemOpt
  System.out.println("Getting the first connector again using reference, no instantiation")
  val ec2MemOptIns2: EC2Con = EC2ConnectionFactory.EC2MemOpt
  System.out.println("Establishing connection with references to the connector")
  System.out.println(ec2MemOptIns1.connect)
  System.out.println(ec2MemOptIns2.connect)
  System.out.println("Destroying connector")
  EC2ConnectionFactory.destroyEC2MemOptIns
  System.out.println("Trying to establish connection with destroyed connector")
  System.out.println(ec2MemOptIns1.connect)
}
