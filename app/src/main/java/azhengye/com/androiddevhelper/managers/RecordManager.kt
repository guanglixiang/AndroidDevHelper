package azhengye.com.androiddevhelper.managers

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import azhengye.com.androiddevhelper.utils.DeviceUtils
import azhengye.com.androiddevhelper.utils.Utils


/**
 * Created by XiangKang on 2019-10-03.
 */
final class RecordManager(val ctx: Context?) {
    private val TAG = "RecordManager"
    private val context: Context? = ctx
    private lateinit var projectionManager:MediaProjectionManager
    private lateinit var mediaProjection: MediaProjection
    private lateinit var virtualDisplay: VirtualDisplay

    private val PERMISSION_CODE = 1

    companion object {
        @Volatile
        private var instance: RecordManager? = null
        fun getInstance(context: Context?): RecordManager {
            val i = instance
            if (i != null) {
                return i
            }

            return synchronized(this) {
                val i2 = instance
                if (i2 != null) {
                    i2
                } else {
                    val created = RecordManager(context)
                    instance = created
                    created
                }
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode !== PERMISSION_CODE) {
            Log.e(TAG, "Unknown request code: $requestCode")
            return
        }
        if (resultCode !== RESULT_OK) {
            Toast.makeText(
                context,
                "User denied screen sharing permission", Toast.LENGTH_SHORT
            ).show()
            return
        }
        mediaProjection = projectionManager.getMediaProjection(resultCode, data)
        virtualDisplay = createVirtualDisplay()
    }

    private fun createVirtualDisplay(): VirtualDisplay {
        var activity = Utils.getActivityFromContext(context)
        return mediaProjection.createVirtualDisplay(
            "ScreenSharingDemo",
            DeviceUtils.getScreenWidth(activity),
            DeviceUtils.getScreenHeight(activity),
            DeviceUtils.getScreenDensity(activity),
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            null,
            null /*Callbacks*/,
            null /*Handler*/
        );
    }

    fun startRecord() {
        projectionManager = context?.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        (context as AppCompatActivity).startActivityForResult(projectionManager.createScreenCaptureIntent(),PERMISSION_CODE);
    }

    fun stopRecord() {

    }
}