package by.kos.shoppingtasks.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import by.kos.shoppingtasks.R
import by.kos.shoppingtasks.databinding.ActivityMainBinding
import by.kos.shoppingtasks.databinding.ItemShopDisabledBinding
import by.kos.shoppingtasks.databinding.ItemShopEnabledBinding
import by.kos.shoppingtasks.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>) {
        binding.loutShopList.removeAllViews()
        for(shopItem in list){
            val layoutId = if(shopItem.enabled){
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater.from(this).inflate(layoutId, binding.loutShopList, false)
            view.isHapticFeedbackEnabled = true
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            val tvCount = view.findViewById<TextView>(R.id.tvCount)
            val tvMeasure = view.findViewById<TextView>(R.id.tvMeasure)
            tvTitle.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            tvMeasure.text = shopItem.measure
            view.setOnLongClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                viewModel.changeEnabledState(shopItem)
                true
            }
            binding.loutShopList.addView(view)
        }
    }
}