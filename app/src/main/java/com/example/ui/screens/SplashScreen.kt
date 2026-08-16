package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmberGold
import com.example.ui.theme.MintBackground
import com.example.ui.theme.MintLight
import com.example.ui.theme.PrimaryForest
import com.example.ui.theme.PrimaryGreen
import com.example.ui.theme.PrimaryGreenDark
import com.example.ui.theme.SecondaryMint

@Composable
fun SplashScreen(
  onStartShopping: () -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "splashAnim")

  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.92f,
    targetValue = 1.08f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse"
  )

  val rotation by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 360f,
    animationSpec = infiniteRepeatable(
      animation = tween(12000, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "rotation"
  )

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            PrimaryForest,
            PrimaryGreenDark,
            PrimaryGreen
          )
        )
      )
      .testTag("splash_screen_root"),
    contentAlignment = Alignment.Center
  ) {
    // Ambient geometric background circles
    Canvas(modifier = Modifier.fillMaxSize()) {
      drawCircle(
        color = Color.White.copy(alpha = 0.04f),
        radius = size.width * 0.7f,
        center = Offset(size.width * 0.5f, size.height * 0.35f)
      )
      drawCircle(
        color = Color.White.copy(alpha = 0.03f),
        radius = size.width * 0.95f,
        center = Offset(size.width * 0.5f, size.height * 0.35f)
      )
    }

    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier.padding(horizontal = 32.dp)
    ) {
      // Logo Emblem Container with Pulse
      Box(
        modifier = Modifier
          .scale(pulseScale)
          .size(130.dp)
          .shadow(elevation = 20.dp, shape = CircleShape, spotColor = Color(0x66000000))
          .clip(CircleShape)
          .background(
            Brush.linearGradient(
              colors = listOf(Color.White, MintBackground)
            )
          ),
        contentAlignment = Alignment.Center
      ) {
        // Logo Vector Graphic
        Canvas(modifier = Modifier.size(76.dp)) {
          val w = size.width
          val h = size.height

          // Green grocery bag
          val bagPath = Path().apply {
            moveTo(w * 0.22f, h * 0.35f)
            lineTo(w * 0.78f, h * 0.35f)
            lineTo(w * 0.74f, h * 0.88f)
            lineTo(w * 0.26f, h * 0.88f)
            close()
          }
          drawPath(bagPath, PrimaryGreen)

          // Handle
          val handlePath = Path().apply {
            moveTo(w * 0.35f, h * 0.35f)
            cubicTo(w * 0.35f, h * 0.12f, w * 0.65f, h * 0.12f, w * 0.65f, h * 0.35f)
          }
          drawPath(
            handlePath,
            PrimaryForest,
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4.dp.toPx())
          )

          // Fast Lightning Bolt in Center of Bag
          val boltPath = Path().apply {
            moveTo(w * 0.52f, h * 0.42f)
            lineTo(w * 0.42f, h * 0.60f)
            lineTo(w * 0.50f, h * 0.60f)
            lineTo(w * 0.46f, h * 0.80f)
            lineTo(w * 0.58f, h * 0.58f)
            lineTo(w * 0.50f, h * 0.58f)
            close()
          }
          drawPath(boltPath, Color(0xFFFBBF24))
        }
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Brand Title
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "Omni",
          style = MaterialTheme.typography.headlineLarge.copy(
            fontSize = 34.sp,
            fontWeight = FontWeight.Black
          ),
          color = Color.White
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Deliver",
          style = MaterialTheme.typography.headlineLarge.copy(
            fontSize = 34.sp,
            fontWeight = FontWeight.Black
          ),
          color = AmberGold
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Tagline
      Text(
        text = "Fresh Groceries Delivered in 15 Minutes",
        style = MaterialTheme.typography.bodyMedium.copy(
          fontWeight = FontWeight.Medium,
          letterSpacing = 0.5.sp
        ),
        color = MintLight,
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(6.dp))

      // Urdu Tagline
      Text(
        text = "تازہ سودا سلف • تیز ترین ڈیلیوری",
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = Color.White.copy(alpha = 0.85f),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(36.dp))

      // Feature Highlights Row
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        SplashFeatureChip(
          icon = Icons.Default.Bolt,
          title = "15-Min Delivery"
        )
        SplashFeatureChip(
          icon = Icons.Default.LocalFlorist,
          title = "Farm Fresh"
        )
        SplashFeatureChip(
          icon = Icons.Default.DeliveryDining,
          title = "Cash on Delivery"
        )
      }

      Spacer(modifier = Modifier.height(44.dp))

      // Action Button to Enter App
      Button(
        onClick = onStartShopping,
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.White,
          contentColor = PrimaryForest
        ),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .shadow(12.dp, RoundedCornerShape(18.dp))
          .testTag("start_shopping_button")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = "Start Shopping",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Go",
            modifier = Modifier.size(20.dp)
          )
        }
      }
    }
  }
}

@Composable
private fun SplashFeatureChip(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  title: String
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.padding(4.dp)
  ) {
    Box(
      modifier = Modifier
        .size(44.dp)
        .clip(CircleShape)
        .background(Color.White.copy(alpha = 0.15f)),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = AmberGold,
        modifier = Modifier.size(22.dp)
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Text(
      text = title,
      style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
      color = Color.White.copy(alpha = 0.9f)
    )
  }
}
