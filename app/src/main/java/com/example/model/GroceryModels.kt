package com.example.model

data class GroceryCategory(
  val id: String,
  val name: String,
  val urduName: String,
  val iconKey: String,
  val colorHex: Long,
  val itemCount: Int,
  val description: String = ""
)

data class Product(
  val id: String,
  val name: String,
  val urduName: String,
  val categoryId: String,
  val price: Int, // in PKR (Rs.)
  val originalPrice: Int? = null,
  val unit: String, // e.g. "1 kg", "500g", "1 Litre", "Pack of 12"
  val rating: Float = 4.8f,
  val reviewsCount: Int = 124,
  val description: String,
  val discountPercent: Int? = null,
  val isPopular: Boolean = false,
  val isDealOfTheDay: Boolean = false,
  val origin: String = "Farm Fresh Pakistan",
  val nutritionalHighlight: String = "100% Quality Inspected",
  val inStock: Boolean = true,
  val visualType: ProductVisualType = ProductVisualType.VEGETABLE
)

enum class ProductVisualType {
  TOMATO,
  ONION,
  POTATO,
  CHILLI,
  MANGO,
  BANANA,
  APPLE,
  MILK,
  YOGURT,
  BUTTER,
  EGGS,
  RICE,
  ATTA,
  DAAL,
  OIL,
  GHEE,
  SPICES_BIRYANI,
  TEA_TAPAL,
  BISCUITS,
  JAM,
  PICKLE,
  ROOH_AFZA,
  DETERGENT,
  HANDWASH,
  VEGETABLE,
  FRUIT,
  DAIRY,
  SNACK,
  GENERAL
}

data class CartItem(
  val product: Product,
  val quantity: Int
) {
  val totalPrice: Int get() = product.price * quantity
}

data class DeliverySlot(
  val id: String,
  val title: String,
  val subtitle: String,
  val isExpress: Boolean = false,
  val fee: Int = 99
)

enum class OrderStatus(val title: String, val step: Int, val description: String) {
  PLACED("Order Placed", 1, "Your grocery order has been confirmed by Omni Deliver"),
  PACKING("Packing Fresh Items", 2, "Items are being carefully picked and packed at the fulfillment hub"),
  ON_THE_WAY("Rider Out for Delivery", 3, "Rider is heading to your doorstep on express delivery route"),
  DELIVERED("Delivered", 4, "Delivered to your address with Cash on Delivery verified")
}

data class Order(
  val id: String,
  val orderNumber: String,
  val placedTime: String,
  val items: List<CartItem>,
  val subtotal: Int,
  val deliveryFee: Int,
  val discount: Int,
  val total: Int,
  val customerName: String,
  val customerPhone: String,
  val deliveryAddress: String,
  val city: String,
  val paymentMethod: String = "Cash on Delivery (COD)",
  val deliverySlot: String,
  val status: OrderStatus = OrderStatus.PLACED,
  val estimatedArrival: String = "20-30 Mins"
)

enum class AppNavTab(val title: String, val testTag: String) {
  HOME("Home", "nav_tab_home"),
  CATEGORIES("Categories", "nav_tab_categories"),
  CART("Cart", "nav_tab_cart"),
  ORDERS("Orders", "nav_tab_orders"),
  PROFILE("Profile", "nav_tab_profile")
}
