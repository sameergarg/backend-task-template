import scala.io.StdIn.readLine

object Main {

  def main(args: Array[String]): Unit = {
    println("Hello!")
    println("Here's an example of the simplest way of reading user input:")
    val yourInput: String = readLine()
    println(s"You entered $yourInput.")
  }
}
