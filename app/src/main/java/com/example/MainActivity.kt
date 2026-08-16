package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.AppNavTab
import com.example.ui.components.OmniBottomNavigationBar
import com.example.ui.screens.CartScreen
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.CheckoutScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.OrderConfirmationScreen
import com.example.ui.screens.OrdersScreen
import com.example.ui.screens.ProductDetailSheet
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.theme.OmniDeliverTheme
import com.example.viewmodel.GroceryViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  private val viewModel: GroceryViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      OmniDeliverTheme {
        OmniDeliverApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun OmniDeliverApp(viewModel: GroceryViewModel) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val displayedProducts by viewModel.displayedProducts.collectAsStateWithLifecycle()

  val customerName by viewModel.customerName.collectAsStateWithLifecycle()
  val customerPhone by viewModel.customerPhone.collectAsStateWithLifecycle()
  val deliveryAddress by viewModel.deliveryAddress.collectAsStateWithLifecycle()
  val deliveryCity by viewModel.deliveryCity.collectAsStateWithLifecycle()
  val deliveryInstructions by viewModel.deliveryInstructions.collectAsStateWithLifecycle()
  val selectedSlot by viewModel.selectedDeliverySlot.collectAsStateWithLifecycle()
  val isPlacingOrder by viewModel.isPlacingOrder.collectAsStateWithLifecycle()

  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  // Handle system back button properly
  BackHandler(enabled = uiState.isCheckoutScreen || uiState.activeConfirmationOrder != null || uiState.activeCategoryId != null || uiState.selectedTab != AppNavTab.HOME) {
    when {
      uiState.activeConfirmationOrder != null -> viewModel.dismissConfirmation()
      uiState.isCheckoutScreen -> viewModel.closeCheckout()
      uiState.activeCategoryId != null -> viewModel.selectCategory(null)
      uiState.selectedTab != AppNavTab.HOME -> viewModel.selectTab(AppNavTab.HOME)
    }
  }

  // Splash Screen View
  if (uiState.isSplash) {
    SplashScreen(
      onStartShopping = { viewModel.dismissSplash() }
    )
    return
  }

  // Order Confirmation Full Screen Overlay
  if (uiState.activeConfirmationOrder != null) {
    OrderConfirmationScreen(
      order = uiState.activeConfirmationOrder!!,
      onViewOrders = { viewModel.dismissConfirmation() },
      onContinueShopping = {
        viewModel.dismissConfirmation()
        viewModel.selectTab(AppNavTab.HOME)
      }
    )
    return
  }

  val totalCartCount = uiState.cartItems.values.sumOf { it.quantity }

  Scaffold(
    snackbarHost = { SnackbarHost(snackbarHostState) },
    bottomBar = {
      if (!uiState.isCheckoutScreen) {
        OmniBottomNavigationBar(
          currentTab = uiState.selectedTab,
          cartCount = totalCartCount,
          onTabSelected = { tab -> viewModel.selectTab(tab) }
        )
      }
    },
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      if (uiState.isCheckoutScreen) {
        CheckoutScreen(
          customerName = customerName,
          onNameChange = { viewModel.customerName.value = it },
          customerPhone = customerPhone,
          onPhoneChange = { viewModel.customerPhone.value = it },
          deliveryAddress = deliveryAddress,
          onAddressChange = { viewModel.deliveryAddress.value = it },
          deliveryCity = deliveryCity,
          onCityChange = { viewModel.deliveryCity.value = it },
          deliveryInstructions = deliveryInstructions,
          onInstructionsChange = { viewModel.deliveryInstructions.value = it },
          selectedSlot = selectedSlot,
          onSelectSlot = { viewModel.selectedDeliverySlot.value = it },
          cartItems = uiState.cartItems,
          promoDiscount = uiState.promoDiscount,
          isPlacingOrder = isPlacingOrder,
          onBackClick = { viewModel.closeCheckout() },
          onPlaceOrderClick = {
            viewModel.placeOrder(
              onSuccess = {
                scope.launch {
                  snackbarHostState.showSnackbar("Order placed successfully via Cash on Delivery!")
                }
              }
            )
          }
        )
      } else {
        // Tab Crossfade transitions
        Crossfade(
          targetState = uiState.selectedTab,
          label = "tabTransition"
        ) { currentTab ->
          when (currentTab) {
            AppNavTab.HOME -> {
              HomeScreen(
                searchQuery = uiState.searchQuery,
                onSearchChange = { viewModel.updateSearchQuery(it) },
                onClearSearch = { viewModel.updateSearchQuery("") },
                products = displayedProducts,
                cartItems = uiState.cartItems,
                favoriteIds = uiState.favoriteProductIds,
                isLoading = uiState.isShimmerLoading,
                onProductClick = { viewModel.openProductDetail(it) },
                onAddToCart = { viewModel.addToCart(it, 1) },
                onIncrement = { prodId -> viewModel.updateCartQuantity(prodId, 1) },
                onDecrement = { prodId -> viewModel.updateCartQuantity(prodId, -1) },
                onToggleFavorite = { viewModel.toggleFavorite(it) },
                onCategorySelect = { catId -> viewModel.selectCategory(catId) },
                onViewAllCategories = { viewModel.selectTab(AppNavTab.CATEGORIES) },
                onApplyPromo = { code ->
                  viewModel.applyPromoCode(code)
                  scope.launch {
                    snackbarHostState.showSnackbar("Voucher $code applied to cart!")
                  }
                }
              )
            }

            AppNavTab.CATEGORIES -> {
              CategoriesScreen(
                activeCategoryId = uiState.activeCategoryId,
                onCategorySelect = { viewModel.selectCategory(it) },
                products = displayedProducts,
                cartItems = uiState.cartItems,
                favoriteIds = uiState.favoriteProductIds,
                onProductClick = { viewModel.openProductDetail(it) },
                onAddToCart = { viewModel.addToCart(it, 1) },
                onIncrement = { prodId -> viewModel.updateCartQuantity(prodId, 1) },
                onDecrement = { prodId -> viewModel.updateCartQuantity(prodId, -1) },
                onToggleFavorite = { viewModel.toggleFavorite(it) }
              )
            }

            AppNavTab.CART -> {
              CartScreen(
                cartItems = uiState.cartItems,
                promoCode = uiState.appliedPromoCode,
                promoDiscount = uiState.promoDiscount,
                promoSuccess = uiState.promoSuccess,
                promoError = uiState.promoError,
                onIncrement = { prodId -> viewModel.updateCartQuantity(prodId, 1) },
                onDecrement = { prodId -> viewModel.updateCartQuantity(prodId, -1) },
                onRemoveItem = { prodId -> viewModel.removeFromCart(prodId) },
                onClearCart = { viewModel.clearCart() },
                onApplyPromo = { code -> viewModel.applyPromoCode(code) },
                onProceedToCheckout = { viewModel.openCheckout() },
                onStartShopping = { viewModel.selectTab(AppNavTab.HOME) }
              )
            }

            AppNavTab.ORDERS -> {
              OrdersScreen(
                orders = uiState.orders,
                onReorder = { order ->
                  viewModel.reorderItems(order)
                  scope.launch {
                    snackbarHostState.showSnackbar("Items added back to your cart!")
                  }
                },
                onStartShopping = { viewModel.selectTab(AppNavTab.HOME) }
              )
            }

            AppNavTab.PROFILE -> {
              ProfileScreen(
                userName = customerName,
                userPhone = customerPhone
              )
            }
          }
        }
      }

      // Product Detail Bottom Sheet Popup
      uiState.selectedProductForDetail?.let { product ->
        ProductDetailSheet(
          product = product,
          currentCartQty = uiState.cartItems[product.id]?.quantity ?: 0,
          isFavorite = uiState.favoriteProductIds.contains(product.id),
          onDismiss = { viewModel.closeProductDetail() },
          onAddToCart = { qty ->
            val delta = qty - (uiState.cartItems[product.id]?.quantity ?: 0)
            viewModel.addToCart(product, delta)
            scope.launch {
              snackbarHostState.showSnackbar("${product.name} added to cart")
            }
          },
          onToggleFavorite = { viewModel.toggleFavorite(product.id) }
        )
      }
    }
  }
}
