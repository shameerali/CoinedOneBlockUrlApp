package com.lumi.coinedoneblockurlapp.ui.schedule.schedule_start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lumi.coinedoneblockurlapp.databinding.FragmentScheduleStartBinding
import com.lumi.coinedoneblockurlapp.dialog.dialog_add_timing.TimeDialogViewModel
import com.lumi.coinedoneblockurlapp.ui.schedule.ScheduleActivity
import com.lumi.coinedoneblockurlapp.ui.schedule.schedule_page.ScheduleFragment

class ScheduleStartFragment : Fragment(){

    private lateinit var timeDialogViewModel: TimeDialogViewModel

    private var _binding: FragmentScheduleStartBinding?=null
    private val binding get() = _binding

    companion object{
        public fun getInstance() : ScheduleStartFragment{
            return ScheduleStartFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentScheduleStartBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){


        timeDialogViewModel =ViewModelProvider(this).get(TimeDialogViewModel::class.java)
        timeDialogViewModel.fromTime.observe(this, Observer {
            val data = it

        })

        timeDialogViewModel.toTime.observe(this, Observer {
            val data =it

        })


        binding?.ivAddSchedule?.setOnClickListener{

            (activity as ScheduleActivity?)!!.launchFragment(ScheduleFragment.getInstance(),
                "SCHEDULE_FRAGMENT")

//            TimeDialogFragment.newInstance()
//                .show(childFragmentManager, "")

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}