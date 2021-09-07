package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.NEWS_TYPE
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEtBinding
import com.example.myapplication.model.EtnewsRss
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.adapter.EtnewsAdapter
import com.example.myapplication.utils.withThread
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EtnewsFragment : BindingFragment<FragmentEtBinding>(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.fragment_et
    private val vm by sharedViewModel<MainActivityViewModel>()
    private lateinit var etnewsAdapter: EtnewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())

            etnewsAdapter = EtnewsAdapter(vm)
            adapter = etnewsAdapter
        }

        binding.run {
            etEconomy.setOnClickListener(this@EtnewsFragment)
            etFlash.setOnClickListener(this@EtnewsFragment)
            etInternational.setOnClickListener(this@EtnewsFragment)
            etLocal.setOnClickListener(this@EtnewsFragment)
            etPopular.setOnClickListener(this@EtnewsFragment)
            etSw.setOnClickListener(this@EtnewsFragment)
            etToday.setOnClickListener(this@EtnewsFragment)

            etToday.performClick()
        }

        loading = binding.lottieLoading
    }

    @SuppressLint("CheckResult")
    override fun onResult(t: Any) {
        Observable.just(t)
            .cast(EtnewsRss::class.java)
            .subscribe({
                it.channel?.run {
                    etnewsAdapter.submitList(item){
                        binding.lottieLoading.visibility = View.GONE
                    }
                }
            }, {
               it.printStackTrace()
            }, {

            })
    }

    override fun onClick(v: View?) {
        etnewsAdapter.submitList(null) {
            binding.lottieLoading.visibility = View.VISIBLE
            v?.run {
                vm.fetchEtNews(id)
                    ?.withThread()
                    ?.subscribe(observer)
            }
        }
    }

    companion object{

        @JvmStatic
        fun newInstance(type : String) = EtnewsFragment().apply{
            val bundle = Bundle().apply{
                putString(NEWS_TYPE,type)
            }
            arguments = bundle
        }
    }
}