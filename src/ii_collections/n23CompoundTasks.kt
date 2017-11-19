package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> = customers.filter { customer ->
    customer.orders.flatMap { order -> order.products }
            .contains(product)
}.toSet()

// Return the most expensive product among all delivered products
// (use the Order.isDelivered flag)
fun Customer.getMostExpensiveDeliveredProduct(): Product? = orders.filter { it.isDelivered }
        .flatMap { order -> order.products }
        .maxBy { product -> product.price }

// Return the number of times the given product was ordered.
// Note: a customer may order the same product for several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int = customers.flatMap { customer -> customer.orders}
        .flatMap { order -> order.products }
        .filter { p -> p == product }
        .count()
