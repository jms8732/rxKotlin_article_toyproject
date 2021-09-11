package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.load.engine.Resource
import com.example.myapplication.R
import com.example.myapplication.ui.adapter.BindingViewAdapter
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BindingFragment<T : ViewDataBinding> : Fragment() {
    val disposable by lazy {
        CompositeDisposable()
    }

    var loading: LottieAnimationView? = null

    val observer = object : Observer<Any> {
        override fun onSubscribe(d: Disposable) {
            disposable.add(d)
            loading?.run {
                setAnimation("8707_loading.json")
                playAnimation()
            }
        }

        override fun onNext(t: Any) {
            onResult(t)
        }

        override fun onError(e: Throwable) {
            loading?.run {
                setAnimation("40444_paul_r_bear_fail.json")
                playAnimation()
            }
            Logger.e(e.toString())
        }

        override fun onComplete() {

        }
    }


    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun onResult(t: Any): Unit

    private var _binding: T? = null

    protected val binding: T
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<T>(inflater, getLayout(), container, false).apply {
            _binding = this
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }


    fun removeTag(s : String?) = s?.run{
        val start = indexOf("<")
        val end = indexOf(">")

        if (start != -1 && end != -1)
            removeRange(start, end + 1)
        else
            this
    }


    //TODO: 추후 html 특수 문자 추가
    fun refineString(s: String?) = s?.replace("&#39;", "'")
        ?.replace("&#09;", " ")
        ?.replace("&#10;", " ")
        ?.replace("&#32;", " ")
        ?.replace("&#33;", "!")
        ?.replace("&#34;", "\"")
        ?.replace("&#35;", "#")
        ?.replace("&#160;", " ")
        ?.replace("&lt;", "<")
        ?.replace("&gt;", ">")
        ?.replace("&amp;", "&")
        ?.replace("&lsquo;", "‘")
        ?.replace("&rsquo;", "’")
        ?.replace("&quot", "\"")
        ?.replace("&ldquo;", "“")
        ?.replace("&rdquo;", "”")
        ?.replace("&deg;", "°")
        ?.replace("&middot;", "·")
        ?.replace("&times;", "×")
        ?.replace("&divie;", "÷")
        ?.replace("&minus;", "?")
        ?.replace("&sim;","∼")
        ?.replace("&infin;","∞")
        ?.replace("nbsp;"," ")
        ?.replace("amp;","&")
        ?.replace("quot;","\"")
        ?.replace("apos","'")
        ?.replace("lt;","<")
        ?.replace("gt;",">")
        ?.replace("&nbsp;"," ")
        ?.replace("&amp;","&")
        ?.replace("&quot;","\"")
        ?.replace("&apos","'")
        ?.replace("&lt;","<")
        ?.replace("&gt;",">")
}