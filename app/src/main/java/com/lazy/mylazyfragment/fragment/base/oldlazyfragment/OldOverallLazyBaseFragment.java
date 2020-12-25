package com.lazy.mylazyfragment.fragment.base.oldlazyfragment;

import android.util.Log;

import com.lazy.mylazyfragment.fragment.base.LogFragment;

/**
 * author : xu
 * date : 2020/12/25 17:42
 * description :
 * 1既支持 add  show  hide   旧的懒加载
 * 2 也能支持  vp+ FragmentPageAdapter
 * <p>
 * 他是  OldLazyFragment  + OldVpLazyFragment  合版  兼容    上述 1  2 两种情况  的 传统的 懒加载方式
 */
public abstract class OldOverallLazyBaseFragment extends LogFragment {
    /**
     * 是否执行懒加载
     */
    private boolean isLoaded = false;

    /**
     * 当前Fragment是否对用户可见    vp+fragmenPageAdapter  方案用
     */
    private boolean isVisibleToUser = false;

    /**
     * 当使用ViewPager+Fragment形式会调用该方法时，setUserVisibleHint会优先Fragment生命周期函数调用，
     * 所以这个时候就,会导致在setUserVisibleHint方法执行时就执行了懒加载，
     * 而不是在onResume方法实际调用的时候执行懒加载。所以需要这个变量
     */
    private boolean isCallResume = false;

    /**
     * 是否调用了setUserVisibleHint方法。处理show+add+hide模式下，默认可见 Fragment 不调用
     * onHiddenChanged 方法，进而不执行懒加载方法的问题。
     */
    private boolean isCallUserVisibleHint = false;


    @Override
    public void onResume() {
        super.onResume();
        isCallResume = true;
        if (!isCallUserVisibleHint) {
            // 如果没走  UserVisibleHint  应是  add + show+ hide  方案控制fragment的
            // 此时 fragment 是否可见 取决于onHiddenChanged的返回值isHidden（）
            isVisibleToUser = !isHidden();
        }
        judgeLazyInit();
    }

    /**
     * 判断是否可执行懒加载 初始化
     * 1 没初始化过
     * 2 对用户可见
     * 3 调用过 resume
     */
    private void judgeLazyInit() {
        if (!isLoaded && isVisibleToUser && isCallResume) {
            lazyInit();
            Log.d(TAG, "lazyInit:!!!!!!!----" + this.getClass().getSimpleName());
            isLoaded = true;
        }
    }

    /**
     * 只有 在 add hide  show  方案 控制fragment 时  才会回调
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // fragment  是否可见
        isVisibleToUser = !hidden;
        judgeLazyInit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded = false;
        isVisibleToUser = false;
        isCallUserVisibleHint = false;
        isCallResume = false;
    }

    /**
     * 只有在 vp+  adapter  情况下才回调
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 控制vp  模式下  fragment 是否可见
        this.isVisibleToUser = isVisibleToUser;
        isCallUserVisibleHint = true;
        judgeLazyInit();
    }

    /**
     * 业务模块   实际执行懒加载的 业务 网络请求等
     */
    public abstract void lazyInit();
}
