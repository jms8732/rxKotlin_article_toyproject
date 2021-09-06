package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.NEWS_TYPE
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentJoongangBinding
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.adapter.JoongangAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class JoongangFragment : BindingFragment<FragmentJoongangBinding>() {
    override fun getLayout(): Int = R.layout.fragment_joongang
    private lateinit var jAdapter: JoongangAdapter
    private val vm: MainActivityViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initObserver()
    }

    @SuppressLint("CheckResult")
    private fun initObserver() {
        vm.loadNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.channel?.run {
                    jAdapter.submitList(item)
                }
            }, {
                it.printStackTrace()
            }, {

            }).apply {
                disposable.add(this)
            }

    }

    private fun initUI() {
        binding.recyclerView.run {
            jAdapter = JoongangAdapter(vm)
            adapter = jAdapter

            layoutManager = LinearLayoutManager(requireContext())
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