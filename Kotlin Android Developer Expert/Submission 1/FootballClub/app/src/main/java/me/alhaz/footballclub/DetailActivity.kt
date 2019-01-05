package me.alhaz.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*


class DetailActivity: AppCompatActivity() {

    private var name: String = ""
    private var image: Int = 0
    private var description: String = ""
    lateinit var logoImageView: ImageView
    lateinit var nameTextView: TextView
    lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         *
         * Anko Layout
         *
         */

        verticalLayout {
            padding = dip(16)
            gravity = Gravity.CENTER_HORIZONTAL
            logoImageView = imageView().lparams(width = dip(50), height = dip(50))
            nameTextView = textView() {
                this.gravity = Gravity.CENTER
            }.lparams(){
                topMargin = dip(16)
            }
            descriptionTextView = textView() {
                this.gravity = Gravity.CENTER
            }.lparams(){
                topMargin = dip(16)
            }
        }

        val intent = intent
        name = intent.getStringExtra("name")
        image = intent.getIntExtra("image", 0)
        description = intent.getStringExtra("description")

        /**
         *
         * Android XT
         *
         */

        nameTextView.text = name
        nameTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 16f)

        descriptionTextView.text = description
        descriptionTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14f)

        Glide.with(this).load(image).into(logoImageView)
    }

}