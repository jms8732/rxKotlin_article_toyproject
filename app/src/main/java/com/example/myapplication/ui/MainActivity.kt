package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.*
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragment.EtnewsFragment
import com.example.myapplication.ui.fragment.JoongangFragment
import com.example.myapplication.ui.fragment.YonhapFragment
import com.orhanobut.logger.Logger
import org.koin.android.ext.android.inject

class MainActivity : BindingActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main
    private val viewModel by inject<MainActivityViewModel>()
    private val fragmentList = arrayOfNulls<Fragment>(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        initObserver()
    }

    private fun initUI() {
        binding.viewpager2.run{
            isUserInputEnabled = false
            adapter =  FragmentAdapter()
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bubbleNavigation.setCurrentActiveItem(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
        }


        binding.bubbleNavigation.setNavigationChangeListener { _, position ->
            binding.viewpager2.setCurrentItem(position,true)
        }
    }

    private fun initObserver(){
        viewModel.item.observe(this){

            Logger.e("Item : ${it.toString()}")
        }
    }

    inner class FragmentAdapter : FragmentStateAdapter(this){
        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment {
            val fragment = fragmentList[position] ?: inflateFragment(position)

            if(fragment == null)
                fragmentList[position] = fragment

            return fragment
        }

        private fun inflateFragment(position : Int) : Fragment = when(position){
            0 -> JoongangFragment.newInstance(JOONGANG)
            1-> YonhapFragment.newInstance(YONHAP)
            else -> EtnewsFragment.newInstance(JTBC)
        }
    }

}

