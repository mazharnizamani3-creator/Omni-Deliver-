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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SampleGroceryData
import com.example.model.CartItem
import com.example.model.GroceryCategory
import com.example.model.Product
import com.example.ui.components.OmniProductCard
import com.example.ui.components.OmniSearchBar
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
fun CategoriesScreen(
  activeCategoryId: String?,
  onCategorySelect: (String?) -> Unit,
  products: List<Product>,
  cartItems: Map<String, CartItem>,
  favoriteIds: Set<String>,
  onProductClick: (Product) -> Unit,
  onAddToCart: (Product) -> Unit,
  onIncrement: (String) -> Unit,
  onDecrement: (String) -> Unit,
  onToggleFavorite: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val selectedCategory = SampleGroceryData.categories.find { it.id == activeCategoryId }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("categories_screen_root"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Header
    item {
      Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
          ) {
            Column {
              Text(
                text = "Grocery Categories",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
              )
              Text(
                text = "Explore all aisles & fresh farm produce",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
              )
            }

            if (activeCategoryId != null) {
              Surface(
                shape = RoundedCornerShape(12.dp),
                color = MintBackground,
                border = androidx.compose.foundation.BorderStroke(1.dp, MintLight),
                modifier = Modifier.clickable { onCategorySelect(null) }
              ) {
                Text(
                  text = "Show All",
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = PrimaryGreenDark,
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
              }
            }
          }
        }
      }
    }

    // Horizontal category selection filter pills
    item {
      LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        item {
          FilterChip(
            selected = activeCategoryId == null,
            onClick = { onCategorySelect(null) },
            label = {
              Text(
                text = "All Items",
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = if (activeCategoryId == null) FontWeight.Bold else FontWeight.Normal
                )
              )
            },
            colors = FilterChipDefaults.filterChipColors(
              selectedContainerColor = PrimaryGreen,
              selectedLabelColor = Color.White
            )
          )
        }

        items(SampleGroceryData.categories) { cat ->
          val isSelected = cat.id == activeCategoryId
          FilterChip(
            selected = isSelected,
            onClick = {
              if (isSelected) onCategorySelect(null) else onCategorySelect(cat.id)
            },
            label = {
              Text(
                text = cat.name,
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
              )
            },
            colors = FilterChipDefaults.filterChipColors(
              selectedContainerColor = PrimaryGreen,
              selectedLabelColor = Color.White
            )
          )
        }
      }
    }

    // If no category is selected, show Category Overview Cards Grid
    if (activeCategoryId == null) {
      item {
        Text(
          text = "All Categories",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = TextPrimary,
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
      }

      val chunkedCategories = SampleGroceryData.categories.chunked(2)
      items(chunkedCategories) { pair ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          CategoryOverviewCard(
            category = pair[0],
            onClick = { onCategorySelect(pair[0].id) },
            modifier = Modifier.weight(1f)
          )
          if (pair.size > 1) {
            CategoryOverviewCard(
              category = pair[1],
              onClick = { onCategorySelect(pair[1].id) },
              modifier = Modifier.weight(1f)
            )
          } else {
            Spacer(modifier = Modifier.weight(1f))
          }
        }
      }
    } else {
      // Show Selected Category Banner
      item {
        selectedCategory?.let { cat ->
          Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
              containerColor = Color(cat.colorHex).copy(alpha = 0.12f)
            ),
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 8.dp)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Column {
                Text(
                  text = cat.name,
                  style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                  color = PrimaryForest
                )
                Text(
                  text = cat.urduName,
                  style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                  color = Color(cat.colorHex)
                )
                Text(
                  text = cat.description,
                  style = MaterialTheme.typography.bodySmall,
                  color = TextSecondary
                )
              }

              Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White
              ) {
                Text(
                  text = "${products.size} Products",
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = PrimaryForest,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
              }
            }
          }
        }
      }

      // Filtered Products
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
fun CategoryOverviewCard(
  category: GroceryCategory,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .shadow(elevation = 2.dp, shape = RoundedCornerShape(18.dp))
      .clip(RoundedCornerShape(18.dp))
      .clickable(onClick = onClick)
      .testTag("cat_card_${category.id}"),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    shape = RoundedCornerShape(18.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
      ) {
        Box(
          modifier = Modifier
            .size(46.dp)
            .clip(CircleShape)
            .background(Color(category.colorHex).copy(alpha = 0.15f)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.ShoppingBag,
            contentDescription = null,
            tint = Color(category.colorHex),
            modifier = Modifier.size(24.dp)
          )
        }

        Surface(
          shape = RoundedCornerShape(8.dp),
          color = MintBackground
        ) {
          Text(
            text = "${category.itemCount}+",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = PrimaryGreenDark,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = category.name,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = TextPrimary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

      Text(
        text = category.urduName,
        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
        color = Color(category.colorHex)
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = category.description,
        style = MaterialTheme.typography.bodySmall,
        color = TextSecondary,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}
