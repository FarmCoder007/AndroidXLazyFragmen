package com.lazy.mylazyfragment.fragment.base.oldlazyfragment;

import android.util.Log;

import com.lazy.mylazyfragment.fragment.base.LogFragment;

/**
 * author : xu
 * date : 2020/12/25 14:49
 * description : vp+fragmentPageAdapter  旧懒加载方案
 * <p>
 * <p>
 * 当使用ViewPager+Fragment形式会调用该方法时，setUserVisibleHint会优先Fragment生命周期函数调用，
 * 所以这个时候就,会导致在setUserVisibleHint方法执行时就执行了懒加载，
 * 而不是在onResume方法实际调用的时候执行懒加载。所以需要这个变量
 */
public abstract class OldVpLazyFragment extends LogFragment {

    //控制是否执行懒加载
    private boolean isLoaded = false;

    /**
     * 当使用ViewPager+Fragment形式会调用该方法时，setUserVisibleHint会优先Fragment生命周期函数调用，
     * 所以这个时候就,会导致在setUserVisibleHint方法执行时就执行了懒加载，
     * 而不是在onResume方法实际调用的时候执行懒加载。所以需要这个变量
     */
    private boolean isCallResume = false;

    @Override
    public void onResume() {
        super.onResume();
        // 用于首次 显示的那个  fragment 初始化
        isCallResume = true;
        judgeLazyInit();
    }

    /**
     * 判断是否可以执行懒加载方法
     */
    private void judgeLazyInit() {
        if (!isLoaded && getUserVisibleHint() && isCallResume) {
            lazyInit();
            Log.d(TAG, "lazyInit:!!!!!!!" + this.getClass().getSimpleName());
            isLoaded = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded = false;
        isCallResume = false;
    }

    /**
     * 懒加载方法
     */
    public abstract void lazyInit();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 用于切换其他fragment  时  检测是否初始化过
        judgeLazyInit();
    }
}
