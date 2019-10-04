package azhengye.com.androiddevhelper.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import azhengye.com.androiddevhelper.R
import azhengye.com.androiddevhelper.beans.StartRecordBean
import azhengye.com.androiddevhelper.beans.SwitchFloatingBtnBean
import azhengye.com.androiddevhelper.managers.RecordManager
import azhengye.com.androiddevhelper.sp.SWITCH_FLOATING_BTN
import azhengye.com.androiddevhelper.sp.SharedPreference
import com.drakeet.multitype.MultiTypeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by XiangKang on 2019-10-02.
 */

class HomeFragment : BaseFragment() {

    private val adapter = MultiTypeAdapter()
    private val items = ArrayList<Any>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewData()
        initRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        RecordManager.getInstance(context).onActivityResult(requestCode, resultCode, data)
    }

    private fun initRecyclerView() {
        adapter.register(SwitchFloatingBtnViewBinder())
        adapter.register(StartRecordViewBinder(context))
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.shape__home_divider, null))
        recycler_view.addItemDecoration(divider)
        recycler_view.adapter = adapter
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    private fun initRecyclerViewData() {
        val switchFloatingBtnItem = SwitchFloatingBtnBean(getSwitchFloatingBtnStatus())
        val startRecordBean = StartRecordBean(getString(R.string.start_record_desc), true)
        items.add(switchFloatingBtnItem)
        items.add(startRecordBean)
    }

    private fun getSwitchFloatingBtnStatus(): Boolean {
        val sp = context?.let { SharedPreference(it) }
        return sp!!.getValueBoolien(SWITCH_FLOATING_BTN, false)
    }
}
