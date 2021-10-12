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
    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.shopList = it
        }

    }

    private fun setupRecyclerView() {
        adapter = ShopListAdapter()
        binding.rvShopList.adapter = adapter
    }

}