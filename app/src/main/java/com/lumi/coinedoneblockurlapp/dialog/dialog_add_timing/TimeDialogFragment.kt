package com.lumi.coinedoneblockurlapp.dialog.dialog_add_timing

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.lumi.coinedoneblockurlapp.databinding.DialogAddTimeBinding
import com.lumi.coinedoneblockurlapp.preferences.SharedPrefs

class TimeDialogFragment : DialogFragment() {

    private lateinit var viewModel: TimeDialogViewModel
    private var _binding:DialogAddTimeBinding ?= null
    private val binding get() = _binding

            companion object {
        fun newInstance(): TimeDialogFragment {
            val args = Bundle()

            val fragment = TimeDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddTimeBinding.inflate(inflater, container, false)

        return  _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =ViewModelProvider(requireParentFragment()).get(TimeDialogViewModel::class.java)
        setUpView(view)
    }

    private fun setUpView(view: View) {

        binding?.etFrom?.setOnClickListener{

            val timePicker:TimePickerDialog = TimePickerDialog(context,
                    timePickerDialogListener,
                    12,
                    10,
                    false)

//
            timePicker.show()

        }

        _binding?.ivClose?.setOnClickListener{
            dismiss()
        }

        _binding?.btSave?.setOnClickListener{
            val fromVal = binding?.etFrom?.text.toString()
            val toVal = binding?.etTo?.text.toString()

            if (fromVal == "" ||toVal == ""){
                Toast.makeText(context, "Enter Value",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intFromVal = fromVal.toInt();
            val intToVal = toVal.toInt();

            if (intFromVal > 24 || intToVal > 24){
                Toast.makeText(context, "Enter Value 1 to 24",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            SharedPrefs.setBoolean("is_time_added", true)
            viewModel.sendTime(_binding?.etFrom?.text.toString(),_binding?.etTo?.text.toString())
            dismiss()
        }

    }




    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout( ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener=
            object :TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                    Log.d("TAG", "onTimeSet: "+hourOfDay+"  "+minute)

                }

            }


}