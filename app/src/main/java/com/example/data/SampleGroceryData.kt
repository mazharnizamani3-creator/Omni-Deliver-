package com.example.data

import com.example.model.CartItem
import com.example.model.DeliverySlot
import com.example.model.GroceryCategory
import com.example.model.Order
import com.example.model.OrderStatus
import com.example.model.Product
import com.example.model.ProductVisualType

object SampleGroceryData {

  val categories = listOf(
    GroceryCategory(
      id = "cat_veg",
      name = "Fresh Vegetables",
      urduName = "تازہ سبزیاں",
      iconKey = "veg",
      colorHex = 0xFF10B981,
      itemCount = 14,
      description = "Farm-fresh handpicked daily sabzi"
    ),
    GroceryCategory(
      id = "cat_fruits",
      name = "Fresh Fruits",
      urduName = "تازہ پھل",
      iconKey = "fruit",
      colorHex = 0xFFF59E0B,
      itemCount = 10,
      description = "Sweet Sindhri mangoes, apples & citrus"
    ),
    GroceryCategory(
      id = "cat_dairy",
      name = "Dairy & Eggs",
      urduName = "دودھ اور انڈے",
      iconKey = "dairy",
      colorHex = 0xFF3B82F6,
      itemCount = 12,
      description = "Pure milk, butter, desi eggs & yogurt"
    ),
    GroceryCategory(
      id = "cat_staples",
      name = "Atta, Rice & Daal",
      urduName = "آٹا، چاول اور دالیں",
      iconKey = "grains",
      colorHex = 0xFFD97706,
      itemCount = 16,
      description = "Chakki atta, Super Basmati & pulses"
    ),
    GroceryCategory(
      id = "cat_oil",
      name = "Cooking Oil & Ghee",
      urduName = "کوکنگ آئل اور گھی",
      iconKey = "oil",
      colorHex = 0xFFEAB308,
      itemCount = 8,
      description = "Dalda, Habib, Sufi & Banaspati"
    ),
    GroceryCategory(
      id = "cat_spices",
      name = "Masalay & Spices",
      urduName = "مصالحہ جات",
      iconKey = "spice",
      colorHex = 0xFFEF4444,
      itemCount = 15,
      description = "Shan biryani, National spices & herbs"
    ),
    GroceryCategory(
      id = "cat_tea",
      name = "Chai & Beverages",
      urduName = "چائے اور مشروبات",
      iconKey = "tea",
      colorHex = 0xFF8B5CF6,
      itemCount = 11,
      description = "Tapal Danedar, Rooh Afza & Juices"
    ),
    GroceryCategory(
      id = "cat_snacks",
      name = "Snacks & Biscuits",
      urduName = "بسکٹ اور سنیکس",
      iconKey = "snack",
      colorHex = 0xFFEC4899,
      itemCount = 18,
      description = "Sooper, Rio, Nimco & breakfast jams"
    ),
    GroceryCategory(
      id = "cat_cleaning",
      name = "Cleaning & Household",
      urduName = "صفائی اور گھریلو اشیاء",
      iconKey = "clean",
      colorHex = 0xFF06B6D4,
      itemCount = 9,
      description = "Surf Excel, Dettol & home hygiene"
    )
  )

