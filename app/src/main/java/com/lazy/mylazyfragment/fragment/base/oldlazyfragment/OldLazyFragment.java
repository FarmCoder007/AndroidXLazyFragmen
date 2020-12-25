package com.lazy.mylazyfragment.fragment.base.oldlazyfragment;

import android.util.Log;

import com.lazy.mylazyfragment.fragment.base.LogFragment;

/**
 * author : xu
 * date : 2020/12/25 14:15
 * description : add  show  hide  旧懒加载方案 专用
 *
 * 1 由于 首次add所有fragment 时 由activity3 可知  会把所有的fragment 生命周期 走到  onresume
 * 2 而切换的时候 不走onresume  而是走onHiddenChanged  控制显示隐藏 估 旧懒加载方案如下
 * <p>
 * 弊端： 首次会走未显示的fragment 的无用生命周期   虽然不初始化什么内容
 */
public abstract class OldLazyFragment extends LogFragment {
    //控制是否执行懒加载
    private boolean isLoaded = false;

    @Override
    public void onResume() {
        super.onResume();
        // 用于首次 显示的那个  fragment 初始化
        judgeLazyInit();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // 用于切换其他fragment  时  检测是否初始化过
        judgeLazyInit();
    }

    /**
     * 判断是否可以执行懒加载方法
     */
    private void judgeLazyInit() {
        if (!isLoaded && !isHidden()) {
            lazyInit();
            Log.d(TAG, "lazyInit:!!!!!!!" + this.getClass().getSimpleName());
            isLoaded = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded = false;
    }

    /**
     * 懒加载方法
     */
    public abstract void lazyInit();
}
