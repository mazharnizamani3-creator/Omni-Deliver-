package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BreakfastDining
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.RiceBowl
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.model.ProductVisualType

@Composable
fun GroceryProductVisual(
  type: ProductVisualType,
  modifier: Modifier = Modifier,
  size: Dp = 100.dp
) {
  val (bgStart, bgEnd, accentColor) = when (type) {
    ProductVisualType.TOMATO -> Triple(Color(0xFFFFEBEB), Color(0xFFFFD5D5), Color(0xFFE11D48))
    ProductVisualType.ONION -> Triple(Color(0xFFFEF3C7), Color(0xFFFDE68A), Color(0xFFD97706))
    ProductVisualType.POTATO -> Triple(Color(0xFFF3E8FF), Color(0xFFE9D5FF), Color(0xFF9333EA))
    ProductVisualType.CHILLI -> Triple(Color(0xFFDCFCE7), Color(0xFFBBF7D0), Color(0xFF16A34A))
    ProductVisualType.MANGO -> Triple(Color(0xFFFEF08A), Color(0xFFFBBF24), Color(0xFFD97706))
    ProductVisualType.BANANA -> Triple(Color(0xFFFEF9C3), Color(0xFFFDE047), Color(0xFFCA8A04))
    ProductVisualType.APPLE -> Triple(Color(0xFFFFE4E6), Color(0xFFFECDD3), Color(0xFFE11D48))
    ProductVisualType.MILK -> Triple(Color(0xFFEFF6FF), Color(0xFFDBEAFE), Color(0xFF2563EB))
    ProductVisualType.YOGURT -> Triple(Color(0xFFF0FDF4), Color(0xFFDCFCE7), Color(0xFF10B981))
    ProductVisualType.BUTTER -> Triple(Color(0xFFFEF9C3), Color(0xFFFDE047), Color(0xFFEAB308))
    ProductVisualType.EGGS -> Triple(Color(0xFFFFFBEB), Color(0xFFFEF3C7), Color(0xFFF59E0B))
    ProductVisualType.RICE -> Triple(Color(0xFFF8FAFC), Color(0xFFE2E8F0), Color(0xFF475569))
    ProductVisualType.ATTA -> Triple(Color(0xFFFEF3C7), Color(0xFFFDE68A), Color(0xFFB45309))
    ProductVisualType.DAAL -> Triple(Color(0xFFFFFBEB), Color(0xFFFDE68A), Color(0xFFD97706))
    ProductVisualType.OIL -> Triple(Color(0xFFFEF9C3), Color(0xFFFDE047), Color(0xFFCA8A04))
    ProductVisualType.GHEE -> Triple(Color(0xFFFEF08A), Color(0xFFFBBF24), Color(0xFFB45309))
    ProductVisualType.SPICES_BIRYANI -> Triple(Color(0xFFFFEDD5), Color(0xFFFED7AA), Color(0xFFEA580C))
    ProductVisualType.TEA_TAPAL -> Triple(Color(0xFFF5F3FF), Color(0xFFDDD6FE), Color(0xFF7C3AED))
    ProductVisualType.BISCUITS -> Triple(Color(0xFFFEF3C7), Color(0xFFFDE68A), Color(0xFFD97706))
    ProductVisualType.JAM -> Triple(Color(0xFFFFE4E6), Color(0xFFFECDD3), Color(0xFFE11D48))
    ProductVisualType.PICKLE -> Triple(Color(0xFFFEF9C3), Color(0xFFD9F99D), Color(0xFF65A30D))
    ProductVisualType.ROOH_AFZA -> Triple(Color(0xFFFFE4E6), Color(0xFFFECDD3), Color(0xFFBE123C))
    ProductVisualType.DETERGENT -> Triple(Color(0xFFECFEFF), Color(0xFFCFFAFE), Color(0xFF0891B2))
    ProductVisualType.HANDWASH -> Triple(Color(0xFFECFDF5), Color(0xFFA7F3D0), Color(0xFF059669))
    ProductVisualType.VEGETABLE -> Triple(Color(0xFFDCFCE7), Color(0xFFBBF7D0), Color(0xFF16A34A))
    ProductVisualType.FRUIT -> Triple(Color(0xFFFEF3C7), Color(0xFFFDE68A), Color(0xFFD97706))
    ProductVisualType.DAIRY -> Triple(Color(0xFFEFF6FF), Color(0xFFDBEAFE), Color(0xFF3B82F6))
    ProductVisualType.SNACK -> Triple(Color(0xFFFDF2F8), Color(0xFFFCE7F3), Color(0xFFDB2777))
    ProductVisualType.GENERAL -> Triple(Color(0xFFF1F5F9), Color(0xFFE2E8F0), Color(0xFF64748B))
  }

  Box(
    modifier = modifier
      .size(size)
      .clip(RoundedCornerShape(16.dp))
      .background(
        Brush.radialGradient(
          colors = listOf(bgStart, bgEnd),
          center = Offset.Unspecified,
          radius = 120f
        )
      ),
    contentAlignment = Alignment.Center
  ) {
    // Background decorative ring/shadow
    Canvas(modifier = Modifier.fillMaxSize()) {
      drawCircle(
        color = accentColor.copy(alpha = 0.08f),
        radius = size.toPx() * 0.38f,
        center = Offset(this.size.width / 2f, this.size.height / 2f + 4.dp.toPx())
      )
    }

    // Centered product vector rendering
    when (type) {
      ProductVisualType.TOMATO -> TomatoVisual(size = size * 0.65f)
      ProductVisualType.ONION -> OnionVisual(size = size * 0.65f)
      ProductVisualType.POTATO -> PotatoVisual(size = size * 0.65f)
      ProductVisualType.CHILLI -> ChilliVisual(size = size * 0.65f)
      ProductVisualType.MANGO -> MangoVisual(size = size * 0.65f)
      ProductVisualType.BANANA -> BananaVisual(size = size * 0.65f)
      ProductVisualType.MILK -> MilkVisual(size = size * 0.65f)
      ProductVisualType.EGGS -> EggsVisual(size = size * 0.65f)
      ProductVisualType.BUTTER -> ButterVisual(size = size * 0.65f)
      ProductVisualType.RICE -> RiceVisual(size = size * 0.65f)
      ProductVisualType.ATTA -> AttaVisual(size = size * 0.65f)
      ProductVisualType.DAAL -> DaalVisual(size = size * 0.65f)
      ProductVisualType.OIL -> OilVisual(size = size * 0.65f)
      ProductVisualType.GHEE -> GheeVisual(size = size * 0.65f)
      ProductVisualType.SPICES_BIRYANI -> BiryaniSpiceVisual(size = size * 0.65f)
      ProductVisualType.TEA_TAPAL -> TapalTeaVisual(size = size * 0.65f)
      ProductVisualType.ROOH_AFZA -> RoohAfzaVisual(size = size * 0.65f)
      ProductVisualType.BISCUITS -> BiscuitVisual(size = size * 0.65f)
      ProductVisualType.JAM -> JamVisual(size = size * 0.65f)
      ProductVisualType.PICKLE -> PickleVisual(size = size * 0.65f)
      ProductVisualType.DETERGENT -> DetergentVisual(size = size * 0.65f)
      ProductVisualType.HANDWASH -> HandwashVisual(size = size * 0.65f)
      else -> {
        Icon(
          imageVector = Icons.Default.LocalGroceryStore,
          contentDescription = null,
          tint = accentColor,
          modifier = Modifier.size(size * 0.5f)
        )
      }
    }
  }
}

