package com.reznov.fragmentku

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment


class OptionDialogFragment : DialogFragment(), View.OnClickListener {

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbEzio: RadioButton
    private lateinit var rbAltair: RadioButton
    private lateinit var rbGil: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnChoose.setOnClickListener(this)
        btnClose = view.findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        rgOptions = view.findViewById(R.id.rg_options)
        rbEzio = view.findViewById(R.id.rb_ezio)
        rbAltair = view.findViewById(R.id.rb_altair)
        rbGil = view.findViewById(R.id.rb_gil)
    }
    override fun onAttach(context: Context){
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment){
            val detailCategoryFragment = fragment
            this.optionDialogListener = detailCategoryFragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }


    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_close -> dialog?.cancel()

            R.id.btn_choose ->{
                val checkedRadioButtonId = rgOptions.checkedRadioButtonId
                if (checkedRadioButtonId !=1){
                    var hero: String? = null
                    when (checkedRadioButtonId){
                        R.id.rb_ezio -> hero = rbEzio.text.toString().trim()
                        R.id.rb_altair -> hero = rbAltair.text.toString().trim()
                        R.id.rb_gil -> hero = rbGil.text.toString().trim()
                    }
                    if (optionDialogListener != null){
                        optionDialogListener?.onOptionChosen(hero)
                    }
                    dialog?.dismiss()
                }
            }
        }
    }
    interface OnOptionDialogListener{
        fun onOptionChosen(text: String?)
    }
}