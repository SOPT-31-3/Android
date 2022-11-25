package com.example.triple_aos.presentation.new_plan

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.triple_aos.R
import com.example.triple_aos.base.BindingActivity
import com.example.triple_aos.databinding.ActivityNewPlanBinding

class NewPlanActivity : BindingActivity<ActivityNewPlanBinding>(R.layout.activity_new_plan) {

    private val tabPlanFragment = PlanFragment()
    private val flightFragment = FlightFragment()
    private val foodFragment = FoodFragment()
    private val tourFragment = TourFragment()
    private val accommodationFragment = AccomodationFragment()

    val fragments = arrayListOf<Fragment>(
        tabPlanFragment,
        flightFragment,
        accommodationFragment,
        foodFragment,
        tourFragment
    )
    val tabAdapter = object : FragmentStateAdapter(this) {
        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        binding.vpNewPlan.adapter = tabAdapter
        binding.vpNewPlan.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabNewPlan.selectTab(binding.tabNewPlan.getTabAt(position))
            }
        })
    }

}