package com.kiana.sjt.myinfocollecter.home.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.kiana.sjt.fluttermyinfo.R;
import com.kiana.sjt.myinfocollecter.Constants;
import com.kiana.sjt.myinfocollecter.MainActivity;
import com.kiana.sjt.myinfocollecter.home.vmodel.WelcomeVModel;
import com.kiana.sjt.myinfocollecter.utils.PropertiesUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 欢迎页
 * Created by taodi on 2018/4/23.
 */

public class WelcomeAcivity extends MainActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setNoTitle();
        requestPermissions();
    }

    /**
     * 获取权限
     */
    private void requestPermissions() {
        PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.PHONE, PermissionConstants.CAMERA)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {

                    }


                }).callback(new PermissionUtils.FullCallback() {
            @Override
            public void onGranted(List<String> permissionsGranted) {
                initConstants();
                new WelcomeVModel(WelcomeAcivity.this, WelcomeAcivity.this);
            }

            @Override
            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                finish();
            }
        }).request();
    }

    /**
     * 拿到权限后初始化全局变量
     */
    private void initConstants() {
        Constants.serverUrl = PropertiesUtil.getAppConfigValue(this, "serverUrl");
        Constants.zentaoUrl = PropertiesUtil.getAppConfigValue(this, "zentaoUrl");
        Constants.serverImgUrl = Constants.serverUrl + "/images/";
    }

    /**
     * 设置背景图片
     * @param url
     */
    public void setBackImage(String url) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, (ImageView) findViewById(R.id.iv_bg), getImageLoaderOptionForWelcome());
    }

    /**
     * 欢迎页加载图片的配置项
     * @return
     */
    public static DisplayImageOptions getImageLoaderOptionForWelcome() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading_default)
                .showImageForEmptyUri(R.drawable.img_loading_default)
                .showImageOnFail(R.drawable.img_loading_default)
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .build();
        return options;
    }
}
