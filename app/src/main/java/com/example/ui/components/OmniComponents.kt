package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppNavTab
import com.example.model.GroceryCategory
import com.example.model.Product
import com.example.ui.theme.AmberGold
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.MintBackground
import com.example.ui.theme.MintLight
import com.example.ui.theme.PrimaryForest
import com.example.ui.theme.PrimaryGreen
import com.example.ui.theme.PrimaryGreenDark
import com.example.ui.theme.SecondaryMint
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun OmniTopHeader(
  currentLocation: String = "Phase 5 DHA, Lahore",
  deliveryTime: String = "15-20 mins",
  onLocationClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  Surface(
    color = MaterialTheme.colorScheme.surface,
    modifier = modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .weight(1f)
          .clickable(onClick = onLocationClick)
      ) {
        Box(
          modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(MintLight),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location",
            tint = PrimaryGreenDark,
            modifier = Modifier.size(24.dp)
          )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "Deliver to",
              style = MaterialTheme.typography.labelMedium,
              color = TextMuted
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
              imageVector = Icons.Default.KeyboardArrowDown,
              contentDescription = "Select City/Address",
              tint = PrimaryGreen,
              modifier = Modifier.size(16.dp)
            )
          }
          Text(
            text = currentLocation,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }
      }

      // Express ETA Badge
      Surface(
        shape = RoundedCornerShape(20.dp),
        color = MintBackground,
        border = androidx.compose.foundation.BorderStroke(1.dp, MintLight)
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Bolt,
            contentDescription = "Express",
            tint = PrimaryGreen,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(2.dp))
          Text(
            text = deliveryTime,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = PrimaryForest
          )
        }
      }
    }
  }
}

@Composable
fun OmniSearchBar(
  query: String,
  onQueryChange: (String) -> Unit,
  onClear: () -> Unit,
  modifier: Modifier = Modifier
) {
  OutlinedTextField(
    value = query,
    onValueChange = onQueryChange,
    placeholder = {
      Text(
        text = "Search fresh fruits, sabzi, atta, milk, chai...",
        style = MaterialTheme.typography.bodyMedium,
        color = TextMuted,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    },
    leadingIcon = {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search",
        tint = PrimaryGreen
      )
    },
    trailingIcon = {
      if (query.isNotEmpty()) {
        IconButton(
          onClick = onClear,
          modifier = Modifier.testTag("clear_search_button")
        ) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Clear search",
            tint = TextMuted
          )
        }
      }
    },
    singleLine = true,
    shape = RoundedCornerShape(16.dp),
    colors = OutlinedTextFieldDefaults.colors(
      focusedContainerColor = MaterialTheme.colorScheme.surface,
      unfocusedContainerColor = MaterialTheme.colorScheme.surface,
      focusedBorderColor = PrimaryGreen,
      unfocusedBorderColor = BorderSubtle,
      cursorColor = PrimaryGreen
    ),
    modifier = modifier
      .fillMaxWidth()
      .testTag("search_input_field")
  )
}

