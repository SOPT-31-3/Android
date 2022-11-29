package com.example.triple_aos.util

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GridItemOffsetDecoration(private val context: Context, private val mItemOffset: Int) : RecyclerView.ItemDecoration() {

    private val offset = mItemOffset.dpToPx(context)

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // spanIndex = 0 -> 왼쪽
        // spanIndex = 1 -> 가운데
        // spanIndex = 2 -> 오른쪽
        val lp = view.layoutParams as GridLayoutManager.LayoutParams
        when (lp.spanIndex) {
            0 -> {
                outRect.right = offset
                outRect.bottom = offset*2
            }
            1 -> {
                outRect.left = offset
                outRect.right = offset
                outRect.bottom = offset*2
            }
            2 -> {
                outRect.left = offset
                outRect.bottom = offset*2
            }
        }
    }

    // dp -> pixel 단위로 변경

    private fun Int.dpToPx(context: Context): Int {
        return context.resources.displayMetrics.density.let { density ->
            (this * density).toInt()
        }

    }
}