package net.swimmi.androidlibrary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.item_treasure.view.*
import kotlinx.android.synthetic.main.item_treasure_tag.view.*
import net.swimmi.androidlibrary.R
import net.swimmi.androidlibrary.model.Treasure
import net.swimmi.androidlibrary.utils.DataUtil

class TreasureAdapter(var context: Context, var data: List<Treasure>, var layoutId: Int) : RecyclerView.Adapter<TreasureAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreasureAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }


    private lateinit var mItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setViews(context, data[position])

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(it, position)
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun setViews(context: Context, item: Treasure) {
            itemView.tv_title.text = item.title
            itemView.tv_desc.text = item.desc
            itemView.iv_image.setImageBitmap(DataUtil(context).getTreasureImage(item.id))
            if (item.tags != null) {
                for (tag in item.tags!!) {
                    val tagTv = LayoutInflater.from(context).inflate(R.layout.item_treasure_tag, null)
                    tagTv.tv_tag.text = tag
                    val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    layoutParams.setMargins(0, 0, 20, 0)
                    tagTv.layoutParams = layoutParams
                    itemView.ll_tags.addView(tagTv)
                }
            }
            itemView.tv_datetime.text = item.datetime
        }
    }
}