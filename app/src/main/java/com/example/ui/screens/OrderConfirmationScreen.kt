package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Order
import com.example.model.OrderStatus
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
fun OrderConfirmationScreen(
  order: Order,
  onViewOrders: () -> Unit,
  onContinueShopping: () -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "confirmAnim")

  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.95f,
    targetValue = 1.08f,
    animationSpec = infiniteRepeatable(
      animation = tween(1000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse"
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("order_confirmation_screen"),
    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    item {
      Spacer(modifier = Modifier.height(16.dp))

      // Animated Glowing Checkmark Badge
      Box(
        modifier = Modifier
          .scale(pulseScale)
          .size(100.dp)
          .shadow(16.dp, CircleShape, spotColor = PrimaryGreen)
          .clip(CircleShape)
          .background(PrimaryGreen),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.Check,
          contentDescription = "Success",
          tint = Color.White,
          modifier = Modifier.size(54.dp)
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      Text(
        text = "Order Confirmed!",
        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Black),
        color = PrimaryForest
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "Shukriya! Your fresh groceries are being packed.",
        style = MaterialTheme.typography.bodyLarge,
        color = TextSecondary,
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(4.dp))

      Surface(
        shape = RoundedCornerShape(10.dp),
        color = MintBackground,
        border = androidx.compose.foundation.BorderStroke(1.dp, MintLight)
      ) {
        Text(
          text = "Order ID: ${order.orderNumber}",
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = PrimaryGreenDark,
          modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
        )
      }

      Spacer(modifier = Modifier.height(24.dp))
    }

    // Live Tracking Status Stepper Card
    item {
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.Bolt,
                contentDescription = null,
                tint = PrimaryGreen,
                modifier = Modifier.size(22.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Estimated Arrival",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
              )
            }

            Surface(
              shape = RoundedCornerShape(10.dp),
              color = Color(0xFFFEF3C7)
            ) {
              Text(
                text = "⚡ 15-25 Mins",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFFB45309),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Stepper Items
          TrackingStepItem(
            title = "Order Confirmed",
            subtitle = "Received at Dark Store Hub",
            isDone = true,
            isCurrent = false
          )
          TrackingStepItem(
            title = "Packing Fresh Groceries",
            subtitle = "Selecting highest quality produce",
            isDone = false,
            isCurrent = true
          )
          TrackingStepItem(
            title = "Rider Out for Delivery",
            subtitle = "Heading directly to your doorstep",
            isDone = false,
            isCurrent = false
          )
          TrackingStepItem(
            title = "Delivered & COD Received",
            subtitle = "Cash on Delivery payment",
            isDone = false,
            isCurrent = false,
            isLast = true
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))
    }

    // Delivery & Payment Summary Card
    item {
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Text(
            text = "Delivery Details",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary
          )

          Spacer(modifier = Modifier.height(10.dp))

          Row(verticalAlignment = Alignment.Top) {
            Icon(
              imageVector = Icons.Default.LocationOn,
              contentDescription = null,
              tint = PrimaryGreen,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
              Text(
                text = "${order.customerName} • ${order.customerPhone}",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
              )
              Text(
                text = "${order.deliveryAddress}, ${order.city}",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
              )
            }
          }

          HorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp),
            color = BorderSubtle
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "Payable on Delivery",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
              )
              Text(
                text = order.paymentMethod,
                style = MaterialTheme.typography.bodySmall,
                color = PrimaryGreenDark
              )
            }

            Text(
              text = "Rs. ${order.total}",
              style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
              color = PrimaryGreenDark
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(24.dp))
    }

    // Action Buttons
    item {
      Button(
        onClick = onViewOrders,
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(52.dp)
          .testTag("track_in_orders_btn")
      ) {
        Icon(
          imageVector = Icons.Default.ReceiptLong,
          contentDescription = null,
          modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Track in My Orders",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      OutlinedButton(
        onClick = onContinueShopping,
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, PrimaryGreen),
        modifier = Modifier
          .fillMaxWidth()
          .height(52.dp)
          .testTag("continue_shopping_btn")
      ) {
        Text(
          text = "Continue Shopping",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = PrimaryGreen
        )
      }

      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
private fun TrackingStepItem(
  title: String,
  subtitle: String,
  isDone: Boolean,
  isCurrent: Boolean,
  isLast: Boolean = false
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.Top
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Box(
        modifier = Modifier
          .size(24.dp)
          .clip(CircleShape)
          .background(
            when {
              isDone -> PrimaryGreen
              isCurrent -> AmberGold
              else -> Color(0xFFCBD5E1)
            }
          ),
        contentAlignment = Alignment.Center
      ) {
        if (isDone) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(14.dp)
          )
        } else if (isCurrent) {
          Box(
            modifier = Modifier
              .size(8.dp)
              .clip(CircleShape)
              .background(Color.White)
          )
        }
      }

      if (!isLast) {
        Box(
          modifier = Modifier
            .width(2.dp)
            .height(26.dp)
            .background(if (isDone) PrimaryGreen else Color(0xFFE2E8F0))
        )
      }
    }

    Spacer(modifier = Modifier.width(12.dp))

    Column(modifier = Modifier.padding(bottom = if (isLast) 0.dp else 12.dp)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(
          fontWeight = if (isDone || isCurrent) FontWeight.Bold else FontWeight.Normal
        ),
        color = if (isDone || isCurrent) TextPrimary else TextMuted
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = if (isCurrent) PrimaryGreenDark else TextSecondary
      )
    }
  }
}
