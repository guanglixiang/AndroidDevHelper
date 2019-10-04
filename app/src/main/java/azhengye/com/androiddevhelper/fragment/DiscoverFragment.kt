package azhengye.com.androiddevhelper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import azhengye.com.androiddevhelper.R

/**
 * Created by XiangKang on 2019-10-02.
 */
class DiscoverFragment :BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discover,container)
    }
}