@Composable
fun OmniProductCard(
  product: Product,
  cartQuantity: Int,
  isFavorite: Boolean,
  onCardClick: () -> Unit,
  onAddToCart: () -> Unit,
  onIncrement: () -> Unit,
  onDecrement: () -> Unit,
  onToggleFavorite: () -> Unit,
  modifier: Modifier = Modifier
) {
  val interactionSource = remember { MutableInteractionSource() }
  val isPressed by interactionSource.collectIsPressedAsState()
  val scale by animateFloatAsState(
    targetValue = if (isPressed) 0.96f else 1f,
    animationSpec = spring(dampingRatio = 0.7f),
    label = "cardScale"
  )

  Card(
    modifier = modifier
      .scale(scale)
      .shadow(elevation = 3.dp, shape = RoundedCornerShape(20.dp), spotColor = Color(0x1A000000))
      .clip(RoundedCornerShape(20.dp))
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onCardClick
      )
      .testTag("product_card_${product.id}"),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    shape = RoundedCornerShape(20.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
    ) {
      // Visual with badges
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(130.dp)
          .clip(RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
      ) {
        GroceryProductVisual(
          type = product.visualType,
          modifier = Modifier.fillMaxWidth(),
          size = 130.dp
        )

        // Discount or Deal Tag
        if (product.discountPercent != null && product.discountPercent > 0) {
          Surface(
            modifier = Modifier
              .align(Alignment.TopStart)
              .padding(6.dp),
            shape = RoundedCornerShape(8.dp),
            color = PrimaryGreen
          ) {
            Text(
              text = "${product.discountPercent}% OFF",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
              color = Color.White,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
            )
          }
        }

        // Favorite heart icon
        Box(
          modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(4.dp)
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.85f))
            .clickable(onClick = onToggleFavorite),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) Color(0xFFEF4444) else TextMuted,
            modifier = Modifier.size(18.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Unit tag
      Text(
        text = product.unit,
        style = MaterialTheme.typography.labelSmall,
        color = TextMuted
      )

      // Product Name
      Text(
        text = product.name,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = TextPrimary,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.height(38.dp)
      )

      // Urdu Name subtitle
      Text(
        text = product.urduName,
        style = MaterialTheme.typography.bodySmall,
        color = TextSecondary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Price and Add/Quantity control row
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Rs. ${product.price}",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = PrimaryGreenDark
          )
          if (product.originalPrice != null && product.originalPrice > product.price) {
            Text(
              text = "Rs. ${product.originalPrice}",
              style = MaterialTheme.typography.labelSmall.copy(textDecoration = TextDecoration.LineThrough),
              color = TextMuted
            )
          }
        }

        // Cart Stepper or Add Button
        if (cartQuantity == 0) {
          Button(
            onClick = onAddToCart,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = MintBackground,
              contentColor = PrimaryGreenDark
            ),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, MintLight),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
            modifier = Modifier
              .height(36.dp)
              .testTag("add_to_cart_btn_${product.id}")
          ) {
            Icon(
              imageVector = Icons.Default.Add,
              contentDescription = "Add",
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "ADD",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
          }
        } else {
          // Quantity Stepper with Green Background
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = PrimaryGreen,
            modifier = Modifier.height(36.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(horizontal = 4.dp)
            ) {
              IconButton(
                onClick = onDecrement,
                modifier = Modifier
                  .size(28.dp)
                  .testTag("dec_btn_${product.id}")
              ) {
                Icon(
                  imageVector = Icons.Default.Remove,
                  contentDescription = "Decrease",
                  tint = Color.White,
                  modifier = Modifier.size(16.dp)
                )
              }

              Text(
                text = "$cartQuantity",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 6.dp)
              )

              IconButton(
                onClick = onIncrement,
                modifier = Modifier
                  .size(28.dp)
                  .testTag("inc_btn_${product.id}")
              ) {
                Icon(
                  imageVector = Icons.Default.Add,
                  contentDescription = "Increase",
                  tint = Color.White,
                  modifier = Modifier.size(16.dp)
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun OmniCategoryPill(
  category: GroceryCategory,
  isSelected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val backgroundColor by animateColorAsState(
    targetValue = if (isSelected) PrimaryGreen else MaterialTheme.colorScheme.surface,
    label = "catBg"
  )
  val textColor by animateColorAsState(
    targetValue = if (isSelected) Color.White else TextPrimary,
    label = "catText"
  )

  Surface(
    shape = RoundedCornerShape(16.dp),
    color = backgroundColor,
    border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
    shadowElevation = if (isSelected) 2.dp else 0.dp,
    modifier = modifier
      .clip(RoundedCornerShape(16.dp))
      .clickable(onClick = onClick)
      .testTag("category_pill_${category.id}")
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(28.dp)
          .clip(CircleShape)
          .background(
            if (isSelected) Color.White.copy(alpha = 0.2f)
            else Color(category.colorHex).copy(alpha = 0.15f)
          ),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.ShoppingBag,
          contentDescription = null,
          tint = if (isSelected) Color.White else Color(category.colorHex),
          modifier = Modifier.size(16.dp)
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      Column {
        Text(
          text = category.name,
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
          color = textColor
        )
        Text(
          text = "${category.itemCount} items",
          style = MaterialTheme.typography.labelSmall,
          color = if (isSelected) Color.White.copy(alpha = 0.8f) else TextMuted
        )
      }
    }
  }
}

@Composable
fun OmniBottomNavigationBar(
  currentTab: AppNavTab,
  cartCount: Int,
  onTabSelected: (AppNavTab) -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "badgePulse")
  val badgeScale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = if (cartCount > 0) 1.15f else 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(600, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "badgeScale"
  )

  NavigationBar(
    containerColor = MaterialTheme.colorScheme.surface,
    tonalElevation = 8.dp,
    modifier = modifier
      .navigationBarsPadding()
      .shadow(elevation = 12.dp)
  ) {
    val items = listOf(
      Triple(AppNavTab.HOME, Icons.Default.Home, Icons.Outlined.Home),
      Triple(AppNavTab.CATEGORIES, Icons.Default.Category, Icons.Outlined.Category),
      Triple(AppNavTab.CART, Icons.Default.ShoppingCart, Icons.Outlined.ShoppingCart),
      Triple(AppNavTab.ORDERS, Icons.Default.ReceiptLong, Icons.Outlined.ReceiptLong),
      Triple(AppNavTab.PROFILE, Icons.Default.Person, Icons.Outlined.Person)
    )

    items.forEach { (tab, filledIcon, outlinedIcon) ->
      val isSelected = currentTab == tab

      NavigationBarItem(
        selected = isSelected,
        onClick = { onTabSelected(tab) },
        icon = {
          if (tab == AppNavTab.CART) {
            BadgedBox(
              badge = {
                if (cartCount > 0) {
                  Badge(
                    containerColor = PrimaryGreen,
                    contentColor = Color.White,
                    modifier = Modifier
                      .scale(badgeScale)
                      .testTag("cart_badge_counter")
                  ) {
                    Text(
                      text = "$cartCount",
                      style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                  }
                }
              }
            ) {
              Icon(
                imageVector = if (isSelected) filledIcon else outlinedIcon,
                contentDescription = tab.title,
                modifier = Modifier.size(24.dp)
              )
            }
          } else {
            Icon(
              imageVector = if (isSelected) filledIcon else outlinedIcon,
              contentDescription = tab.title,
              modifier = Modifier.size(24.dp)
            )
          }
        },
        label = {
          Text(
            text = tab.title,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = PrimaryForest,
          selectedTextColor = PrimaryForest,
          indicatorColor = MintLight,
          unselectedIconColor = TextSecondary,
          unselectedTextColor = TextSecondary
        ),
        modifier = Modifier.testTag(tab.testTag)
      )
    }
  }
}

@Composable
fun ShimmerProductSkeleton(modifier: Modifier = Modifier) {
  val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
  val alpha by infiniteTransition.animateFloat(
    initialValue = 0.3f,
    targetValue = 0.8f,
    animationSpec = infiniteRepeatable(
      animation = tween(700, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "shimmerAlpha"
  )

  Card(
    modifier = modifier
      .fillMaxWidth()
      .height(230.dp),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(110.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(Color.LightGray.copy(alpha = alpha))
      )
      Spacer(modifier = Modifier.height(10.dp))
      Box(
        modifier = Modifier
          .width(80.dp)
          .height(14.dp)
          .clip(RoundedCornerShape(4.dp))
          .background(Color.LightGray.copy(alpha = alpha))
      )
      Spacer(modifier = Modifier.height(6.dp))
      Box(
        modifier = Modifier
          .fillMaxWidth(0.9f)
          .height(18.dp)
          .clip(RoundedCornerShape(4.dp))
          .background(Color.LightGray.copy(alpha = alpha))
      )
      Spacer(modifier = Modifier.height(12.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .width(60.dp)
            .height(20.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.LightGray.copy(alpha = alpha))
        )
        Box(
          modifier = Modifier
            .size(width = 65.dp, height = 32.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray.copy(alpha = alpha))
        )
      }
    }
  }
}
