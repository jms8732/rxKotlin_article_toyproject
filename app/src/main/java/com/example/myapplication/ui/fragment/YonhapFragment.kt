package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.NEWS_TYPE
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentYonhapBinding
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.adapter.YonhapAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class YonhapFragment : BindingFragment<FragmentYonhapBinding>() {
    override fun getLayout(): Int = R.layout.fragment_yonhap
    private lateinit var yAdapter: YonhapAdapter
    private val vm by sharedViewModel<MainActivityViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initObserver()
    }

    private fun initUI() {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())

            yAdapter = YonhapAdapter(vm)
            adapter = yAdapter
        }
    }

    @SuppressLint("CheckResult")
    private fun initObserver() {
        vm.fetchNews()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                it.channel?.run {
                    yAdapter.submitList(item)
                }
            }.apply{
                disposable.add(this)
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