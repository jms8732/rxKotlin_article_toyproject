package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.NEWS_TYPE
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentJoongangBinding
import com.example.myapplication.model.Rss
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.adapter.JoongangAdapter
import com.example.myapplication.utils.refineString
import com.example.myapplication.utils.withThread
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class JoongangFragment : BindingFragment<FragmentJoongangBinding>(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.fragment_joongang
    private lateinit var jAdapter: JoongangAdapter
    private val vm: MainActivityViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initUI()
    }


    @SuppressLint("CheckResult")
    private fun initUI() {
        Logger.e("joonang initUI")
        binding.recyclerView.run {
            jAdapter = JoongangAdapter(vm)
            adapter = jAdapter

            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.run{
            joongangMain.setOnClickListener(this@JoongangFragment)
            joongangEconomy.setOnClickListener(this@JoongangFragment)
            joongangPolicy.setOnClickListener(this@JoongangFragment)
            joongangCulture.setOnClickListener(this@JoongangFragment)
            joongangITScience.setOnClickListener(this@JoongangFragment)
            joongangWorld.setOnClickListener(this@JoongangFragment)
            joongangSocial.setOnClickListener(this@JoongangFragment)

            joongangMain.performClick()
        }

        vm.title.value = "중앙 일보"

        loading = binding.lottieLoading
        swipe = binding.swipeRefreshLayout

        binding.swipeRefreshLayout.setOnRefreshListener {
            jAdapter.submitList(null) {
                vm.fetchJoongangNews(binding.chipGroup.checkedChipId)
                    ?.withThread()
                    ?.subscribe(observer)
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun onResult(t: Any) {
        //여기로 데이터가 온다.
        Observable.just(t)
            .cast(Rss::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.channel?.run {
                    item?.asSequence()
                        ?.map {
                            it.description = it.description?.refineString()
                        }?.toList()
                    jAdapter.submitList(item) {
                        binding.lottieLoading.visibility = View.GONE

                        if(binding.swipeRefreshLayout.isRefreshing)
                            binding.swipeRefreshLayout.isRefreshing = false
                    }

                }
            }, { it.printStackTrace() }, {

            })

    }

    override fun onClick(v: View?) {
        v?.run {
            jAdapter.submitList(null) {
                binding.lottieLoading.visibility = View.VISIBLE
                vm.fetchJoongangNews(id)
                    ?.withThread()
                    ?.subscribe(observer)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.e("joongang destroy view")
    }

    companion object {
        @JvmStatic
        fun newInstance(type: String) = JoongangFragment().apply {
            val bundle = Bundle().apply {
                putString(NEWS_TYPE, type)
            }
            arguments = bundle
        }
    }
}