@Composable
private fun TomatoVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Tomato Body (Rich Red with highlight)
    drawOval(
      brush = Brush.radialGradient(
        colors = listOf(Color(0xFFFF5252), Color(0xFFD32F2F)),
        center = Offset(w * 0.4f, h * 0.45f),
        radius = w * 0.45f
      ),
      topLeft = Offset(w * 0.1f, h * 0.2f),
      size = Size(w * 0.8f, h * 0.75f)
    )

    // Leaf stem at top (Green)
    val leafPath = Path().apply {
      moveTo(w * 0.5f, h * 0.22f)
      quadraticTo(w * 0.3f, h * 0.08f, w * 0.25f, h * 0.18f)
      quadraticTo(w * 0.4f, h * 0.24f, w * 0.5f, h * 0.22f)
      quadraticTo(w * 0.7f, h * 0.08f, w * 0.75f, h * 0.18f)
      quadraticTo(w * 0.6f, h * 0.24f, w * 0.5f, h * 0.22f)
      close()
    }
    drawPath(leafPath, Color(0xFF2E7D32))

    // Tiny stem
    drawLine(
      color = Color(0xFF1B5E20),
      start = Offset(w * 0.5f, h * 0.22f),
      end = Offset(w * 0.5f, h * 0.1f),
      strokeWidth = 3.dp.toPx()
    )

    // Specular shine
    drawOval(
      color = Color.White.copy(alpha = 0.5f),
      topLeft = Offset(w * 0.25f, h * 0.32f),
      size = Size(w * 0.15f, h * 0.1f)
    )
  }
}

