package com.whiteside.bokemoncourse.base

import android.app.AlertDialog
import android.content.Context
import com.whiteside.bokemoncourse.R

class LoadingDialog  {

    var dialog: AlertDialog? = null

    fun show(context: Context) {
        dialog = AlertDialog.Builder(context)
            .setCancelable(false)
            .setView(R.layout.loading_dialog)
            .create()

        dialog!!.show()
    }

    fun dismiss() {
        dialog!!.cancel()
    }
}