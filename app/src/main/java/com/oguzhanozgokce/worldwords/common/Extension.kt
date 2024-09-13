package com.oguzhanozgokce.worldwords.common

import android.view.View

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.toggleVisibility(){
    if (this.visibility == View.VISIBLE){
        this.visibility = View.INVISIBLE
    }else{
        this.visibility = View.VISIBLE
    }
}
