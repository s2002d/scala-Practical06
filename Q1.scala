object Q1 {
  case class Product(id: Int, name: String, quantity: Int, price: Double)

  
  val inven1: Map[Int, Product] = Map(
    001 -> Product(001, "Pen", 8, 25.00),
    002 -> Product(002, "Pencil", 9, 20.00),
    003 -> Product(003, "Box", 12, 50.00)
  )

  val inven2: Map[Int, Product] = Map(
    003 -> Product(003, "Box", 10, 80.00),
    004 -> Product(004, "Eraser", 9, 10.00),
    005 -> Product(005, "Book", 4, 100.00)
  )

  
  def getProductNames(inventory: Map[Int, Product]): List[String] = {
    inventory.values.map(_.name).toList
  }

  def calculateTotalValue(inventory: Map[Int, Product]): Double = {
  var sum = 0.0

  for (product <- inventory.values) {
    sum = sum + product.quantity * product.price
  }

  sum
}

  def isInventoryEmpty(inventory: Map[Int, Product]): Boolean = {
    inventory.isEmpty
  }

  def mergeInventories(inven1: Map[Int, Product], inven2: Map[Int, Product]): Map[Int, Product] = {
  var mergedInventory = inven1
  
  for ((key, product) <- inven2) {
    val updatedProduct = mergedInventory.get(key) match {
      case Some(existingProduct) =>
        val updatedQuantity = existingProduct.quantity + product.quantity
        val highestPrice = math.max(existingProduct.price, product.price)
        existingProduct.copy(quantity = updatedQuantity, price = highestPrice)
      case None =>
        product
    }
    mergedInventory = mergedInventory.updated(key, updatedProduct)
  }
  
  mergedInventory
}


  def checkProductExists(inventory: Map[Int, Product], key: Int): Unit = {
    inventory.get(key) match {
      case Some(product) => println(s"Product found: $product")
      case None => println(s"Product with Ikey $key not found.")
    }
  }


  def main(args: Array[String]): Unit = {
    val productNames = getProductNames(inven1)
    println(s"Product names: ${productNames.mkString(", ")}")

  
    val totalValue = calculateTotalValue(inven1)
    println(s"Total value of inventory1: ${totalValue}")

    val isEmpty = isInventoryEmpty(inven1)
    println(s"Is inventory1 empty? $isEmpty")

    val mergedInventory = mergeInventories(inven1, inven2)
    println(s"Merged Inventory: $mergedInventory")

    checkProductExists(inven2, 004)
  }
}
