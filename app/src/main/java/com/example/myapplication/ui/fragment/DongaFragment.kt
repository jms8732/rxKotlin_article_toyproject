package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.NEWS_TYPE
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDongaBinding
import com.example.myapplication.model.DongaRss
import com.example.myapplication.model.YonhapRss
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.adapter.DongaAdapter
import com.example.myapplication.utils.removeTag
import com.example.myapplication.utils.withThread
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.toObservable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DongaFragment : BindingFragment<FragmentDongaBinding>(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.fragment_donga

    private lateinit var dongaAdapter: DongaAdapter
    private val vm by sharedViewModel<MainActivityViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())

            dongaAdapter = DongaAdapter(vm)
            adapter = dongaAdapter
        }

        loading = binding.lottieLoading
        swipe = binding.swipeRefreshLayout

        binding.run {
            dongaTotal.setOnClickListener(this@DongaFragment)
            dongaScience.setOnClickListener(this@DongaFragment)
            dongaCulture.setOnClickListener(this@DongaFragment)
            dongaNational.setOnClickListener(this@DongaFragment)
            dongaInternational.setOnClickListener(this@DongaFragment)
            dongaEconomy.setOnClickListener(this@DongaFragment)
            dongaEditorial.setOnClickListener(this@DongaFragment)
            dongaPolitic.setOnClickListener(this@DongaFragment)
            dongaSport.setOnClickListener(this@DongaFragment)

            dongaTotal.performClick()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            dongaAdapter.submitList(null) {
                vm.fetchDongaNews(binding.chipGroup.checkedChipId)
                    ?.withThread()
                    ?.subscribe(observer)
            }
        }

    }

    @SuppressLint("CheckResult")
    override fun onResult(t: Any) {
        Observable.just(t)
            .cast(DongaRss::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.channel?.run {
                    item?.asSequence()
                        ?.map {
                            it.description = it.description?.removeTag()
                        }?.toList()

                    dongaAdapter.submitList(item) {
                        binding.lottieLoading.visibility = View.GONE

                        if(binding.swipeRefreshLayout.isRefreshing)
                            binding.swipeRefreshLayout.isRefreshing = false
                    }
                }
            }, {
                it.printStackTrace()
            }, {

            })
    }

    override fun onClick(v: View?) {
        v?.run {
            dongaAdapter.submitList(null) {
                binding.lottieLoading.visibility = View.VISIBLE
                vm.fetchDongaNews(id)
                    ?.withThread()
                    ?.subscribe(observer)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(type: String) = DongaFragment().apply {
            val bundle = Bundle().apply {
                putString(NEWS_TYPE, type)
            }
            arguments = bundle
        }
    }
}