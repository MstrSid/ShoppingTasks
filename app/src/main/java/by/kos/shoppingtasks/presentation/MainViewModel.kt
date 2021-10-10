package by.kos.shoppingtasks.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.kos.shoppingtasks.data.ShopListRepositoryImpl
import by.kos.shoppingtasks.domain.*

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopListUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun changeEnabledState(shopItem: ShopItem){
        val item = shopItem.copy(enabled = !shopItem.enabled)
        editShopListUseCase.editShopItem(item)
        getShopList()
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

}