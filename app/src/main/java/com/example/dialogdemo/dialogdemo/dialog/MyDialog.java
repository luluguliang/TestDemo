package com.example.dialogdemo.dialogdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogdemo.dialogdemo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class MyDialog extends Dialog implements View.OnClickListener {
    private TextView  priceTv;
    private  ImageView goodsIv;
    private TextView  nameTv;
    private TextView addNumTv;

    public MyDialog(Context context) {
        this(context, R.style.ProgressHUD);
        setContentView(R.layout.dialog_add_to_cart);
        getWindow().getAttributes().gravity= Gravity.BOTTOM;
        WindowManager m =getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.dimAmount=0.2f;
        lp.width = d.getWidth();
        getWindow().setAttributes(lp);
        Log.d("lulu","myDialog--------------");
        goodsIv = (ImageView)findViewById(R.id.id_drug_icon);
        priceTv = (TextView) findViewById(R.id.id_price);
        nameTv = (TextView)findViewById(R.id.id_name);
        addNumTv = (TextView)findViewById(R.id.id_buy_num);
        if(!TextUtils.isEmpty(goodsImage)){
            Picasso.with(getContext()).load(goodsImage).error(R.mipmap.ic_launcher).into(goodsIv);
        }
        findViewById(R.id.id_jia).setOnClickListener(this);
        findViewById(R.id.id_jian).setOnClickListener(this);
        findViewById(R.id.id_buy_ok).setOnClickListener(this);
        findViewById(R.id.id_close_dialog).setOnClickListener(this);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lulu","onCreate--------------");

    }
    public void setData(String goodsName, String goodsPrice, String goodsIcon){
        Log.d("lulu","setData--------------");
       // MyDialog dialog = new MyDialog(context,R.style.ProgressHUD);
        /*View dialogView = getLayoutInflater().inflate(
                R.layout.dialog_add_to_cart, null);*/
        /*goodsIv = (ImageView) dialogView.findViewById(R.id.id_drug_icon);
        priceTv = (TextView) dialogView.findViewById(R.id.id_price);
        nameTv = (TextView) dialogView.findViewById(R.id.id_name);
        addNumTv = (TextView) dialogView.findViewById(R.id.id_buy_num);*/
        /*if(!TextUtils.isEmpty(goodsImage)){
            Picasso.with(getContext()).load(goodsImage).error(R.mipmap.ic_launcher).into(goodsIv);
        }
        priceTv.setText(goodsPrice+"");
        nameTv.setText(goodsName);*/
        /*dialogView.findViewById(R.id.id_jia).setOnClickListener(this);
        dialogView.findViewById(R.id.id_jian).setOnClickListener(this);
        dialogView.findViewById(R.id.id_buy_ok).setOnClickListener(this);
        dialogView.findViewById(R.id.id_close_dialog).setOnClickListener(this);*/
    }
    private String goodsName;
    private String goodsImage;
    private String price;
    private int buyNum = 1;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_jia:
                buyNum++;
                addNumTv.setText(buyNum+"");
                break;
            case R.id.id_jian:
                if(buyNum<=1){
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                    return;
                }
                buyNum--;
                addNumTv.setText(buyNum+"");
                break;
            case R.id.id_buy_ok:
                /*if(selectGoodsOKListener != null){
                    selectGoodsOKListener.selectGoodsOk(buyNum);
                }*/
                dismiss();
                break;
            case R.id.id_close_dialog:
                //  case R.id.id_view_out:
                dismiss();
                break;
        }
    }
}
