package pt.ipt.databiding

import android.view.View

data class MyBinding(var txtDizOla: String) {

    fun mostraBotao():Int {
        if (txtDizOla.length < 20)
            return View.VISIBLE
        else
            return View.INVISIBLE
    }

}