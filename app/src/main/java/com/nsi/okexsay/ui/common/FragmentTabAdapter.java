package com.nsi.okexsay.ui.common;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseFragment;

public class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener{
    private List<Fragment> fragments;
    private RadioGroup rgs;
    private FragmentActivity fragmentActivity;
    private int fragmentContentId;

    private int currentTab;

    private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener;

    public FragmentTabAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;

        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(0));
        ft.commit();

        rgs.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        for(int i = 0; i < rgs.getChildCount(); i++){
            if(rgs.getChildAt(i).getId() == checkedId){
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);

                getCurrentFragment().onPause();
               
                if(fragment.isAdded()){
                    fragment.onResume();
                }else{
 
                	ft.add(fragmentContentId, fragment);
 
                }
                showTab(i); // 显示目标tab
      
                ft.commit();

                if(null != onRgsExtraCheckedChangedListener){
                    onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(radioGroup, checkedId, i);
                }
                
                

            }
        }

    }

    private void showTab(int idx){
        for(int i = 0; i < fragments.size(); i++){
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);

            ft.hide(fragment);
            if(idx == i){
                ft.show(fragment);
            }else{
//                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx;
    }


    private FragmentTransaction obtainFragmentTransaction(int index){
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if(index > currentTab){
            ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
        }else{
            ft.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
        }
        return ft;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public BaseFragment getCurrentFragment(){
        return (BaseFragment)(fragments.get(currentTab));
    }

    public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
        return onRgsExtraCheckedChangedListener;
    }

    public void setOnRgsExtraCheckedChangedListener(OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
        this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
    }

    public static class OnRgsExtraCheckedChangedListener{
        public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index){
        	 
        }
    }

}