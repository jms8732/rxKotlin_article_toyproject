package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.NEWS_TYPE
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentJoongangBinding
import com.example.myapplication.model.Rss
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.adapter.JoongangAdapter
import com.example.myapplication.utils.withThread
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class JoongangFragment : BindingFragment<FragmentJoongangBinding>(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.fragment_joongang
    private lateinit var jAdapter: JoongangAdapter
    private val vm: MainActivityViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }


    @SuppressLint("CheckResult")
    private fun initUI() {
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
        vm.logo.value = R.drawable.ic_joongang_icon
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
                            it.description = refineString(it.description)
                        }?.toList()
                    jAdapter.submitList(item) {
                        binding.lottieLoading.visibility = View.GONE
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