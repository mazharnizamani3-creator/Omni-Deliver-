package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SampleGroceryData
import com.example.model.AppNavTab
import com.example.model.CartItem
import com.example.model.DeliverySlot
import com.example.model.GroceryCategory
import com.example.model.Order
import com.example.model.OrderStatus
import com.example.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

enum class SortOption(val title: String) {
  RECOMMENDED("Popular First"),
  PRICE_LOW_HIGH("Price: Low to High"),
  PRICE_HIGH_LOW("Price: High to Low"),
  DISCOUNT("Highest Discount")
}

data class GroceryUiState(
  val isSplash: Boolean = true,
  val selectedTab: AppNavTab = AppNavTab.HOME,
  val activeCategoryId: String? = null,
  val searchQuery: String = "",
  val sortOption: SortOption = SortOption.RECOMMENDED,
  val selectedProductForDetail: Product? = null,
  val isCheckoutScreen: Boolean = false,
  val activeConfirmationOrder: Order? = null,
  val cartItems: Map<String, CartItem> = emptyMap(),
  val appliedPromoCode: String = "",
  val promoDiscount: Int = 0,
  val promoError: String? = null,
  val promoSuccess: String? = null,
  val favoriteProductIds: Set<String> = emptySet(),
  val orders: List<Order> = SampleGroceryData.samplePastOrders,
  val isFastDeliveryBadgeVisible: Boolean = true,
  val toastMessage: String? = null,
  val isShimmerLoading: Boolean = false
)

class GroceryViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(GroceryUiState())
  val uiState: StateFlow<GroceryUiState> = _uiState.asStateFlow()

  // Checkout inputs state
  val customerName = MutableStateFlow("Ali Raza")
  val customerPhone = MutableStateFlow("0300-8472910")
  val deliveryAddress = MutableStateFlow("House 42-B, Street 7, Phase 5 DHA")
  val deliveryCity = MutableStateFlow("Lahore")
  val deliveryInstructions = MutableStateFlow("Leave with security guard if not answering bell")
  val selectedDeliverySlot = MutableStateFlow(SampleGroceryData.deliverySlots.first())
  val isPlacingOrder = MutableStateFlow(false)

  // Filtered products flow
  val displayedProducts: StateFlow<List<Product>> = combine(
    _uiState,
    SampleGroceryData.sampleProducts.let { MutableStateFlow(it) }
  ) { state, allProducts ->
    var list = allProducts

    // Filter by Category
    if (state.activeCategoryId != null) {
      list = list.filter { it.categoryId == state.activeCategoryId }
    }

    // Filter by Search Query
    if (state.searchQuery.isNotBlank()) {
      val query = state.searchQuery.trim().lowercase()
      list = list.filter {
        it.name.lowercase().contains(query) ||
          it.urduName.contains(query) ||
          it.description.lowercase().contains(query) ||
          it.origin.lowercase().contains(query)
      }
    }

    // Sorting
    when (state.sortOption) {
      SortOption.RECOMMENDED -> list.sortedByDescending { it.isPopular }
      SortOption.PRICE_LOW_HIGH -> list.sortedBy { it.price }
      SortOption.PRICE_HIGH_LOW -> list.sortedByDescending { it.price }
      SortOption.DISCOUNT -> list.sortedByDescending { it.discountPercent ?: 0 }
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SampleGroceryData.sampleProducts)

  init {
    // Initial splash auto-transition after 1.8 seconds
    viewModelScope.launch {
      delay(1800)
      _uiState.value = _uiState.value.copy(isSplash = false)
    }
  }

  fun dismissSplash() {
    _uiState.value = _uiState.value.copy(isSplash = false)
  }

  fun selectTab(tab: AppNavTab) {
    _uiState.value = _uiState.value.copy(
      selectedTab = tab,
      isCheckoutScreen = false,
      activeConfirmationOrder = null
    )
  }

  fun selectCategory(categoryId: String?) {
    _uiState.value = _uiState.value.copy(
      activeCategoryId = categoryId,
      selectedTab = AppNavTab.CATEGORIES
    )
  }

  fun updateSearchQuery(query: String) {
    _uiState.value = _uiState.value.copy(searchQuery = query)
  }

  fun setSortOption(option: SortOption) {
    _uiState.value = _uiState.value.copy(sortOption = option)
  }

  fun openProductDetail(product: Product) {
    _uiState.value = _uiState.value.copy(selectedProductForDetail = product)
  }

  fun closeProductDetail() {
    _uiState.value = _uiState.value.copy(selectedProductForDetail = null)
  }

  fun toggleFavorite(productId: String) {
    val current = _uiState.value.favoriteProductIds
    val updated = if (current.contains(productId)) current - productId else current + productId
    _uiState.value = _uiState.value.copy(favoriteProductIds = updated)
  }

  fun addToCart(product: Product, quantityDelta: Int = 1) {
    val currentCart = _uiState.value.cartItems.toMutableMap()
    val existing = currentCart[product.id]
    val newQuantity = (existing?.quantity ?: 0) + quantityDelta
    if (newQuantity <= 0) {
      currentCart.remove(product.id)
    } else {
      currentCart[product.id] = CartItem(product, newQuantity)
    }
    _uiState.value = _uiState.value.copy(cartItems = currentCart)
  }

  fun updateCartQuantity(productId: String, delta: Int) {
    val currentCart = _uiState.value.cartItems.toMutableMap()
    val existing = currentCart[productId] ?: return
    val newQty = existing.quantity + delta
    if (newQty <= 0) {
      currentCart.remove(productId)
    } else {
      currentCart[productId] = existing.copy(quantity = newQty)
    }
    _uiState.value = _uiState.value.copy(cartItems = currentCart)
  }

  fun removeFromCart(productId: String) {
    val currentCart = _uiState.value.cartItems.toMutableMap()
    currentCart.remove(productId)
    _uiState.value = _uiState.value.copy(cartItems = currentCart)
  }

  fun clearCart() {
    _uiState.value = _uiState.value.copy(
      cartItems = emptyMap(),
      appliedPromoCode = "",
      promoDiscount = 0,
      promoSuccess = null,
      promoError = null
    )
  }

  fun applyPromoCode(code: String) {
    val trimmed = code.trim().uppercase()
    when (trimmed) {
      "OMNI100" -> {
        _uiState.value = _uiState.value.copy(
          appliedPromoCode = trimmed,
          promoDiscount = 100,
          promoSuccess = "Promo applied! Rs. 100 discount added.",
          promoError = null
        )
      }
      "SAVE200" -> {
        _uiState.value = _uiState.value.copy(
          appliedPromoCode = trimmed,
          promoDiscount = 200,
          promoSuccess = "Super Saver! Rs. 200 discount added.",
          promoError = null
        )
      }
      "FREESHIP" -> {
        _uiState.value = _uiState.value.copy(
          appliedPromoCode = trimmed,
          promoDiscount = 99,
          promoSuccess = "Free delivery unlocked!",
          promoError = null
        )
      }
      else -> {
        _uiState.value = _uiState.value.copy(
          promoError = "Invalid code. Try 'OMNI100' or 'SAVE200'",
          promoSuccess = null
        )
      }
    }
  }

  fun openCheckout() {
    _uiState.value = _uiState.value.copy(isCheckoutScreen = true)
  }

  fun closeCheckout() {
    _uiState.value = _uiState.value.copy(isCheckoutScreen = false)
  }

  fun placeOrder(onSuccess: () -> Unit = {}) {
    if (isPlacingOrder.value) return
    isPlacingOrder.value = true

    viewModelScope.launch {
      delay(900) // Realistic order processing animation
      val itemsList = _uiState.value.cartItems.values.toList()
      val subtotal = itemsList.sumOf { it.totalPrice }
      val deliveryFee = if (subtotal >= 1499) 0 else selectedDeliverySlot.value.fee
      val discount = _uiState.value.promoDiscount
      val total = maxOf(0, subtotal + deliveryFee - discount)

      val dateFormat = SimpleDateFormat("h:mm a, d MMM", Locale.getDefault())
      val nowFormatted = dateFormat.format(Date())

      val newOrder = Order(
        id = UUID.randomUUID().toString(),
        orderNumber = "OMNI-${(10000..99999).random()}",
        placedTime = "Just now ($nowFormatted)",
        items = itemsList,
        subtotal = subtotal,
        deliveryFee = deliveryFee,
        discount = discount,
        total = total,
        customerName = customerName.value.ifBlank { "Valued Customer" },
        customerPhone = customerPhone.value.ifBlank { "0300-1234567" },
        deliveryAddress = deliveryAddress.value.ifBlank { "Main Gate, Street 1" },
        city = deliveryCity.value,
        paymentMethod = "Cash on Delivery (COD)",
        deliverySlot = selectedDeliverySlot.value.title,
        status = OrderStatus.PLACED,
        estimatedArrival = if (selectedDeliverySlot.value.isExpress) "15-25 Mins" else "Scheduled Time"
      )

      val updatedOrders = listOf(newOrder) + _uiState.value.orders

      _uiState.value = _uiState.value.copy(
        cartItems = emptyMap(),
        appliedPromoCode = "",
        promoDiscount = 0,
        orders = updatedOrders,
        isCheckoutScreen = false,
        activeConfirmationOrder = newOrder
      )
      isPlacingOrder.value = false
      onSuccess()
    }
  }

  fun dismissConfirmation() {
    _uiState.value = _uiState.value.copy(
      activeConfirmationOrder = null,
      selectedTab = AppNavTab.ORDERS
    )
  }

  fun triggerRefresh() {
    viewModelScope.launch {
      _uiState.value = _uiState.value.copy(isShimmerLoading = true)
      delay(800)
      _uiState.value = _uiState.value.copy(isShimmerLoading = false)
    }
  }

  fun reorderItems(order: Order) {
    val currentCart = _uiState.value.cartItems.toMutableMap()
    for (item in order.items) {
      val existing = currentCart[item.product.id]
      val qty = (existing?.quantity ?: 0) + item.quantity
      currentCart[item.product.id] = CartItem(item.product, qty)
    }
    _uiState.value = _uiState.value.copy(
      cartItems = currentCart,
      selectedTab = AppNavTab.CART
    )
  }
}
