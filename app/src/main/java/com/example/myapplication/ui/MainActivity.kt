package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.*
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.*
import com.example.myapplication.ui.fragment.*
import com.orhanobut.logger.Logger
import org.koin.android.compat.ViewModelCompat.getViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BindingActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main
    private lateinit var viewModel: MainActivityViewModel
    private val fragmentList = arrayOfNulls<Fragment>(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        viewModel = getViewModel()

        initUI()
        initObserver()
    }

    private fun initUI() {
        binding.viewpager2.run {
            isUserInputEnabled = false
            adapter = FragmentAdapter()
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
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
            binding.viewpager2.setCurrentItem(position, true)
        }

    }

    private fun initObserver() {
        viewModel.item.observe(this) {
            Logger.e("Item : ${it.toString()}")
            Intent(this@MainActivity, WebViewActivity::class.java).run {
                putExtra(URL, when(it){
                    is Item -> it.link
                    is DongaItem -> it.link
                    is EtnewsItem -> it.link
                    is KoreaHeraldItem -> it.link
                    is YonhapItem -> it.link
                    else -> null
                })
                startActivity(this)
            }
        }
    }

    inner class FragmentAdapter : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment {
            val fragment = fragmentList[position] ?: inflateFragment(position)

            if (fragment == null)
                fragmentList[position] = fragment

            return fragment
        }

        private fun inflateFragment(position: Int): Fragment = when (position) {
            0 -> JoongangFragment.newInstance(JOONGANG)
            1 -> YonhapFragment.newInstance(YONHAP)
            2 -> EtnewsFragment.newInstance(ETNEWS)
            3 -> DongaFragment.newInstance(DONGA)
            else -> KoreaHeraldFragment.newInstance(HERALD)
        }
    }

}

