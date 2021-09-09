package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.NEWS_TYPE
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentYonhapBinding
import com.example.myapplication.model.YonhapRss
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.adapter.YonhapAdapter
import com.example.myapplication.utils.withThread
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class YonhapFragment : BindingFragment<FragmentYonhapBinding>(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.fragment_yonhap
    private lateinit var yAdapter: YonhapAdapter
    private val vm by sharedViewModel<MainActivityViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())

            yAdapter = YonhapAdapter(vm)
            adapter = yAdapter
        }

        loading = binding.lottieLoading

        binding.run {
            yonhapCulture.setOnClickListener(this@YonhapFragment)
            yonhapRecent.setOnClickListener(this@YonhapFragment)
            yonhapEconomic.setOnClickListener(this@YonhapFragment)
            yonhapHeadline.setOnClickListener(this@YonhapFragment)
            yonhapLocal.setOnClickListener(this@YonhapFragment)
            yonhapPolitic.setOnClickListener(this@YonhapFragment)
            yonhapSport.setOnClickListener(this@YonhapFragment)
            yonhapWeather.setOnClickListener(this@YonhapFragment)
            yonhapWorld.setOnClickListener(this@YonhapFragment)
            yonhapSocial.setOnClickListener(this@YonhapFragment)

            yonhapRecent.performClick()
        }

    }

    @SuppressLint("CheckResult")
    override fun onResult(t: Any) {
        Observable.just(t)
            .cast(YonhapRss::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.channel?.run {
                    yAdapter.submitList(item) {
                        binding.lottieLoading.visibility = View.GONE
                    }
                }
            }, {
                it.printStackTrace()
            }, {

            })
    }

    override fun onClick(v: View?) {
        v?.run {
            yAdapter.submitList(null) {
                binding.lottieLoading.visibility = View.VISIBLE
                vm.fetchYonhapNews(id)
                    ?.withThread()
                    ?.subscribe(observer)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(type: String) = YonhapFragment().apply {
            val bundle = Bundle().apply {
                putString(NEWS_TYPE, type)
            }
            arguments = bundle
        }
    }
}