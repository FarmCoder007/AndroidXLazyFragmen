package com.lazy.mylazyfragment.fragment.base;

import android.util.Log;

/**
 * author : xu
 * date : 2020/12/25 13:54
 * description :  Androidx 下支持栏加载的fragment
 */
public abstract class AndroidXLazyFragment extends LogFragment {


    /**
     *  ************************  以下是针对  add  hide  show 实现懒加载*****************
     */
    private boolean isLoaded = false;

    /**
     *  isHidden 此值来自  onHiddenChanged   true  则该fragment 隐藏  false 则为显示
     */
    @Override
    public void onResume() {
        super.onResume();
        // 未初始化过 并且显示
        if (!isLoaded && !isHidden()) {
            isLoaded = true;
            Log.d(TAG, "lazyInit:!!!!!!!" + this.getClass().getSimpleName());
            lazyInit();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded = false;
    }

    /**
     * 需要懒加载初始化的内容 放在此方法    只有首次初始化 才会执行
     */
    public abstract void lazyInit();
}
