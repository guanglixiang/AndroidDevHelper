package azhengye.com.androiddevhelper.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import azhengye.com.androiddevhelper.R
import azhengye.com.androiddevhelper.beans.SwitchFloatingBtnBean
import com.drakeet.multitype.ItemViewBinder

/**
 * Created by XiangKang on 2019-10-03.
 */
class SwitchFloatingBtnViewBinder : ItemViewBinder<SwitchFloatingBtnBean, SwitchFloatingBtnViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_switch_floating_btn, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: SwitchFloatingBtnBean) {
        holder.switchBtn.isChecked = item.enable
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val switchBtn: SwitchCompat = itemView.findViewById(R.id.switch_btn)
    }
}