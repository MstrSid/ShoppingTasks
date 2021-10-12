package by.kos.shoppingtasks.presentation

import android.content.Context
import android.graphics.Color
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.kos.shoppingtasks.R
import by.kos.shoppingtasks.domain.ShopItem

class ShopListAdapter() : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvCount: TextView = view.findViewById(R.id.tvCount)
        val tvMeasure: TextView = view.findViewById(R.id.tvMeasure)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layoutType =
            if (viewType == ENABLED_VIEW_TYPE) R.layout.item_shop_enabled else R.layout.item_shop_disabled
        val view = LayoutInflater.from(parent.context).inflate(
            layoutType,
            parent,
            false
        )
        view.isHapticFeedbackEnabled = true
        return ShopItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].enabled) {
            ENABLED_VIEW_TYPE
        } else DISABLED_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        val status = if (shopItem.enabled) "Active" else "Inactive"
        holder.tvTitle.text = "${shopItem.name}, $status"
        holder.tvCount.text = shopItem.count.toString()
        holder.tvMeasure.text = shopItem.measure

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
    }
}