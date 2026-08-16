package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Replay
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Order
import com.example.model.OrderStatus
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
fun OrdersScreen(
  orders: List<Order>,
  onReorder: (Order) -> Unit,
  onStartShopping: () -> Unit,
  modifier: Modifier = Modifier
) {
  if (orders.isEmpty()) {
    Box(
      modifier = modifier
        .fillMaxSize()
        .padding(24.dp)
        .testTag("orders_empty_state"),
      contentAlignment = Alignment.Center
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Box(
          modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(MintBackground),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.ReceiptLong,
            contentDescription = null,
            tint = PrimaryGreen,
            modifier = Modifier.size(50.dp)
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = "No Orders Yet",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = TextPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "Place your first fresh grocery order today with 15-minute express delivery.",
          style = MaterialTheme.typography.bodyMedium,
          color = TextSecondary,
          textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
          onClick = onStartShopping,
          colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
          shape = RoundedCornerShape(14.dp)
        ) {
          Text("Browse Products")
        }
      }
    }
    return
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("orders_screen_root"),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
  ) {
    item {
      Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
          text = "My Grocery Orders",
          style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
          color = TextPrimary
        )
        Text(
          text = "Track current deliveries and past purchases",
          style = MaterialTheme.typography.bodyMedium,
          color = TextSecondary
        )
      }
    }

    items(orders, key = { it.id }) { order ->
      OrderCard(
        order = order,
        onReorder = { onReorder(order) },
        modifier = Modifier.padding(vertical = 8.dp)
      )
    }

    item {
      Spacer(modifier = Modifier.height(80.dp))
    }
  }
}

@Composable
fun OrderCard(
  order: Order,
  onReorder: () -> Unit,
  modifier: Modifier = Modifier
) {
  val isLiveOrder = order.status != OrderStatus.DELIVERED

  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      // Top row: ID, Date, Status Chip
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = order.orderNumber,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary
          )
          Text(
            text = order.placedTime,
            style = MaterialTheme.typography.bodySmall,
            color = TextMuted
          )
        }

        // Status Badge
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = when (order.status) {
            OrderStatus.PLACED, OrderStatus.PACKING -> MintBackground
            OrderStatus.ON_THE_WAY -> Color(0xFFFEF3C7)
            OrderStatus.DELIVERED -> Color(0xFFECFDF5)
          }
        ) {
          Text(
            text = order.status.title,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = when (order.status) {
              OrderStatus.PLACED, OrderStatus.PACKING -> PrimaryGreenDark
              OrderStatus.ON_THE_WAY -> Color(0xFFB45309)
              OrderStatus.DELIVERED -> PrimaryForest
            },
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Items horizontal preview
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        items(order.items) { item ->
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.width(160.dp)
          ) {
            Row(
              modifier = Modifier.padding(8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
              ) {
                GroceryProductVisual(
                  type = item.product.visualType,
                  modifier = Modifier.fillMaxSize(),
                  size = 36.dp
                )
              }
              Spacer(modifier = Modifier.width(8.dp))
              Column {
                Text(
                  text = item.product.name,
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = TextPrimary,
                  maxLines = 1
                )
                Text(
                  text = "Qty: ${item.quantity} • Rs. ${item.totalPrice}",
                  style = MaterialTheme.typography.labelSmall,
                  color = TextSecondary
                )
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      HorizontalDivider(color = BorderSubtle)

      Spacer(modifier = Modifier.height(10.dp))

      // Bottom Row: Total & Reorder CTA
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Total (${order.items.size} items)",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
          )
          Text(
            text = "Rs. ${order.total}",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
            color = PrimaryGreenDark
          )
        }

        OutlinedButton(
          onClick = onReorder,
          shape = RoundedCornerShape(12.dp),
          border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryGreen),
          colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryGreen)
        ) {
          Icon(
            imageVector = Icons.Default.Replay,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Reorder",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
          )
        }
      }
    }
  }
}
