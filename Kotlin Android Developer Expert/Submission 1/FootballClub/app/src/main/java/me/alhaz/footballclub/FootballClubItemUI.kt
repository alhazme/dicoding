package me.alhaz.footballclub

import android.view.Gravity
import android.view.Gravity.CENTER_VERTICAL
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import org.jetbrains.anko.*

class FootballClubItemUI: AnkoComponent<ViewGroup> {

    companion object {
        val logoImageView = 1
        val nameTextView = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {

        verticalLayout {
            layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            imageView {
                id = logoImageView
                layoutParams = LinearLayout.LayoutParams(dip(50), dip(50))
                setImageResource(R.drawable.img_barca)
            }

            textView {
                id = nameTextView
                gravity = Gravity.CENTER
            }.lparams(wrapContent, wrapContent) {
                margin = dip(10)
            }

        }

    }


}