@Composable
private fun MangoVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    val mangoPath = Path().apply {
      moveTo(w * 0.5f, h * 0.15f)
      cubicTo(w * 0.85f, h * 0.2f, w * 0.95f, h * 0.65f, w * 0.6f, h * 0.9f)
      cubicTo(w * 0.45f, h * 0.95f, w * 0.25f, h * 0.8f, w * 0.2f, h * 0.55f)
      cubicTo(w * 0.15f, h * 0.3f, w * 0.35f, h * 0.15f, w * 0.5f, h * 0.15f)
      close()
    }

    drawPath(
      path = mangoPath,
      brush = Brush.linearGradient(
        colors = listOf(Color(0xFFFFD54F), Color(0xFFFFB300), Color(0xFFFF8F00)),
        start = Offset(w * 0.3f, h * 0.2f),
        end = Offset(w * 0.8f, h * 0.85f)
      )
    )

    // Mango green leaf
    val leafPath = Path().apply {
      moveTo(w * 0.5f, h * 0.15f)
      quadraticTo(w * 0.65f, h * 0.05f, w * 0.8f, h * 0.1f)
      quadraticTo(w * 0.65f, h * 0.2f, w * 0.5f, h * 0.15f)
      close()
    }
    drawPath(leafPath, Color(0xFF43A047))
  }
}

@Composable
private fun MilkVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Milk carton body
    val bodyPath = Path().apply {
      moveTo(w * 0.25f, h * 0.35f)
      lineTo(w * 0.75f, h * 0.35f)
      lineTo(w * 0.78f, h * 0.9f)
      lineTo(w * 0.22f, h * 0.9f)
      close()
    }
    drawPath(
      path = bodyPath,
      brush = Brush.verticalGradient(
        colors = listOf(Color(0xFF1E88E5), Color(0xFF1565C0))
      )
    )

    // Milk carton top roof
    val topPath = Path().apply {
      moveTo(w * 0.35f, h * 0.18f)
      lineTo(w * 0.65f, h * 0.18f)
      lineTo(w * 0.75f, h * 0.35f)
      lineTo(w * 0.25f, h * 0.35f)
      close()
    }
    drawPath(topPath, Color(0xFF0D47A1))

    // Milk label stripe
    drawRect(
      color = Color.White,
      topLeft = Offset(w * 0.24f, h * 0.5f),
      size = Size(w * 0.52f, h * 0.25f)
    )

    // Fresh drop on label
    drawCircle(
      color = Color(0xFF0D47A1),
      radius = w * 0.08f,
      center = Offset(w * 0.5f, h * 0.62f)
    )
  }
}

@Composable
private fun EggsVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Tray base
    drawRoundRect(
      color = Color(0xFFBCAAA4),
      topLeft = Offset(w * 0.1f, h * 0.65f),
      size = Size(w * 0.8f, h * 0.25f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
    )

    // Left Egg
    drawOval(
      brush = Brush.radialGradient(
        colors = listOf(Color(0xFFFFECB3), Color(0xFFFFD54F), Color(0xFFFFB74D)),
        center = Offset(w * 0.35f, h * 0.45f),
        radius = w * 0.25f
      ),
      topLeft = Offset(w * 0.18f, h * 0.25f),
      size = Size(w * 0.32f, h * 0.48f)
    )

    // Right Egg
    drawOval(
      brush = Brush.radialGradient(
        colors = listOf(Color(0xFFFFF8E1), Color(0xFFFFE082), Color(0xFFFFA726)),
        center = Offset(w * 0.62f, h * 0.42f),
        radius = w * 0.25f
      ),
      topLeft = Offset(w * 0.5f, h * 0.22f),
      size = Size(w * 0.32f, h * 0.5f)
    )
  }
}

