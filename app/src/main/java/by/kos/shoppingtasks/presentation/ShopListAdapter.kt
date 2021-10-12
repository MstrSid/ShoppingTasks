package by.kos.shoppingtasks.presentation

import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.kos.shoppingtasks.R
import by.kos.shoppingtasks.databinding.ItemShopDisabledBinding
import by.kos.shoppingtasks.databinding.ItemShopEnabledBinding
import by.kos.shoppingtasks.domain.ShopItem

class ShopListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ShopItemViewHolderEnabled(val binding: ItemShopEnabledBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ShopItemViewHolderDisabled(val binding: ItemShopDisabledBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ENABLED_VIEW_TYPE -> {
                ShopItemViewHolderEnabled(
                    ItemShopEnabledBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            DISABLED_VIEW_TYPE -> {
                ShopItemViewHolderDisabled(
                    ItemShopDisabledBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
            else -> throw RuntimeException("Unknown view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].enabled) {
            ENABLED_VIEW_TYPE
        } else DISABLED_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val shopItem = shopList[position]
        when (holder) {
            is ShopItemViewHolderEnabled -> {
                holder.binding.tvTitle.text = "${shopItem.name} enabled"
                holder.binding.tvCount.text = shopItem.count.toString()
                holder.binding.tvMeasure.text = shopItem.measure
            }
            is ShopItemViewHolderDisabled -> {
                holder.binding.tvTitle.text = "${shopItem.name} disabled"
                holder.binding.tvCount.text = shopItem.count.toString()
                holder.binding.tvMeasure.text = shopItem.measure
            }
        }
        holder.itemView.setOnLongClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            true
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    companion object {
        const val ENABLED_VIEW_TYPE = 1
        const val DISABLED_VIEW_TYPE = 0
        const val POOL_RV_SIZE_MAX = 15
    }
}