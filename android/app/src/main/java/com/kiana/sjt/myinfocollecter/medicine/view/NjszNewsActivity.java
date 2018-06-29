package com.kiana.sjt.myinfocollecter.medicine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baoyz.widget.PullRefreshLayout;
import com.kiana.sjt.fluttermyinfo.R;
import com.kiana.sjt.myinfocollecter.MainActivity;
import com.kiana.sjt.myinfocollecter.medicine.model.NjszrlModel;
import com.kiana.sjt.myinfocollecter.medicine.vmodel.NjszNewsVModel;
import com.kiana.sjt.myinfocollecter.utils.webview.ContentWebView;

import java.util.List;

/**
 * Created by taodi on 2018/4/24.
 */

public class NjszNewsActivity extends MainActivity {

    public PullRefreshLayout pullRefreshLayout;

    public RecyclerView recyclerView;

    public NjszNewsAdapter adapter;

    public NjszNewsVModel vModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setDefaultToolbar("南京市中");
        initView();
        initAfter();
    }

    public void initView() {
        pullRefreshLayout = (PullRefreshLayout) findViewById(R.id.pullview);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void initAfter() {
        vModel = new NjszNewsVModel(this);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                vModel.questData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 设置内容
     * @param dataList
     */
    public void setRecyclerViewData(final List<NjszrlModel.Newslist> dataList) {
        adapter = new NjszNewsAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new NjszNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(NjszNewsActivity.this, ContentWebView.class);
                intent.putExtra(ContentWebView.PARAM_URL, dataList.get(position).getUrl());
                intent.putExtra(ContentWebView.PARAM_TITLE, dataList.get(position).getTitle());
                startActivity(intent);
            }
        });
        pullRefreshLayout.setRefreshing(false);
    }
}
