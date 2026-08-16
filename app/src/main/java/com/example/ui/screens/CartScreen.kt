package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CartItem
import com.example.ui.components.GroceryProductVisual
import com.example.ui.theme.AmberGold
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.MintBackground
import com.example.ui.theme.MintLight
import com.example.ui.theme.PrimaryForest
import com.example.ui.theme.PrimaryGreen
import com.example.ui.theme.PrimaryGreenDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun CartScreen(
  cartItems: Map<String, CartItem>,
  promoCode: String,
  promoDiscount: Int,
  promoSuccess: String?,
  promoError: String?,
  onIncrement: (String) -> Unit,
  onDecrement: (String) -> Unit,
  onRemoveItem: (String) -> Unit,
  onClearCart: () -> Unit,
  onApplyPromo: (String) -> Unit,
  onProceedToCheckout: () -> Unit,
  onStartShopping: () -> Unit,
  modifier: Modifier = Modifier
) {
  val itemsList = cartItems.values.toList()
  val subtotal = itemsList.sumOf { it.totalPrice }
  val freeDeliveryThreshold = 1499
  val deliveryFee = if (subtotal >= freeDeliveryThreshold || subtotal == 0) 0 else 99
  val total = maxOf(0, subtotal + deliveryFee - promoDiscount)
  var voucherInput by remember { mutableStateOf("") }

  if (itemsList.isEmpty()) {
    // Empty Cart State
    Box(
      modifier = modifier
        .fillMaxSize()
        .padding(24.dp)
        .testTag("cart_empty_state"),
      contentAlignment = Alignment.Center
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Box(
          modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(MintBackground),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
            tint = PrimaryGreen,
            modifier = Modifier.size(60.dp)
          )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
          text = "Your Cart is Empty",
          style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
          color = TextPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "Looks like you haven't added fresh vegetables, dairy, or pantry essentials yet.",
          style = MaterialTheme.typography.bodyMedium,
          color = TextSecondary,
          textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
          onClick = onStartShopping,
          colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier
            .height(50.dp)
            .testTag("empty_cart_shop_btn")
        ) {
          Text(
            text = "Explore Groceries",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
    return
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("cart_screen_root"),
    contentPadding = PaddingValues(bottom = 100.dp)
  ) {
    // Header
    item {
      Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "My Grocery Cart",
              style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
            Text(
              text = "${itemsList.size} unique items (${itemsList.sumOf { it.quantity }} total items)",
              style = MaterialTheme.typography.bodyMedium,
              color = TextSecondary
            )
          }

          TextButton(
            onClick = onClearCart,
            modifier = Modifier.testTag("clear_cart_btn")
          ) {
            Text(
              text = "Clear All",
              color = Color(0xFFEF4444),
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
          }
        }
      }
    }

    // Free Delivery Milestone Bar
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MintBackground),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          val remainingForFree = freeDeliveryThreshold - subtotal
          val progress = (subtotal.toFloat() / freeDeliveryThreshold).coerceIn(0f, 1f)

          if (remainingForFree <= 0) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = PrimaryGreen,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "🎉 You've unlocked FREE Express Delivery!",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = PrimaryForest
              )
            }
          } else {
            Text(
              text = "Add Rs. $remainingForFree more for FREE Delivery",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
              color = PrimaryForest
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          LinearProgressIndicator(
            progress = { progress },
            color = PrimaryGreen,
            trackColor = MintLight,
            modifier = Modifier
              .fillMaxWidth()
              .height(6.dp)
              .clip(RoundedCornerShape(3.dp))
          )
        }
      }
    }

    // Cart Items List
    items(itemsList, key = { it.product.id }) { item ->
      CartItemCard(
        item = item,
        onIncrement = { onIncrement(item.product.id) },
        onDecrement = { onDecrement(item.product.id) },
        onRemove = { onRemoveItem(item.product.id) },
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
      )
    }

    // Promo Code Box
    item {
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 10.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.LocalOffer,
              contentDescription = null,
              tint = PrimaryGreen,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Promo Voucher Code",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            OutlinedTextField(
              value = voucherInput,
              onValueChange = { voucherInput = it },
              placeholder = { Text("Enter code (e.g. OMNI100)") },
              singleLine = true,
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = BorderSubtle
              ),
              modifier = Modifier
                .weight(1f)
                .testTag("promo_input_field")
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
              onClick = {
                if (voucherInput.isNotBlank()) {
                  onApplyPromo(voucherInput)
                }
              },
              colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier
                .height(52.dp)
                .testTag("apply_promo_btn")
            ) {
              Text("Apply")
            }
          }

          // Quick Code Suggestions
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            listOf("OMNI100", "SAVE200", "FREESHIP").forEach { code ->
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = MintBackground,
                border = androidx.compose.foundation.BorderStroke(1.dp, MintLight),
                modifier = Modifier.clickable {
                  voucherInput = code
                  onApplyPromo(code)
                }
              ) {
                Text(
                  text = code,
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = PrimaryGreenDark,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }
            }
          }

          if (promoSuccess != null) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = promoSuccess,
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
              color = PrimaryGreenDark
            )
          }

          if (promoError != null) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = promoError,
              style = MaterialTheme.typography.bodySmall,
              color = Color(0xFFEF4444)
            )
          }
        }
      }
    }

    // Bill Breakdown Summary
    item {
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "Bill Details",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary
          )

          Spacer(modifier = Modifier.height(12.dp))

          BillRow(title = "Item Subtotal", amount = "Rs. $subtotal")
          BillRow(
            title = "Delivery Fee",
            amount = if (deliveryFee == 0) "FREE" else "Rs. $deliveryFee",
            isHighlight = deliveryFee == 0
          )

          if (promoDiscount > 0) {
            BillRow(
              title = "Promo Discount ($promoCode)",
              amount = "-Rs. $promoDiscount",
              isHighlight = true
            )
          }

          HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = BorderSubtle
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "To Pay (Cash on Delivery)",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
              )
              Text(
                text = "Inclusive of all taxes",
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted
              )
            }

            Text(
              text = "Rs. $total",
              style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
              color = PrimaryGreenDark
            )
          }
        }
      }
    }

    // Checkout Action Button
    item {
      Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Button(
          onClick = onProceedToCheckout,
          colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .testTag("proceed_to_checkout_btn")
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Proceed to Checkout",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
              text = "Rs. $total →",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
          }
        }
      }
    }
  }
}

