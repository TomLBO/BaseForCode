package com.bob.baseforcode.multiType;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bob.baseforcode.R;

import java.util.ArrayList;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by bob
 * on 2018/7/4.
 */
public class MultiTypeActivity extends AppCompatActivity {
    private static final String TAG = "MultiTypeActivity";
    RecyclerView mRecyclerView;
    private Items mItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);

        mRecyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return (mItems.get(position) instanceof Category) ? 4 : 1;
////                return 2;
//            }
//        });
        mRecyclerView.setLayoutManager(layoutManager);



        MultiTypeAdapter adapter = new MultiTypeAdapter();

        adapter.register(Category.class, new CategoryViewBinder());
        adapter.register(AppInfo.class, new AppViewBinder());
        adapter.register(AppList.class, new AppsViewBinder());

        mRecyclerView.setAdapter(adapter);

        mItems = getData(20);

        adapter.setItems(mItems);
        adapter.notifyDataSetChanged();

    }

    private Items getData(int count) {
        Items items = new Items();

        items.add(new Category("办公"));

        ArrayList<AppInfo> infos = new ArrayList<>();
        infos.add(new AppInfo("电子公文"));
        infos.add(new AppInfo("集中报销"));
        infos.add(new AppInfo("合同管理"));
        items.add(new AppList(infos));

//        items.add(new AppInfo("电子公文"));
//        items.add(new AppInfo("集中报销"));
//        items.add(new AppInfo("合同管理"));


        for (int i = 0; i < count; i++) {
            items.add(new Category("category" + i));
//            items.add(new AppInfo("app" + i));
//            items.add(new AppInfo("app" + i * i));
//            items.add(new AppInfo("app" + i * i * i));
//            items.add(new AppInfo("app" + i * i * i));
//            items.add(new AppInfo("app" + i * i * i));
//            items.add(new AppInfo("app" + i * i * i));

            ArrayList<AppInfo> apps = new ArrayList<>();
            apps.add(new AppInfo("app" + i));
            apps.add(new AppInfo("app" + i * i));
            apps.add(new AppInfo("app" + i * i * i));
            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
//            apps.add(new AppInfo("app" + i * i * i));
            apps.add(new AppInfo("app" + i * i * i));
            items.add(new AppList(apps));
        }
        return items;
    }
}
