package com.going.jetpackstudy.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment


inline fun<reified clz:Activity> Context.jump(configIntent: Intent.() -> Unit) {
    startActivity(Intent(this, clz::class.java).apply(configIntent))
}

inline fun<reified clz:Activity> Fragment.jump(configIntent: Intent.() -> Unit) {
    context?.apply { startActivity(Intent(this, clz::class.java).apply(configIntent))  }
}
