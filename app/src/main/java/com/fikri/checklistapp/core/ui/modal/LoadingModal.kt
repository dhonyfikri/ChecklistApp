package com.fikri.checklistapp.core.ui.modal

import android.content.Context

class LoadingModal(private val context: Context) : MyModal() {
    companion object {
        const val TYPE_GENERAL = "general"
    }

    fun showLoadingModal(
        type: String = TYPE_GENERAL,
        message: String?
    ) {
        dismiss()
//        modal = Dialog(context)
//        modal?.setContentView(R.layout.common_loading)
//        val ivIllustration = modal?.findViewById<ImageView>(R.id.iv_illustration)
//        val tvMessage = modal?.findViewById<TextView>(R.id.tv_message)
//
//        if (ivIllustration != null) {
//            Glide.with(context).load(
//                when (type) {
//                    TYPE_GENERAL -> ContextCompat.getDrawable(context, R.drawable.il_loading)
//                    else -> ContextCompat.getDrawable(context, R.drawable.il_loading)
//                }
//            ).into(ivIllustration)
//        }
//        tvMessage?.text = message

        showModal()
    }
}