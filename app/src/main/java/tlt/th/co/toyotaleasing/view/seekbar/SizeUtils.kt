package tlt.th.co.toyotaleasing.view.seekbar

import android.content.Context
import android.util.TypedValue

class SizeUtils {

    companion object {

        fun dp2px(context: Context, dpValue: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.resources.displayMetrics).toInt()
        }

        fun sp2px(context: Context, spValue: Float) : Int {
            return (spValue * context.resources.displayMetrics.scaledDensity + 0.5f).toInt()
        }

        fun px2sp(context: Context, pxValue: Float) : Int {
            return (pxValue / context.resources.displayMetrics.scaledDensity + 0.5f).toInt()
        }
    }

}