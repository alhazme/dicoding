package me.alhaz.snippet.mcdragon

import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat
import android.support.v7.widget.Toolbar
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.graphics.Color.parseColor
import android.support.v4.graphics.ColorUtils
import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text


class DetailActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var ivPhoto: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvPrice: TextView
    private lateinit var btnOrder: Button

    var menu: Menu = Menu()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setContentView(R.layout.activity_detail)

        getDefaultData()
        setupLayout()
    }

    private fun hideStatusBar(){
        if (Build.VERSION.SDK_INT >= 16) {
            getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
            getWindow().getDecorView().setSystemUiVisibility(3328);
        }
        else{
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private fun getDefaultData() {
        intent?.let {
            menu = it.getParcelableExtra("menu")
        }
    }

    private fun setupLayout() {
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_navigation_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed();
        }

        collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsible_toolbar)
        collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.SANS_SERIF)
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorBlack))
        collapsingToolbarLayout.setCollapsedTitleTypeface(Typeface.SANS_SERIF)
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite))

        ivPhoto = findViewById<ImageView>(R.id.iv_photo)
        ivPhoto.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGray))
        Glide.with(this).load(menu.photo).into(ivPhoto)

        tvName = findViewById<TextView>(R.id.tv_name)
        tvName.text = menu.name
        tvDescription = findViewById<TextView>(R.id.tv_description)
        tvDescription.text = menu.description
        tvPrice = findViewById<TextView>(R.id.tv_price)
        tvPrice.text = "Rp " + menu.price

        btnOrder = findViewById<Button>(R.id.btn_order)
        btnOrder.setOnClickListener {
            Toast.makeText(this, "Kamu telah memesan " + menu.name, Toast.LENGTH_SHORT).show()
        }

        appBarLayout = findViewById<AppBarLayout>(R.id.appbar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange >= -100 ) {
                //  Collapsed
                toolbar.getNavigationIcon()?.let {
                    it.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbarLayout.setTitle(menu.name)
                    tvName.visibility = View.GONE
                }
            }
            else {
                //Expanded
                toolbar.getNavigationIcon()?.let {
                    it.setColorFilter(ContextCompat.getColor(this, R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbarLayout.setTitle("")
                    tvName.visibility = View.VISIBLE
                }
            }
        })
    }


}
