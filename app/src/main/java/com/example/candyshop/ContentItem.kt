package com.example.candyshop

data class ContentItem(
    val id: Int,
    val name: String,
    val image: Int,
    val price: Int,
    val description: String = "some placeholder text"
)
fun getCandyDetailsById(id: Int?): ContentItem? = content.firstOrNull { id == it.id }

val content = listOf(
    ContentItem(
        id = 1,
        name = "Candy Hearts",
        image = R.drawable.candy_hearts,
        price = 10
    ), ContentItem(
        id = 2,
        name = "Starburst",
        image = R.drawable.starburst,
        price = 15
    ), ContentItem(
        id = 3,
        name = "Chocolate Truffles",
        image = R.drawable.chocolate_truffles,
        price = 30
    ), ContentItem(
        id = 4,
        name = "Cupcakes",
        image = R.drawable.cupcakes,
        price = 30
    ), ContentItem(
        id = 5,
        name = "Fizzy Tablets",
        image = R.drawable.fizzy_tablets,
        price = 10
    ), ContentItem(
        id = 6,
        name = "Gumballs",
        image = R.drawable.gumballs,
        price = 15
    ), ContentItem(
        id = 7,
        name = "Jelly Beans",
        image = R.drawable.jelly_beans,
        price = 20
    ), ContentItem(
        id = 8,
        name = "Jelly Bears",
        image = R.drawable.jelly_bears,
        price = 15
    ), ContentItem(
        id = 9,
        name = "Jelly Raspberries",
        image = R.drawable.jelly_raspberries,
        price = 20
    ), ContentItem(
        id = 10,
        name = "Jelly Worms",
        image = R.drawable.jelly_worms,
        price = 25
    ), ContentItem(
        id = 11,
        name = "Lollipop",
        image = R.drawable.lollipop,
        price = 15
    ), ContentItem(
        id = 12,
        name = "Mackaroons",
        image = R.drawable.mackaroons,
        price = 40
    ), ContentItem(
        id = 13,
        name = "Marshmallow",
        image = R.drawable.marshmallow,
        price = 25
    ), ContentItem(
        id = 14,
        name = "Meringue",
        image = R.drawable.meringue,
        price = 20
    ), ContentItem(
        id = 15,
        name = "Sour Bears",
        image = R.drawable.sour_bears,
        price = 20
    ), ContentItem(
        id = 16,
        name = "Sprinkles",
        image = R.drawable.sprinkles,
        price = 25
    ), ContentItem(
        id = 17,
        name = "Swirly Lollipop",
        image = R.drawable.swirly_lollipop,
        price = 20
    )
)