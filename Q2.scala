object Q2 {

  def getStudentInfoWithRetry(): (String, Int, Int, Double, Char) = {
    var isValid = true
    var studentInfo: (String, Int, Int, Double, Char) = ("", 0, 0, 0.0, 'D')

    while (isValid) {
      val name = scala.io.StdIn.readLine("Enter student's name: ")
      val marks = scala.io.StdIn.readLine("Enter marks: ").toInt
      val total = scala.io.StdIn.readLine("Enter last total marks: ").toInt

      val valiResult = validateInput(name, marks, total)

      if (valiResult._1) {
        studentInfo = getStudentInfo(name, marks, total)
        isValid = false
      } else {
        println(valiResult._2.getOrElse("Invalid input. Please try again."))
      }
    }

    studentInfo
  }
  def validateInput(name: String, marks: Int, total: Int): (Boolean, Option[String]) = {
    if (name.isEmpty) {
      (false, Some("Name cannot be empty."))
    } else if (marks < 0 || marks > total) {
      (false, Some("Marks should be positive and not exceed total possible marks."))
    } else {
      (true, None)
    }
  }

  def getStudentInfo(name: String, marks: Int, total: Int): (String, Int, Int, Double, Char) = {
    val per = (marks.toDouble / total) * 100
    val grade = per match {
      case p if p >= 90 => 'A'
      case p if p >= 75 => 'B'
      case p if p >= 50 => 'C'
      case _ => 'D'
    }

    (name, marks, total, per, grade)
  }

  def printStudentRecord(student: (String, Int, Int, Double, Char)): Unit = {
    val (name, marks, total, per, grade) = student
    println("------------------------------------")
    println(s"Student Name: $name")
    println(s"Marks Obtained: $marks")
    println(s"Total Possible Marks: $total")
    println(f"Percentage: $per%.2f%%")
    println(s"Grade: $grade")
  }

   def main(args: Array[String]): Unit = {
    val studentRecord = getStudentInfoWithRetry()
    printStudentRecord(studentRecord)
  }


  
}