@Composable
private fun OnionVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Onion bulb
    val onionPath = Path().apply {
      moveTo(w * 0.5f, h * 0.15f)
      cubicTo(w * 0.85f, h * 0.25f, w * 0.88f, h * 0.75f, w * 0.5f, h * 0.88f)
      cubicTo(w * 0.12f, h * 0.75f, w * 0.15f, h * 0.25f, w * 0.5f, h * 0.15f)
      close()
    }
    drawPath(
      path = onionPath,
      brush = Brush.radialGradient(
        colors = listOf(Color(0xFFFFCC80), Color(0xFFFB8C00), Color(0xFFE65100)),
        center = Offset(w * 0.45f, h * 0.5f),
        radius = w * 0.4f
      )
    )

    // Top sprout
    drawLine(
      color = Color(0xFF43A047),
      start = Offset(w * 0.5f, h * 0.15f),
      end = Offset(w * 0.45f, h * 0.05f),
      strokeWidth = 2.5.dp.toPx()
    )
  }
}

@Composable
private fun PotatoVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    drawOval(
      brush = Brush.radialGradient(
        colors = listOf(Color(0xFFD7CCC8), Color(0xFFBCAAA4), Color(0xFF8D6E63)),
        center = Offset(w * 0.4f, h * 0.45f),
        radius = w * 0.45f
      ),
      topLeft = Offset(w * 0.15f, h * 0.25f),
      size = Size(w * 0.7f, h * 0.55f)
    )

    // Dots/eyes
    drawCircle(Color(0xFF5D4037), radius = 2.dp.toPx(), center = Offset(w * 0.35f, h * 0.45f))
    drawCircle(Color(0xFF5D4037), radius = 2.dp.toPx(), center = Offset(w * 0.65f, h * 0.4f))
    drawCircle(Color(0xFF5D4037), radius = 2.dp.toPx(), center = Offset(w * 0.5f, h * 0.65f))
  }
}

@Composable
private fun ChilliVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    val chilliPath = Path().apply {
      moveTo(w * 0.65f, h * 0.2f)
      cubicTo(w * 0.8f, h * 0.45f, w * 0.6f, h * 0.85f, w * 0.3f, h * 0.85f)
      cubicTo(w * 0.4f, h * 0.65f, w * 0.5f, h * 0.4f, w * 0.55f, h * 0.2f)
      close()
    }
    drawPath(
      path = chilliPath,
      brush = Brush.linearGradient(
        colors = listOf(Color(0xFF43A047), Color(0xFF2E7D32), Color(0xFF1B5E20))
      )
    )

    // Stem
    drawLine(
      color = Color(0xFF1B5E20),
      start = Offset(w * 0.6f, h * 0.2f),
      end = Offset(w * 0.7f, h * 0.08f),
      strokeWidth = 3.dp.toPx()
    )
  }
}

@Composable
private fun BananaVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    val bananaPath = Path().apply {
      moveTo(w * 0.2f, h * 0.75f)
      cubicTo(w * 0.3f, h * 0.35f, w * 0.7f, h * 0.25f, w * 0.85f, h * 0.3f)
      cubicTo(w * 0.7f, h * 0.45f, w * 0.4f, h * 0.6f, w * 0.2f, h * 0.75f)
      close()
    }
    drawPath(
      path = bananaPath,
      brush = Brush.linearGradient(
        colors = listOf(Color(0xFFFFF176), Color(0xFFFFEE58), Color(0xFFFBC02D))
      )
    )
    // Tips
    drawCircle(Color(0xFF5D4037), radius = 3.dp.toPx(), center = Offset(w * 0.2f, h * 0.75f))
    drawCircle(Color(0xFF388E3C), radius = 3.dp.toPx(), center = Offset(w * 0.85f, h * 0.3f))
  }
}

@Composable
private fun RiceVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Rice sack body
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFFE0E0E0), Color(0xFFBDBDBD))),
      topLeft = Offset(w * 0.2f, h * 0.3f),
      size = Size(w * 0.6f, h * 0.6f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
    )
    // Blue Seal Banner
    drawRect(
      color = Color(0xFF1565C0),
      topLeft = Offset(w * 0.2f, h * 0.45f),
      size = Size(w * 0.6f, h * 0.22f)
    )
    // Gold Star
    drawCircle(Color(0xFFFFD54F), radius = 4.dp.toPx(), center = Offset(w * 0.5f, h * 0.56f))
  }
}

