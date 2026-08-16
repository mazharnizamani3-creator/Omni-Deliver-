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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun ProfileScreen(
  userName: String = "Ali Raza",
  userPhone: String = "0300-8472910",
  userEmail: String = "aliraza.pk@gmail.com",
  modifier: Modifier = Modifier
) {
  var notificationsEnabled by remember { mutableStateOf(true) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("profile_screen_root"),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
  ) {
    // User Info Header Card
    item {
      Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Avatar
          Box(
            modifier = Modifier
              .size(68.dp)
              .clip(CircleShape)
              .background(
                Brush.linearGradient(
                  colors = listOf(PrimaryGreen, PrimaryForest)
                )
              ),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "AR",
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
              ),
              color = Color.White
            )
          }

          Spacer(modifier = Modifier.width(16.dp))

          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = userName,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
              )
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFFFEF3C7)
              ) {
                Text(
                  text = "GOLD",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black),
                  color = Color(0xFFB45309),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
              text = userPhone,
              style = MaterialTheme.typography.bodyMedium,
              color = TextSecondary
            )

            Text(
              text = userEmail,
              style = MaterialTheme.typography.bodySmall,
              color = TextMuted
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))
    }

    // Wallet & Points Card
    item {
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryForest),
        modifier = Modifier
          .fillMaxWidth()
          .shadow(6.dp, RoundedCornerShape(20.dp))
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Omni Wallet Balance",
              style = MaterialTheme.typography.labelMedium,
              color = MintLight
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "Rs. 450",
              style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
              color = Color.White
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "120 Omni Reward Points",
              style = MaterialTheme.typography.bodySmall,
              color = AmberGold
            )
          }

          Box(
            modifier = Modifier
              .size(48.dp)
              .clip(CircleShape)
              .background(Color.White.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.AccountBalanceWallet,
              contentDescription = null,
              tint = AmberGold,
              modifier = Modifier.size(26.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))
    }

    // Account Options List
    item {
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
          ProfileMenuItem(
            icon = Icons.Default.LocationOn,
            title = "Saved Delivery Addresses",
            subtitle = "Home (DHA Phase 5), Office (Gulberg III)",
            onClick = {}
          )
          HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = BorderSubtle)

          ProfileMenuItem(
            icon = Icons.Default.Money,
            title = "Payment Preference",
            subtitle = "Cash on Delivery (Default)",
            onClick = {}
          )
          HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = BorderSubtle)

          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(CircleShape)
                  .background(MintBackground),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.Notifications,
                  contentDescription = null,
                  tint = PrimaryGreen,
                  modifier = Modifier.size(20.dp)
                )
              }
              Spacer(modifier = Modifier.width(12.dp))
              Column {
                Text(
                  text = "Order Notifications & SMS",
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                  color = TextPrimary
                )
                Text(
                  text = "Real-time rider and delivery updates",
                  style = MaterialTheme.typography.bodySmall,
                  color = TextSecondary
                )
              }
            }

            Switch(
              checked = notificationsEnabled,
              onCheckedChange = { notificationsEnabled = it },
              colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryGreen
              )
            )
          }

          HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = BorderSubtle)

          ProfileMenuItem(
            icon = Icons.Default.HeadsetMic,
            title = "24/7 Customer Support & Helpline",
            subtitle = "Call 042-111-OMNI (6664)",
            onClick = {}
          )

          HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = BorderSubtle)

          ProfileMenuItem(
            icon = Icons.Default.Shield,
            title = "Privacy Policy & Terms",
            subtitle = "Safe shopping & secure data guarantee",
            onClick = {}
          )
        }
      }

      Spacer(modifier = Modifier.height(20.dp))
    }

    // App Version Tag
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "Omni Deliver • Version 1.0.0",
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
          color = PrimaryGreenDark
        )
        Text(
          text = "Crafted for seamless grocery shopping in Pakistan",
          style = MaterialTheme.typography.bodySmall,
          color = TextMuted
        )
      }
      Spacer(modifier = Modifier.height(80.dp))
    }
  }
}

@Composable
private fun ProfileMenuItem(
  icon: ImageVector,
  title: String,
  subtitle: String,
  onClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .padding(horizontal = 16.dp, vertical = 12.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.weight(1f)
    ) {
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(CircleShape)
          .background(MintBackground),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = PrimaryGreen,
          modifier = Modifier.size(20.dp)
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column {
        Text(
          text = title,
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = TextPrimary
        )
        Text(
          text = subtitle,
          style = MaterialTheme.typography.bodySmall,
          color = TextSecondary
        )
      }
    }

    Icon(
      imageVector = Icons.Default.ChevronRight,
      contentDescription = null,
      tint = TextMuted,
      modifier = Modifier.size(20.dp)
    )
  }
}
