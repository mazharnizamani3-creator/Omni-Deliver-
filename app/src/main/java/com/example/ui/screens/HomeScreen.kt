package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SampleGroceryData
import com.example.model.AppNavTab
import com.example.model.CartItem
import com.example.model.GroceryCategory
import com.example.model.Product
import com.example.ui.components.OmniCategoryPill
import com.example.ui.components.OmniProductCard
import com.example.ui.components.OmniSearchBar
import com.example.ui.components.OmniTopHeader
import com.example.ui.components.ShimmerProductSkeleton
import com.example.ui.theme.AmberGold
import com.example.ui.theme.AmberLight
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
fun HomeScreen(
  searchQuery: String,
  onSearchChange: (String) -> Unit,
  onClearSearch: () -> Unit,
  products: List<Product>,
  cartItems: Map<String, CartItem>,
  favoriteIds: Set<String>,
  isLoading: Boolean,
  onProductClick: (Product) -> Unit,
  onAddToCart: (Product) -> Unit,
  onIncrement: (String) -> Unit,
  onDecrement: (String) -> Unit,
  onToggleFavorite: (String) -> Unit,
  onCategorySelect: (String?) -> Unit,
  onViewAllCategories: () -> Unit,
  onApplyPromo: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val dealProducts = products.filter { it.isDealOfTheDay || (it.discountPercent ?: 0) >= 15 }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("home_screen_list"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Top Bar & Location
    item {
      OmniTopHeader(
        currentLocation = "Phase 5 DHA, Lahore",
        deliveryTime = "15-20 mins"
      )
    }

    // Search Bar
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surface)
          .padding(horizontal = 16.dp, vertical = 6.dp)
      ) {
        OmniSearchBar(
          query = searchQuery,
          onQueryChange = onSearchChange,
          onClear = onClearSearch
        )
      }
    }

    // Search Suggestion Quick Chips (when not searching)
    if (searchQuery.isEmpty()) {
      item {
        LazyRow(
          contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          val quickPills = listOf("Tomatoes", "Milk", "Sindhri Mangoes", "Basmati Rice", "Tapal Chai", "Biryani Masala", "Chakki Atta")
          items(quickPills) { pill ->
            Surface(
              shape = RoundedCornerShape(20.dp),
              color = MintBackground,
              border = androidx.compose.foundation.BorderStroke(1.dp, MintLight),
              modifier = Modifier.clickable { onSearchChange(pill) }
            ) {
              Text(
                text = pill,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                color = PrimaryGreenDark,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
              )
            }
          }
        }
      }

      // Hero Promo Banner
      item {
        HomeHeroBanner(
          onApplyPromo = onApplyPromo,
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
      }

      // Express Delivery Notice Ribbon
      item {
        HomeExpressRibbon(
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
      }

      // Categories Section Header
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Explore Categories",
              style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
            Text(
              text = "Freshly stocked Pakistani groceries",
              style = MaterialTheme.typography.bodySmall,
              color = TextSecondary
            )
          }

          Text(
            text = "View All →",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = PrimaryGreen,
            modifier = Modifier
              .clickable(onClick = onViewAllCategories)
              .padding(4.dp)
              .testTag("view_all_categories_btn")
          )
        }
      }

      // Horizontal Category Pills
      item {
        LazyRow(
          contentPadding = PaddingValues(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          items(SampleGroceryData.categories.take(6)) { cat ->
            OmniCategoryPill(
              category = cat,
              isSelected = false,
              onClick = { onCategorySelect(cat.id) }
            )
          }
        }
      }

      // Super Saver Deals Carousel
      if (dealProducts.isNotEmpty()) {
        item {
          Column(modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                  modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFEE2E2)),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Default.LocalOffer,
                    contentDescription = null,
                    tint = Color(0xFFEF4444),
                    modifier = Modifier.size(16.dp)
                  )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "Super Saver Deals",
                  style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                  color = TextPrimary
                )
              }

              Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFFEF3C7)
              ) {
                Text(
                  text = "Up to 25% Off",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = Color(0xFFB45309),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
              contentPadding = PaddingValues(horizontal = 16.dp),
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              items(dealProducts) { product ->
                val cartItem = cartItems[product.id]
                val qty = cartItem?.quantity ?: 0
                val isFav = favoriteIds.contains(product.id)

                OmniProductCard(
                  product = product,
                  cartQuantity = qty,
                  isFavorite = isFav,
                  onCardClick = { onProductClick(product) },
                  onAddToCart = { onAddToCart(product) },
                  onIncrement = { onIncrement(product.id) },
                  onDecrement = { onDecrement(product.id) },
                  onToggleFavorite = { onToggleFavorite(product.id) },
                  modifier = Modifier.width(170.dp)
                )
              }
            }
          }
        }
      }

      // Popular Essentials Header
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Daily Fresh Essentials",
              style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
            Text(
              text = "Handpicked farm sabzi, milk, atta & pantry",
              style = MaterialTheme.typography.bodySmall,
              color = TextSecondary
            )
          }
        }
      }
    } else {
      // Search Result Header
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Results for \"$searchQuery\" (${products.size})",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary
          )
          Text(
            text = "Clear",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = PrimaryGreen,
            modifier = Modifier.clickable(onClick = onClearSearch)
          )
        }
      }
    }

    // Main Product Grid / Rows
    if (isLoading) {
      item {
        Column(
          modifier = Modifier.padding(horizontal = 16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            ShimmerProductSkeleton(modifier = Modifier.weight(1f))
            ShimmerProductSkeleton(modifier = Modifier.weight(1f))
          }
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            ShimmerProductSkeleton(modifier = Modifier.weight(1f))
            ShimmerProductSkeleton(modifier = Modifier.weight(1f))
          }
        }
      }
    } else if (products.isEmpty()) {
      item {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp, horizontal = 24.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Icon(
            imageVector = Icons.Default.SearchOff,
            contentDescription = null,
            tint = TextMuted,
            modifier = Modifier.size(64.dp)
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "No items matched \"$searchQuery\"",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Try searching for tomatoes, milk, atta, rice or tea",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            textAlign = TextAlign.Center
          )
          Spacer(modifier = Modifier.height(16.dp))
          Button(
            onClick = onClearSearch,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
          ) {
            Text("Clear Search")
          }
        }
      }
    } else {
      // Chunk into pairs for 2-column grid in LazyColumn
      val chunkedProducts = products.chunked(2)
      items(chunkedProducts) { pair ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          val p1 = pair[0]
          val cart1 = cartItems[p1.id]?.quantity ?: 0
          val fav1 = favoriteIds.contains(p1.id)

          OmniProductCard(
            product = p1,
            cartQuantity = cart1,
            isFavorite = fav1,
            onCardClick = { onProductClick(p1) },
            onAddToCart = { onAddToCart(p1) },
            onIncrement = { onIncrement(p1.id) },
            onDecrement = { onDecrement(p1.id) },
            onToggleFavorite = { onToggleFavorite(p1.id) },
            modifier = Modifier.weight(1f)
          )

          if (pair.size > 1) {
            val p2 = pair[1]
            val cart2 = cartItems[p2.id]?.quantity ?: 0
            val fav2 = favoriteIds.contains(p2.id)

            OmniProductCard(
              product = p2,
              cartQuantity = cart2,
              isFavorite = fav2,
              onCardClick = { onProductClick(p2) },
              onAddToCart = { onAddToCart(p2) },
              onIncrement = { onIncrement(p2.id) },
              onDecrement = { onDecrement(p2.id) },
              onToggleFavorite = { onToggleFavorite(p2.id) },
              modifier = Modifier.weight(1f)
            )
          } else {
            Spacer(modifier = Modifier.weight(1f))
          }
        }
      }
    }
  }
}

