package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.NEWS_TYPE
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEtBinding
import com.example.myapplication.databinding.FragmentKoreaHeraldBinding
import com.example.myapplication.model.EtnewsRss
import com.example.myapplication.model.KoreaHeraldItem
import com.example.myapplication.model.KoreaHeraldRss
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.adapter.EtnewsAdapter
import com.example.myapplication.ui.adapter.KoreaHeraldAdapter
import com.example.myapplication.utils.withThread
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class KoreaHeraldFragment : BindingFragment<FragmentKoreaHeraldBinding>(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.fragment_korea_herald
    private val vm by sharedViewModel<MainActivityViewModel>()
    private lateinit var heraldAdapter: KoreaHeraldAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())

            heraldAdapter = KoreaHeraldAdapter(vm)
            adapter = heraldAdapter
        }

        binding.run {
            koreaHeraldAllStories.setOnClickListener(this@KoreaHeraldFragment)
            koreaHeraldBusiness.setOnClickListener(this@KoreaHeraldFragment)
            koreaHeraldEntertain.setOnClickListener(this@KoreaHeraldFragment)
            koreaHeraldFinance.setOnClickListener(this@KoreaHeraldFragment)
            koreaHeraldLife.setOnClickListener(this@KoreaHeraldFragment)
            koreaHeraldNational.setOnClickListener(this@KoreaHeraldFragment)
            koreaHeraldSports.setOnClickListener(this@KoreaHeraldFragment)
            koreaHeraldWorld.setOnClickListener(this@KoreaHeraldFragment)

            koreaHeraldAllStories.performClick()
        }

        loading = binding.lottieLoading
    }

    @SuppressLint("CheckResult")
    override fun onResult(t: Any) {
        Observable.just(t)
            .cast(KoreaHeraldRss::class.java)
            .subscribe({
                it.channel?.run {
                    item?.asSequence()
                        ?.map {
                            it.description = refineString(it.description)
                        }?.toList()

                    heraldAdapter.submitList(item){
                        binding.lottieLoading.visibility = View.GONE
                    }
                }
            }, {
                it.printStackTrace()
            }, {

            })
    }

    override fun onClick(v: View?) {
        heraldAdapter.submitList(null) {
            binding.lottieLoading.visibility = View.VISIBLE
            v?.run {
                vm.fetchHeraldNews(id)
                    ?.withThread()
                    ?.subscribe(observer)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(type: String) = KoreaHeraldFragment().apply {
            val bundle = Bundle().apply {
                putString(NEWS_TYPE, type)
            }
            arguments = bundle
        }
    }
}