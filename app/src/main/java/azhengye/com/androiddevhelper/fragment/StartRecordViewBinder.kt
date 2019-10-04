package azhengye.com.androiddevhelper.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import azhengye.com.androiddevhelper.R
import azhengye.com.androiddevhelper.beans.StartRecordBean
import azhengye.com.androiddevhelper.managers.RecordManager
import com.drakeet.multitype.ItemViewBinder

/**
 * Created by XiangKang on 2019-10-03.
 */
class StartRecordViewBinder(val context: Context?) :
    ItemViewBinder<StartRecordBean, StartRecordViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflater.inflate(R.layout.item_start_record, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, item: StartRecordBean) {
        holder.startRecordBean = item;
        holder.desc.setText(item.descText)
        holder.startBtn.setText(if (item.start) context?.getString(R.string.start) else context?.getString(R.string.stop))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val desc: TextView = itemView.findViewById(R.id.start_record_desc)
        val startBtn: TextView = itemView.findViewById(R.id.start_btn)
        lateinit var startRecordBean: StartRecordBean

        init {
            startBtn.setOnClickListener({
                if (startRecordBean.start) RecordManager(context).startRecord() else RecordManager(context).stopRecord()
                startRecordBean.start = !startRecordBean.start
                adapter.notifyItemChanged(getPosition(this))
            })
        }
    }
}