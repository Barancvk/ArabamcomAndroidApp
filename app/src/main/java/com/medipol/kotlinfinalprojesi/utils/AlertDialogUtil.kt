package com.medipol.kotlinfinalprojesi.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.provider.Settings
import androidx.appcompat.app.AlertDialog

object AlertDialogUtil{
    private lateinit var binding: AlertDialogUtil

    var resources: Resources? = null
    fun alertDialogShow(context: Context, title: String?, message: String?, yes: String?, no: String?, screen: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(yes, DialogInterface.OnClickListener { window, i ->
            if (screen == "SplashActivity") {
                context.startActivity(Intent(Settings.ACTION_SETTINGS))
            } else {
                (context as Activity).onBackPressed()
                (context as Activity).finish()
            }
        })
        builder.setNegativeButton(no, DialogInterface.OnClickListener { window, i ->
            if (screen == "SplashActivity") {
                (context as Activity).finish()
            } else {
                window.dismiss()
            }
        })
        builder.show()
    }
}