package com.woyl.lt_woyl.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.woyl.lt_woyl.R;

import java.util.Objects;

public abstract class BaseDialogFragment extends DialogFragment {
    private boolean isWidth ;
    private int ori ;
    protected View view;
    protected LayoutInflater inflater;


    public BaseDialogFragment(boolean isWidth, int ori) {
        this.isWidth = isWidth;
        this.ori = ori;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(this.getDialog()).requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.inflater = inflater;
        initViews();
        return view;
    }

    @Override
    public void onResume() {
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        if (window != null) {
            window.setGravity(ori);
            ViewGroup.LayoutParams layoutParams= window.getAttributes();
            if (isWidth){
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int width = dm.widthPixels;
                layoutParams.width = (int) (width*0.8);
            }
            window.setWindowAnimations(R.style.popmenu_animation);
            setStyle(DialogFragment.STYLE_NORMAL, R.style.dialogStyle);
        }
        super.onResume();
    }

    @Nullable
    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Objects.requireNonNull(
                Objects.requireNonNull(getDialog()).getWindow()).setLayout(displayMetrics.widthPixels,
                Objects.requireNonNull(getDialog().getWindow()).getAttributes().height);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
    }

    protected abstract void initViews();
}
