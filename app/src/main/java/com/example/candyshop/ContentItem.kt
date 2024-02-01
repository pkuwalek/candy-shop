package com.example.candyshop

data class ContentItem(
    val name: String,
   val image: Int,
   val price: String,
   val description: String
)

val content = arrayOf(
    ContentItem(
        name = "Candy Hearts",
        image = R.drawable.candy_hearts,
        price = "10",
        description = "text text text"
    ), ContentItem(name = "Starburst",
        image = R.drawable.starburst,
        price = "15",
        description = "text text text"
    ), ContentItem(name = "Chocolate Truffles",
        image = R.drawable.chocolate_truffles,
        price = "30",
        description = "text text text"
    ), ContentItem(name = "Cupcakes",
        image = R.drawable.cupcakes,
        price = "30",
        description = "text text text"
    ), ContentItem(name = "Fizzy Tablets",
        image = R.drawable.fizzy_tablets,
        price = "10",
        description = "text text text"
    ), ContentItem(name = "Gumballs",
        image = R.drawable.gumballs,
        price = "15",
        description = "text text text"
    ), ContentItem(name = "Jelly Beans",
        image = R.drawable.jelly_beans,
        price = "20",
        description = "text text text"
    ), ContentItem(name = "Jelly Bears",
        image = R.drawable.jelly_bears,
        price = "15",
        description = "text text text"
    ), ContentItem(name = "Jelly Raspberries",
        image = R.drawable.jelly_raspberries,
        price = "20",
        description = "text text text"
    ), ContentItem(name = "Jelly Worms",
        image = R.drawable.jelly_worms,
        price = "25",
        description = "text text text"
    ), ContentItem(name = "Lollipop",
        image = R.drawable.lollipop,
        price = "15",
        description = "text text text"
    ), ContentItem(name = "Mackaroons",
        image = R.drawable.mackaroons,
        price = "40",
        description = "text text text"
    ), ContentItem(name = "Marshmallow",
        image = R.drawable.marshmallow,
        price = "25",
        description = "text text text"
    ), ContentItem(name = "Meringue",
        image = R.drawable.meringue,
        price = "20",
        description = "text text text"
    ), ContentItem(name = "Sour Bears",
        image = R.drawable.sour_bears,
        price = "20",
        description = "text text text"
    ), ContentItem(name = "Sprinkles",
        image = R.drawable.sprinkles,
        price = "25",
        description = "text text text"
    ), ContentItem(name = "Swirly Lollipop",
        image = R.drawable.swirly_lollipop,
        price = "20",
        description = "text text text"
    )
)