@Composable
private fun AttaVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Flour bag
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFFFFE082), Color(0xFFFFCA28), Color(0xFFFFB300))),
      topLeft = Offset(w * 0.2f, h * 0.25f),
      size = Size(w * 0.6f, h * 0.65f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(10.dp.toPx())
    )
    // Green circle brand
    drawCircle(Color(0xFF2E7D32), radius = w * 0.15f, center = Offset(w * 0.5f, h * 0.55f))
    drawCircle(Color.White, radius = w * 0.08f, center = Offset(w * 0.5f, h * 0.55f))
  }
}

@Composable
private fun DaalVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Transparent packet style
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFFFFF9C4), Color(0xFFFFF59D))),
      topLeft = Offset(w * 0.2f, h * 0.25f),
      size = Size(w * 0.6f, h * 0.65f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
    )
    // Grains inside
    for (i in 0..12) {
      val gx = w * (0.28f + (i % 4) * 0.14f)
      val gy = h * (0.35f + (i / 4) * 0.14f)
      drawCircle(Color(0xFFF57F17), radius = 2.5.dp.toPx(), center = Offset(gx, gy))
    }
  }
}

@Composable
private fun OilVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Bottle body
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFFFFF176), Color(0xFFFFEE58), Color(0xFFFBC02D))),
      topLeft = Offset(w * 0.28f, h * 0.35f),
      size = Size(w * 0.44f, h * 0.55f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
    )
    // Neck & Cap
    drawRect(Color(0xFF00796B), topLeft = Offset(w * 0.42f, h * 0.18f), size = Size(w * 0.16f, h * 0.17f))
    drawCircle(Color(0xFF004D40), radius = w * 0.09f, center = Offset(w * 0.5f, h * 0.18f))
  }
}

@Composable
private fun GheeVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Dalda Green Tin
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFF43A047), Color(0xFF2E7D32), Color(0xFF1B5E20))),
      topLeft = Offset(w * 0.22f, h * 0.3f),
      size = Size(w * 0.56f, h * 0.6f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(10.dp.toPx())
    )
    // Palm tree icon highlight
    drawCircle(Color(0xFFFFD54F), radius = w * 0.12f, center = Offset(w * 0.5f, h * 0.58f))
  }
}

@Composable
private fun BiryaniSpiceVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Shan Spice Box (Red & Orange)
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFFE53935), Color(0xFFC62828), Color(0xFFB71C1C))),
      topLeft = Offset(w * 0.22f, h * 0.22f),
      size = Size(w * 0.56f, h * 0.68f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
    )
    // Yellow header stripe
    drawRect(
      color = Color(0xFFFFCA28),
      topLeft = Offset(w * 0.22f, h * 0.32f),
      size = Size(w * 0.56f, h * 0.16f)
    )
    // White text representation
    drawCircle(Color.White, radius = 3.dp.toPx(), center = Offset(w * 0.5f, h * 0.4f))
  }
}

@Composable
private fun TapalTeaVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Tapal Red Pouch
    val pouchPath = Path().apply {
      moveTo(w * 0.3f, h * 0.22f)
      lineTo(w * 0.7f, h * 0.22f)
      lineTo(w * 0.78f, h * 0.88f)
      lineTo(w * 0.22f, h * 0.88f)
      close()
    }
    drawPath(
      path = pouchPath,
      brush = Brush.verticalGradient(listOf(Color(0xFFD32F2F), Color(0xFFB71C1C)))
    )
    // Gold emblem
    drawCircle(Color(0xFFFFD54F), radius = w * 0.14f, center = Offset(w * 0.5f, h * 0.55f))
    drawCircle(Color(0xFFB71C1C), radius = w * 0.08f, center = Offset(w * 0.5f, h * 0.55f))
  }
}