@Composable
fun HomeHeroBanner(
  onApplyPromo: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .shadow(elevation = 6.dp, shape = RoundedCornerShape(22.dp), spotColor = Color(0x33000000)),
    shape = RoundedCornerShape(22.dp),
    colors = CardDefaults.cardColors(containerColor = PrimaryForest)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          Brush.horizontalGradient(
            colors = listOf(
              PrimaryForest,
              PrimaryGreenDark,
              PrimaryGreen
            )
          )
        )
        .padding(18.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = AmberGold
          ) {
            Text(
              text = "SUPER SAVER WEEK",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = 0.5.sp
              ),
              color = PrimaryForest,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "Flat Rs. 100 OFF\nYour Next Order",
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.ExtraBold,
              lineHeight = 26.sp
            ),
            color = Color.White
          )

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "Use voucher code: OMNI100",
            style = MaterialTheme.typography.bodySmall,
            color = MintLight
          )

          Spacer(modifier = Modifier.height(10.dp))

          Button(
            onClick = { onApplyPromo("OMNI100") },
            colors = ButtonDefaults.buttonColors(
              containerColor = Color.White,
              contentColor = PrimaryForest
            ),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
            modifier = Modifier.height(36.dp)
          ) {
            Text(
              text = "Apply OMNI100",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
          }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Banner Decorative Illustration
        Box(
          modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.12f)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.ShoppingBasket,
            contentDescription = null,
            tint = AmberGold,
            modifier = Modifier.size(52.dp)
          )
        }
      }
    }
  }
}

@Composable
fun HomeExpressRibbon(modifier: Modifier = Modifier) {
  Surface(
    shape = RoundedCornerShape(16.dp),
    color = MintBackground,
    border = androidx.compose.foundation.BorderStroke(1.dp, MintLight),
    modifier = modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(34.dp)
          .clip(CircleShape)
          .background(PrimaryGreen),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.Bolt,
          contentDescription = null,
          tint = Color.White,
          modifier = Modifier.size(20.dp)
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = "15-25 Mins Express Delivery Active",
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = PrimaryForest
        )
        Text(
          text = "FREE delivery on orders over Rs. 1,499",
          style = MaterialTheme.typography.bodySmall,
          color = PrimaryGreenDark
        )
      }
    }
  }
}
