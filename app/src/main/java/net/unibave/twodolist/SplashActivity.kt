package net.unibave.twodolist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        val context = this

        val logo: ImageView = findViewById(R.id.logo)

        logo.startAnimation(
            AnimationUtils.loadAnimation(context, R.anim.splash_in)
        )

        handler.postDelayed({
            overridePendingTransition(R.anim.splash_in, R.anim.splash_out)
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 3000)

    }
}
