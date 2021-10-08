package by.kos.shoppingtasks.domain

data class ShopItem(
    val name: String,
    val count: Double,
    val measure: String,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID
)
{
    companion object{
        const val UNDEFINED_ID = -1
    }
}
