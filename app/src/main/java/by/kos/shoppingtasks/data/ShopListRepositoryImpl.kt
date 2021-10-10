package by.kos.shoppingtasks.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.kos.shoppingtasks.domain.ShopItem
import by.kos.shoppingtasks.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0
    private val shopListLD = MutableLiveData<List<ShopItem>>()

    init{
        for(i in 0..10) {
            val item = ShopItem("Item #$i", i.toDouble(), "kg", true, i)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateLiveDataList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateLiveDataList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find {
            it.id == id
        } ?: throw RuntimeException("Element with id $id not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateLiveDataList(){
        shopListLD.value = shopList.toList()
    }
}