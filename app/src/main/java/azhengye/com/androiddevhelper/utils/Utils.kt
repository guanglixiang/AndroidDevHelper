package azhengye.com.androiddevhelper.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by XiangKang on 2019-10-03.
 */
object Utils {
    fun getActivityFromContext(ctx: Context?): AppCompatActivity {
        var context = ctx
        while (context is ContextWrapper) {
            if (context is AppCompatActivity) {
                return context
            }
            context = context.baseContext
        }
        return null!!
    }
}