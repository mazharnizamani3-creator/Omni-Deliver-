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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SampleGroceryData
import com.example.model.CartItem
import com.example.model.DeliverySlot
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
fun CheckoutScreen(
  customerName: String,
  onNameChange: (String) -> Unit,
  customerPhone: String,
  onPhoneChange: (String) -> Unit,
  deliveryAddress: String,
  onAddressChange: (String) -> Unit,
  deliveryCity: String,
  onCityChange: (String) -> Unit,
  deliveryInstructions: String,
  onInstructionsChange: (String) -> Unit,
  selectedSlot: DeliverySlot,
  onSelectSlot: (DeliverySlot) -> Unit,
  cartItems: Map<String, CartItem>,
  promoDiscount: Int,
  isPlacingOrder: Boolean,
  onBackClick: () -> Unit,
  onPlaceOrderClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val itemsList = cartItems.values.toList()
  val subtotal = itemsList.sumOf { it.totalPrice }
  val deliveryFee = if (subtotal >= 1499) 0 else selectedSlot.fee
  val total = maxOf(0, subtotal + deliveryFee - promoDiscount)

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("checkout_screen_root"),
    contentPadding = PaddingValues(bottom = 90.dp)
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
          verticalAlignment = Alignment.CenterVertically
        ) {
          IconButton(
            onClick = onBackClick,
            modifier = Modifier.testTag("checkout_back_btn")
          ) {
            Icon(
              imageVector = Icons.Default.ArrowBack,
              contentDescription = "Back",
              tint = TextPrimary
            )
          }

          Spacer(modifier = Modifier.width(8.dp))

          Column {
            Text(
              text = "Express Checkout",
              style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
            Text(
              text = "Pay with Cash on Delivery",
              style = MaterialTheme.typography.bodySmall,
              color = PrimaryGreenDark
            )
          }
        }
      }
    }

    // 1. Delivery Contact Details Card
    item {
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(MintLight),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = PrimaryForest,
                modifier = Modifier.size(18.dp)
              )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Contact & Recipient Info",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Full Name
          OutlinedTextField(
            value = customerName,
            onValueChange = onNameChange,
            label = { Text("Full Name") },
            placeholder = { Text("e.g. Ali Raza") },
            leadingIcon = {
              Icon(Icons.Default.Person, contentDescription = null, tint = PrimaryGreen)
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = PrimaryGreen,
              unfocusedBorderColor = BorderSubtle
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("checkout_name_input")
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Phone Number
          OutlinedTextField(
            value = customerPhone,
            onValueChange = onPhoneChange,
            label = { Text("Pakistani Mobile Number") },
            placeholder = { Text("0300-1234567") },
            leadingIcon = {
              Icon(Icons.Default.Phone, contentDescription = null, tint = PrimaryGreen)
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = PrimaryGreen,
              unfocusedBorderColor = BorderSubtle
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("checkout_phone_input")
          )
        }
      }
    }

    // 2. Delivery Address Card
    item {
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(MintLight),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = PrimaryForest,
                modifier = Modifier.size(18.dp)
              )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Delivery Address",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          // City Chips
          Text(
            text = "Select City",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = TextSecondary
          )
          Spacer(modifier = Modifier.height(6.dp))
          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            val cities = listOf("Lahore", "Karachi", "Islamabad", "Rawalpindi", "Faisalabad", "Multan")
            items(cities) { city ->
              val isSelected = city == deliveryCity
              Surface(
                shape = RoundedCornerShape(10.dp),
                color = if (isSelected) PrimaryGreen else MintBackground,
                modifier = Modifier.clickable { onCityChange(city) }
              ) {
                Text(
                  text = city,
                  style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                  ),
                  color = if (isSelected) Color.White else PrimaryForest,
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          // Street Address
          OutlinedTextField(
            value = deliveryAddress,
            onValueChange = onAddressChange,
            label = { Text("Complete Street Address / House / Flat / Block") },
            placeholder = { Text("House 42-B, Street 7, Phase 5 DHA") },
            leadingIcon = {
              Icon(Icons.Default.Home, contentDescription = null, tint = PrimaryGreen)
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = PrimaryGreen,
              unfocusedBorderColor = BorderSubtle
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("checkout_address_input")
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Delivery instructions
          OutlinedTextField(
            value = deliveryInstructions,
            onValueChange = onInstructionsChange,
            label = { Text("Rider Notes / Gate Instructions (Optional)") },
            placeholder = { Text("Call upon arrival, near mosque / commercial plaza") },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = PrimaryGreen,
              unfocusedBorderColor = BorderSubtle
            ),
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }

    // 3. Delivery Time Slot
    item {
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(MintLight),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = null,
                tint = PrimaryForest,
                modifier = Modifier.size(18.dp)
              )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Choose Delivery Time Slot",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          SampleGroceryData.deliverySlots.forEach { slot ->
            val isSelected = selectedSlot.id == slot.id
            Surface(
              shape = RoundedCornerShape(14.dp),
              color = if (isSelected) MintBackground else MaterialTheme.colorScheme.surface,
              border = androidx.compose.foundation.BorderStroke(
                if (isSelected) 1.5.dp else 1.dp,
                if (isSelected) PrimaryGreen else BorderSubtle
              ),
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { onSelectSlot(slot) }
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                RadioButton(
                  selected = isSelected,
                  onClick = { onSelectSlot(slot) },
                  colors = RadioButtonDefaults.colors(selectedColor = PrimaryGreen)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = slot.title,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = TextPrimary
                  )
                  Text(
                    text = slot.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                  )
                }

                Text(
                  text = if (subtotal >= 1499) "FREE" else "Rs. ${slot.fee}",
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = PrimaryGreenDark
                )
              }
            }
          }
        }
      }
    }

    // 4. Payment Method Card (Firmly Cash on Delivery)
    item {
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(MintLight),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Money,
                contentDescription = null,
                tint = PrimaryForest,
                modifier = Modifier.size(18.dp)
              )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Payment Method",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          Surface(
            shape = RoundedCornerShape(14.dp),
            color = MintBackground,
            border = androidx.compose.foundation.BorderStroke(1.5.dp, PrimaryGreen),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(14.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(CircleShape)
                  .background(PrimaryGreen),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.Check,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(20.dp)
                )
              }

              Spacer(modifier = Modifier.width(12.dp))

              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = "Cash on Delivery (COD)",
                  style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                  color = PrimaryForest
                )
                Text(
                  text = "Pay exact cash, JazzCash, or EasyPaisa upon doorstep arrival",
                  style = MaterialTheme.typography.bodySmall,
                  color = PrimaryGreenDark
                )
              }
            }
          }
        }
      }
    }

    // 5. Final Order Summary & Place Order CTA
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
            text = "Order Summary",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary
          )

          Spacer(modifier = Modifier.height(10.dp))

          itemsList.forEach { item ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "${item.quantity}x ${item.product.name}",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                maxLines = 1,
                modifier = Modifier.weight(1f)
              )
              Text(
                text = "Rs. ${item.totalPrice}",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
              )
            }
          }

          HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = BorderSubtle
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "Total Payable Amount",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
            Text(
              text = "Rs. $total",
              style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
              color = PrimaryGreenDark
            )
          }

          Spacer(modifier = Modifier.height(18.dp))

          Button(
            onClick = onPlaceOrderClick,
            enabled = !isPlacingOrder,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(56.dp)
              .shadow(8.dp, RoundedCornerShape(16.dp))
              .testTag("place_order_btn")
          ) {
            if (isPlacingOrder) {
              CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.5.dp
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text("Confirming with Omni Deliver...")
            } else {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
              ) {
                Icon(
                  imageVector = Icons.Default.DeliveryDining,
                  contentDescription = null,
                  modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "Place Order • Cash on Delivery",
                  style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
              }
            }
          }
        }
      }
    }
  }
}