@Composable
private fun CartItemCard(
  item: CartItem,
  onIncrement: () -> Unit,
  onDecrement: () -> Unit,
  onRemove: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Visual
      Box(
        modifier = Modifier
          .size(68.dp)
          .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
      ) {
        GroceryProductVisual(
          type = item.product.visualType,
          modifier = Modifier.fillMaxSize(),
          size = 68.dp
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      // Info
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = item.product.name,
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = TextPrimary,
          maxLines = 1
        )
        Text(
          text = "${item.product.unit} • Rs. ${item.product.price} each",
          style = MaterialTheme.typography.bodySmall,
          color = TextSecondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Rs. ${item.totalPrice}",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = PrimaryGreenDark
        )
      }

      // Quantity Control Stepper
      Surface(
        shape = RoundedCornerShape(10.dp),
        color = MintBackground,
        border = androidx.compose.foundation.BorderStroke(1.dp, MintLight)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 2.dp)
        ) {
          IconButton(
            onClick = onDecrement,
            modifier = Modifier.size(28.dp)
          ) {
            Icon(
              imageVector = if (item.quantity == 1) Icons.Default.DeleteOutline else Icons.Default.Remove,
              contentDescription = "Minus",
              tint = if (item.quantity == 1) Color(0xFFEF4444) else PrimaryGreenDark,
              modifier = Modifier.size(16.dp)
            )
          }

          Text(
            text = "${item.quantity}",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = PrimaryForest,
            modifier = Modifier.padding(horizontal = 6.dp)
          )

          IconButton(
            onClick = onIncrement,
            modifier = Modifier.size(28.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Add,
              contentDescription = "Plus",
              tint = PrimaryGreenDark,
              modifier = Modifier.size(16.dp)
            )
          }
        }
      }
    }
  }
}

@Composable
private fun BillRow(
  title: String,
  amount: String,
  isHighlight: Boolean = false
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.bodyMedium,
      color = TextSecondary
    )
    Text(
      text = amount,
      style = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal
      ),
      color = if (isHighlight) PrimaryGreenDark else TextPrimary
    )
  }
}
