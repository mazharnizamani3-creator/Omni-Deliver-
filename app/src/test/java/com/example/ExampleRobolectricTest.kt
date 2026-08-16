package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.SampleGroceryData
import com.example.model.AppNavTab
import com.example.viewmodel.GroceryViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Omni Deliver", appName)
  }

  @Test
  fun `test grocery cart add and promo voucher`() {
    val viewModel = GroceryViewModel()
    val testProduct = SampleGroceryData.sampleProducts.first()

    // Add to cart
    viewModel.addToCart(testProduct, 2)
    val cartItems = viewModel.uiState.value.cartItems
    assertTrue(cartItems.containsKey(testProduct.id))
    assertEquals(2, cartItems[testProduct.id]?.quantity)

    // Apply promo voucher
    viewModel.applyPromoCode("OMNI100")
    assertEquals(100, viewModel.uiState.value.promoDiscount)
    assertNotNull(viewModel.uiState.value.promoSuccess)

    // Select tab
    viewModel.selectTab(AppNavTab.CART)
    assertEquals(AppNavTab.CART, viewModel.uiState.value.selectedTab)
  }
}
