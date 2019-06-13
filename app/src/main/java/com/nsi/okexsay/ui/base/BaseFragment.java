package com.nsi.okexsay.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.wiget.LoadingDialog;

public class BaseFragment extends Fragment {
    private LoadingDialog dialog=null;
    public static int DEFAULT_PAGE_SIZE = 10;
    protected FragmentManager fragmentManager;
    public AppContext appContext;
    protected void showShortToast(int pResId) {
        showShortToast(getString(pResId));
    }

    protected void showLongToast(String pMsg) {
        Toast.makeText(getActivity(), pMsg, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String pMsg) {
        Toast.makeText(getActivity(), pMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (dialog==null){
            dialog = new LoadingDialog(getContext());
        }
        appContext = (AppContext)getActivity().getApplication();
        appContext.addActivity(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        hideKeyboard(getView());
        super.onPause();
    }

    @Override
    public void onResume() {
        hideKeyboard(getView());
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void openFragment(int ContentId, Fragment newFragment) {

        fragmentManager.beginTransaction().replace(ContentId, newFragment).commit();
    }

    protected void hideKeyboard(View view) {
        if (getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }
    public void Shoudialog() {
        dialog.show();
    }

    public void DissDialog() {
        dialog.dismiss();
    }
}
