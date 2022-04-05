package com.lumi.coinedoneblockurlapp.ui.schedule.schedule_page

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lumi.coinedoneblockurlapp.databinding.FragmentScheduleBinding
import com.lumi.coinedoneblockurlapp.dialog.dialog_add_timing.TimeDialogFragment
import com.lumi.coinedoneblockurlapp.dialog.dialog_add_timing.TimeDialogViewModel
import com.lumi.coinedoneblockurlapp.preferences.SharedPrefs


class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding
    private lateinit var timeDialogViewModel: TimeDialogViewModel

    companion object{
        fun getInstance() : ScheduleFragment{
            return ScheduleFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        timeDialogViewModel = ViewModelProvider(this).get(TimeDialogViewModel::class.java)

        timeDialogViewModel.fromTime.observe(this, Observer {
            val data = it
            SharedPrefs.setInt("from_time", data.toInt())

        })

        timeDialogViewModel.toTime.observe(this, Observer {
            val data =it
            SharedPrefs.setInt("to_time", data.toInt())
        })

        if (!SharedPrefs.getBoolean("is_time_added", false)){
//            TimeDialogFragment.newInstance()
//                .show(childFragmentManager, "")
        }else{
            TimeDialogFragment.newInstance()
                    .show(childFragmentManager, "")

        }

        _binding?.tvMode?.setOnClickListener{

        }

        _binding?.sctMode?.setOnCheckedChangeListener { compoundButton: CompoundButton, isChecked: Boolean ->

            SharedPrefs.setBoolean("is_checked_blacklist", isChecked)

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}