package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BindingFragment<T:ViewDataBinding> : Fragment(){
    val disposable by lazy{
        CompositeDisposable()
    }

    @LayoutRes
    abstract fun getLayout() : Int
    private var _binding : T? = null

    protected val binding : T
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<T>(inflater,getLayout(),container,false).apply{
            _binding = this
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }
}