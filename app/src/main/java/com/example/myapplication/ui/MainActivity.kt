package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.BindingActivity
import com.example.myapplication.JOOGNANG
import com.example.myapplication.R
import com.example.myapplication.YONHAP
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragment.JoongangFragment
import com.example.myapplication.ui.fragment.YonhapFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.orhanobut.logger.Logger
import org.koin.android.ext.android.inject

class MainActivity : BindingActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main
    private val viewModel by inject<MainActivityViewModel>()
    private val fragmentList = arrayOfNulls<Fragment>(2)
    private val nameList = arrayOf("중앙일보","연합뉴스")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        initObserver()
    }

    private fun initUI() {
        binding.viewpager2.run {
            adapter = ViewPagerAdapter()
        }

        TabLayoutMediator(binding.tabLayout,binding.viewpager2){ tab, pos ->
            tab.text = nameList[pos]
        }.attach()

    }

    private fun initObserver(){
        viewModel.item.observe(this){

            Logger.e("Item : ${it.toString()}")
        }
    }


    inner class ViewPagerAdapter : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment {
            if (fragmentList[position] == null)
                fragmentList[position] = createNewsFragment(position)

            return fragmentList[position]!!

        }

        private fun createNewsFragment(pos: Int): Fragment {
            return when (pos) {
                0 -> JoongangFragment.newInstance(JOOGNANG)
                else -> YonhapFragment.newInstance(YONHAP)
            }
        }
    }
}