  val sampleProducts = listOf(
    Product(
      id = "prod_tomato",
      name = "Farm Fresh Red Tomatoes",
      urduName = "تازہ لال ٹماٹر",
      categoryId = "cat_veg",
      price = 140,
      originalPrice = 175,
      unit = "1 kg",
      rating = 4.9f,
      reviewsCount = 340,
      description = "Crisp, plump, pesticide-checked farm tomatoes freshly plucked this morning. Perfect for rich gravies, handi, and salads.",
      discountPercent = 20,
      isPopular = true,
      isDealOfTheDay = true,
      origin = "Pattoki Green Farms",
      nutritionalHighlight = "Rich in Vitamin C & Lycopene",
      visualType = ProductVisualType.TOMATO
    ),
    Product(
      id = "prod_onion",
      name = "Golden Onions (Pyaz)",
      urduName = "پیاز اول",
      categoryId = "cat_veg",
      price = 180,
      originalPrice = 210,
      unit = "1 kg",
      rating = 4.8f,
      reviewsCount = 290,
      description = "Premium dried golden onions with thin skins, ideal for crispy tarka, korma gravy, and daily cooking.",
      discountPercent = 14,
      isPopular = true,
      origin = "Swat Valley",
      nutritionalHighlight = "Natural Prebiotics",
      visualType = ProductVisualType.ONION
    ),
    Product(
      id = "prod_potato",
      name = "White Potatoes (Aloo)",
      urduName = "آلو تازہ",
      categoryId = "cat_veg",
      price = 90,
      originalPrice = 110,
      unit = "1 kg",
      rating = 4.7f,
      reviewsCount = 210,
      description = "Clean, firm fresh crop potatoes. Ideal for crispy samosas, aloo bhujia, french fries, and curries.",
      discountPercent = 18,
      isPopular = true,
      origin = "Okara Farms",
      visualType = ProductVisualType.POTATO
    ),
    Product(
      id = "prod_chilli",
      name = "Fresh Green Chillies (Hari Mirch)",
      urduName = "ہری مرچ تیز",
      categoryId = "cat_veg",
      price = 60,
      originalPrice = 80,
      unit = "250 g",
      rating = 4.9f,
      reviewsCount = 180,
      description = "Vibrant green chillies packed with aromatic heat and punch. Essential for fresh chutneys and daily handi.",
      discountPercent = 25,
      isPopular = false,
      origin = "Sindh Farms",
      visualType = ProductVisualType.CHILLI
    ),
    Product(
      id = "prod_mango",
      name = "Sindhri Mangoes (Premium Box)",
      urduName = "سندھڑی آم اسپیشل",
      categoryId = "cat_fruits",
      price = 650,
      originalPrice = 750,
      unit = "1 kg Box",
      rating = 5.0f,
      reviewsCount = 480,
      description = "World renowned Pakistani Sindhri mangoes. Incomparably sweet, juicy, aromatic, and naturally ripened with zero carbide.",
      discountPercent = 13,
      isPopular = true,
      isDealOfTheDay = true,
      origin = "Mirpur Khas, Sindh",
      nutritionalHighlight = "High Vitamin A & Antioxidants",
      visualType = ProductVisualType.MANGO
    ),
    Product(
      id = "prod_banana",
      name = "Fresh Farm Bananas (Kela)",
      urduName = "تازہ کیلے درجن",
      categoryId = "cat_fruits",
      price = 160,
      originalPrice = 190,
      unit = "1 Dozen",
      rating = 4.8f,
      reviewsCount = 155,
      description = "Sweet, creamy golden bananas packed with potassium and instant natural energy. Selected for optimal ripeness.",
      discountPercent = 15,
      isPopular = true,
      origin = "Sindh Orchards",
      visualType = ProductVisualType.BANANA
    ),
    Product(
      id = "prod_milk_olpers",
      name = "Olper's Full Cream Milk",
      urduName = "اولپرز فل کریم دودھ",
      categoryId = "cat_dairy",
      price = 290,
      originalPrice = 300,
      unit = "1 Litre UHT",
      rating = 4.9f,
      reviewsCount = 520,
      description = "100% pure, wholesome, rich dairy milk. Ideal for morning chai, custard, sheer khurma, and nourishing your family.",
      discountPercent = 3,
      isPopular = true,
      isDealOfTheDay = true,
      origin = "Engro Foods Pakistan",
      nutritionalHighlight = "Calcium & Vitamin D Fortified",
      visualType = ProductVisualType.MILK
    ),
    Product(
      id = "prod_desi_eggs",
      name = "Organic Farm Desi Eggs",
      urduName = "دیسی انڈے پیکٹ",
      categoryId = "cat_dairy",
      price = 380,
      originalPrice = 420,
      unit = "12 Pcs Tray",
      rating = 4.9f,
      reviewsCount = 310,
      description = "Free-range, grain-fed desi eggs with rich golden yolks. Carefully inspected and hygienically packed for maximum nutrition.",
      discountPercent = 10,
      isPopular = true,
      origin = "Chakwal Poultry",
      nutritionalHighlight = "High Protein & Omega-3",
      visualType = ProductVisualType.EGGS
    ),
    Product(
      id = "prod_nurpur_butter",
      name = "Nurpur Pure Butter",
      urduName = "نورپور خالص مکھن",
      categoryId = "cat_dairy",
      price = 540,
      originalPrice = 570,
      unit = "200 g",
      rating = 4.8f,
      reviewsCount = 145,
      description = "Creamy, naturally salted traditional butter crafted from fresh cream. Delicious on hot parathas and toasted bread.",
      discountPercent = 5,
      isPopular = false,
      origin = "Bhalwal Dairy",
      visualType = ProductVisualType.BUTTER
    ),
    Product(
      id = "prod_rice_guard",
      name = "Guard Supreme Super Basmati Rice",
      urduName = "گارڈ سپر باسمتی چاول",
      categoryId = "cat_staples",
      price = 850,
      originalPrice = 950,
      unit = "2 kg Bag",
      rating = 4.9f,
      reviewsCount = 260,
      description = "Aged extra long grain fragrant Super Basmati rice. Delivers separate, fluffy, aromatic grains for Biryani and Pulao.",
      discountPercent = 11,
      isPopular = true,
      origin = "Kallar Tract Punjab",
      visualType = ProductVisualType.RICE
    ),
    Product(
      id = "prod_atta_chakki",
      name = "Bake Parlor Chakki Whole Wheat Atta",
      urduName = "چکی کا خالص آٹا",
      categoryId = "cat_staples",
      price = 1450,
      originalPrice = 1600,
      unit = "10 kg Bag",
      rating = 4.8f,
      reviewsCount = 390,
      description = "100% whole grain stone-ground chakki atta. Makes soft, fluffy, fiber-rich rotis and parathas that stay soft for hours.",
      discountPercent = 9,
      isPopular = true,
      origin = "Punjab Wheat Mills",
      nutritionalHighlight = "100% Whole Wheat Fiber",
      visualType = ProductVisualType.ATTA
    ),
    Product(
      id = "prod_daal_chana",
      name = "Special Daal Chana (Gram Pulse)",
      urduName = "دال چنا اسپیشل",
      categoryId = "cat_staples",
      price = 290,
      originalPrice = 330,
      unit = "1 kg Pack",
      rating = 4.7f,
      reviewsCount = 180,
      description = "Triple machine-cleaned, polished yellow split chickpeas. Quick cooking with creamy rich texture and authentic aroma.",
      discountPercent = 12,
      isPopular = false,
      origin = "Thal Desert Harvest",
      visualType = ProductVisualType.DAAL
    ),
    Product(
      id = "prod_habib_oil",
      name = "Habib 100% Pure Cooking Oil",
      urduName = "حبیب کوکنگ آئل",
      categoryId = "cat_oil",
      price = 530,
      originalPrice = 560,
      unit = "1 Litre Pouch",
      rating = 4.9f,
      reviewsCount = 420,
      description = "Naturally refined, light cooking oil enriched with Vitamins A, D, and E. Low cholesterol for healthy everyday cooking.",
      discountPercent = 5,
      isPopular = true,
      origin = "Habib Oil Mills",
      visualType = ProductVisualType.OIL
    ),
    Product(
      id = "prod_dalda_ghee",
      name = "Dalda Banaspati Ghee with VTF",
      urduName = "ڈالڈا بناسپتی گھی",
      categoryId = "cat_oil",
      price = 540,
      originalPrice = 580,
      unit = "1 kg Pouch",
      rating = 4.8f,
      reviewsCount = 340,
      description = "Pakistan's heritage aroma and granular texture. Virtual Trans-Fat Free (VTF) for fragrant traditional curries and halwa.",
      discountPercent = 7,
      isPopular = false,
      origin = "Dalda Foods",
      visualType = ProductVisualType.GHEE
    ),
    Product(
      id = "prod_shan_biryani",
      name = "Shan Special Bombay Biryani Masala",
      urduName = "شان بمبئی بریانی مصالحہ",
      categoryId = "cat_spices",
      price = 130,
      originalPrice = 150,
      unit = "50 g Box",
      rating = 5.0f,
      reviewsCount = 890,
      description = "The authentic recipe of fragrant whole and ground spices with dried plums (aaloo bukhara) for legendary spicy biryani.",
      discountPercent = 13,
      isPopular = true,
      isDealOfTheDay = true,
      origin = "Shan Foods Karachi",
      visualType = ProductVisualType.SPICES_BIRYANI
    ),
    Product(
      id = "prod_tapal_tea",
      name = "Tapal Danedar Black Tea (Pouch)",
      urduName = "ٹپال دانے دار چائے",
      categoryId = "cat_tea",
      price = 680,
      originalPrice = 740,
      unit = "450 g Pouch",
      rating = 5.0f,
      reviewsCount = 940,
      description = "Pakistan's No.1 choice tea blend. Made from high-grown Kenyan tea leaves for brisk color, bold aroma, and robust karak taste.",
      discountPercent = 8,
      isPopular = true,
      isDealOfTheDay = true,
      origin = "Tapal Tea Ltd",
      visualType = ProductVisualType.TEA_TAPAL
    ),
    Product(
      id = "prod_rooh_afza",
      name = "Hamdard Rooh Afza Herbal Syrup",
      urduName = "ہمدرد روح افزا شربت",
      categoryId = "cat_tea",
      price = 390,
      originalPrice = 430,
      unit = "800 ml Bottle",
      rating = 4.9f,
      reviewsCount = 610,
      description = "The refreshing summer drink of the east! Made from rose petals, herbal extracts, and cooling natural distillate.",
      discountPercent = 9,
      isPopular = true,
      origin = "Hamdard Laboratories",
      visualType = ProductVisualType.ROOH_AFZA
    ),
    Product(
      id = "prod_sooper_biscuit",
      name = "Peek Freans Sooper Biscuits Family Pack",
      urduName = "سوپر بسکٹ فیملی پیک",
      categoryId = "cat_snacks",
      price = 150,
      originalPrice = 165,
      unit = "Family Pack",
      rating = 4.9f,
      reviewsCount = 470,
      description = "Egg & milk rich, sweet crumbly biscuit. The timeless Pakistani tea-time companion loved by millions.",
      discountPercent = 9,
      isPopular = true,
      origin = "English Biscuit Manufacturers",
      visualType = ProductVisualType.BISCUITS
    ),
    Product(
      id = "prod_mitchells_jam",
      name = "Mitchell's Golden Mango Jam",
      urduName = "مچلز مینگو جام",
      categoryId = "cat_snacks",
      price = 320,
      originalPrice = 360,
      unit = "450 g Jar",
      rating = 4.8f,
      reviewsCount = 130,
      description = "Luscious real fruit mango pulp preserve. Delicious on warm toast, rolls, and afternoon snacks.",
      discountPercent = 11,
      isPopular = false,
      origin = "Mitchell's Fruit Farms Renala Khurd",
      visualType = ProductVisualType.JAM
    ),
    Product(
      id = "prod_national_achar",
      name = "National Mixed Pickle (Achar) in Oil",
      urduName = "نیشنل مکس اچار",
      categoryId = "cat_snacks",
      price = 280,
      originalPrice = 310,
      unit = "350 g Jar",
      rating = 4.9f,
      reviewsCount = 240,
      description = "Traditional tangy, spicy blend of mangoes, carrots, lime, and chillies pickled in pure mustard oil.",
      discountPercent = 10,
      isPopular = false,
      origin = "National Foods Pakistan",
      visualType = ProductVisualType.PICKLE
    ),
    Product(
      id = "prod_surf_excel",
      name = "Surf Excel Quick Wash Detergent Powder",
      urduName = "سرف ایکسل واشنگ پاؤڈر",
      categoryId = "cat_cleaning",
      price = 620,
      originalPrice = 670,
      unit = "1 kg Pack",
      rating = 4.9f,
      reviewsCount = 380,
      description = "Removes tough stains in just 1 stroke. Superior lathering and fresh fragrance that leaves clothes spotless.",
      discountPercent = 7,
      isPopular = true,
      origin = "Unilever Pakistan",
      visualType = ProductVisualType.DETERGENT
    ),
    Product(
      id = "prod_dettol_soap",
      name = "Dettol Original Germ Protection Handwash",
      urduName = "ڈیٹول ہینڈ واش",
      categoryId = "cat_cleaning",
      price = 360,
      originalPrice = 399,
      unit = "250 ml Pump",
      rating = 4.9f,
      reviewsCount = 290,
      description = "Protects against 100 illness-causing germs with moisturizing pine formula. Tested and trusted hygienic defense.",
      discountPercent = 10,
      isPopular = false,
      origin = "Reckitt Benckiser",
      visualType = ProductVisualType.HANDWASH
    )
  )

