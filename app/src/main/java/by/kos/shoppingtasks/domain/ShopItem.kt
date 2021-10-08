package by.kos.shoppingtasks.domain

data class ShopItem(
    val id: Int,
    val name: String,
    val count: Double,
    val measure: String,
    val enabled: Boolean
)