@Composable
private fun RoohAfzaVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Bottle glass
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFFE91E63), Color(0xFFC2185B), Color(0xFF880E4F))),
      topLeft = Offset(w * 0.3f, h * 0.3f),
      size = Size(w * 0.4f, h * 0.6f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(10.dp.toPx())
    )
    // Green Cap & Neck
    drawRect(Color(0xFF2E7D32), topLeft = Offset(w * 0.42f, h * 0.16f), size = Size(w * 0.16f, h * 0.14f))
    // Floral yellow badge
    drawCircle(Color(0xFFFFEB3B), radius = w * 0.1f, center = Offset(w * 0.5f, h * 0.58f))
  }
}

@Composable
private fun BiscuitVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Biscuit body
    drawRoundRect(
      brush = Brush.radialGradient(
        colors = listOf(Color(0xFFFFE082), Color(0xFFFFB74D), Color(0xFFFFA726)),
        center = Offset(w * 0.5f, h * 0.5f),
        radius = w * 0.4f
      ),
      topLeft = Offset(w * 0.18f, h * 0.28f),
      size = Size(w * 0.64f, h * 0.48f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
    )
    // Biscuit holes
    for (ix in listOf(0.32f, 0.5f, 0.68f)) {
      for (iy in listOf(0.42f, 0.62f)) {
        drawCircle(Color(0xFFBF360C), radius = 2.dp.toPx(), center = Offset(w * ix, h * iy))
      }
    }
  }
}

@Composable
private fun JamVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFFFFB300), Color(0xFFFB8C00))),
      topLeft = Offset(w * 0.25f, h * 0.35f),
      size = Size(w * 0.5f, h * 0.55f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
    )
    // Red checkered lid
    drawRoundRect(
      color = Color(0xFFD32F2F),
      topLeft = Offset(w * 0.22f, h * 0.22f),
      size = Size(w * 0.56f, h * 0.14f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx())
    )
  }
}

@Composable
private fun PickleVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFFC0CA33), Color(0xFFAFB42B), Color(0xFF827717))),
      topLeft = Offset(w * 0.25f, h * 0.35f),
      size = Size(w * 0.5f, h * 0.55f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
    )
    drawRoundRect(
      color = Color(0xFFF57F17),
      topLeft = Offset(w * 0.22f, h * 0.22f),
      size = Size(w * 0.56f, h * 0.14f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx())
    )
  }
}

@Composable
private fun ButterVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Blue & Gold Butter Pack
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFF1976D2), Color(0xFF0D47A1))),
      topLeft = Offset(w * 0.18f, h * 0.32f),
      size = Size(w * 0.64f, h * 0.44f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
    )
    drawRect(
      color = Color(0xFFFFD54F),
      topLeft = Offset(w * 0.18f, h * 0.48f),
      size = Size(w * 0.64f, h * 0.12f)
    )
  }
}

@Composable
private fun DetergentVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Surf Excel pouch (Blue with color burst)
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFF0288D1), Color(0xFF01579B))),
      topLeft = Offset(w * 0.22f, h * 0.22f),
      size = Size(w * 0.56f, h * 0.68f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
    )
    drawCircle(Color(0xFFFF5722), radius = w * 0.15f, center = Offset(w * 0.5f, h * 0.55f))
    drawCircle(Color(0xFFFFEB3B), radius = w * 0.08f, center = Offset(w * 0.5f, h * 0.55f))
  }
}

@Composable
private fun HandwashVisual(size: Dp) {
  Canvas(modifier = Modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Bottle
    drawRoundRect(
      brush = Brush.verticalGradient(listOf(Color(0xFF43A047), Color(0xFF2E7D32))),
      topLeft = Offset(w * 0.28f, h * 0.35f),
      size = Size(w * 0.44f, h * 0.55f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(10.dp.toPx())
    )
    // Pump head
    drawRect(Color.White, topLeft = Offset(w * 0.44f, h * 0.22f), size = Size(w * 0.12f, h * 0.13f))
    drawRect(Color.White, topLeft = Offset(w * 0.32f, h * 0.16f), size = Size(w * 0.36f, h * 0.07f))
    // Sword/Plus cross emblem
    drawCircle(Color.White, radius = w * 0.1f, center = Offset(w * 0.5f, h * 0.62f))
    drawRect(Color(0xFF2E7D32), topLeft = Offset(w * 0.47f, h * 0.55f), size = Size(w * 0.06f, h * 0.14f))
    drawRect(Color(0xFF2E7D32), topLeft = Offset(w * 0.43f, h * 0.59f), size = Size(w * 0.14f, h * 0.06f))
  }
}