  val deliverySlots = listOf(
    DeliverySlot(
      id = "slot_instant",
      title = "⚡ Express 15-25 Mins",
      subtitle = "Instant dispatch from nearest dark store",
      isExpress = true,
      fee = 99
    ),
    DeliverySlot(
      id = "slot_evening",
      title = "🌇 Today Evening (6 PM - 8 PM)",
      subtitle = "Scheduled doorstep delivery",
      isExpress = false,
      fee = 49
    ),
    DeliverySlot(
      id = "slot_tomorrow",
      title = "☀️ Tomorrow Morning (9 AM - 11 AM)",
      subtitle = "Fresh morning grocery drop",
      isExpress = false,
      fee = 0
    )
  )

  val samplePastOrders = listOf(
    Order(
      id = "ord_101",
      orderNumber = "OMNI-98234",
      placedTime = "Yesterday at 7:30 PM",
      items = listOf(
        CartItem(sampleProducts[0], 2), // Tomatoes
        CartItem(sampleProducts[6], 2), // Olper's Milk
        CartItem(sampleProducts[15], 1) // Tapal Tea
      ),
      subtotal = 1540,
      deliveryFee = 0,
      discount = 100,
      total = 1440,
      customerName = "Ali Raza",
      customerPhone = "0300-8472910",
      deliveryAddress = "House 42-B, Block C, Phase 5 DHA",
      city = "Lahore",
      paymentMethod = "Cash on Delivery (COD)",
      deliverySlot = "⚡ Express 15-25 Mins",
      status = OrderStatus.DELIVERED,
      estimatedArrival = "Delivered Yesterday"
    )
  )

  val pakistaniCities = listOf(
    "Lahore (DHA, Gulberg, Model Town, Johar Town, Bahria)",
    "Karachi (Clifton, DHA, Gulshan, PECHS, North Nazimabad)",
    "Islamabad (F-6, F-7, F-8, F-10, G-11, Bahria Town)",
    "Rawalpindi (Saddar, Westridge, Bahria Phase 1-8, Chaklala)",
    "Faisalabad (Kohinoor, D-Ground, Peoples Colony)",
    "Multan (Cantt, Gulgasht, Bosan Road)",
    "Peshawar (Hayatabad, University Town, Cantt)",
    "Hyderabad (Latifabad, Qasimabad, Auto Bhan)"
  )
}
