package zyc.recyclerviewtheme;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        mRecyclerView.setAdapter(new DemoAdapter(true, Arrays.asList("a", "b", "c")));
    }

    @OnClick({R.id.rb_theme_on, R.id.rb_theme_off})
    public void onClick(View view) {
        boolean setTheme;
        switch (view.getId()) {
            case R.id.rb_theme_on:
                setTheme = true;
                break;
            case R.id.rb_theme_off:
                setTheme = false;
                break;
            default:
                return;
        }
        mRecyclerView.setAdapter(new DemoAdapter(setTheme, Arrays.asList("a", "b", "c")));
    }

    public static class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {
        private List<String> mInfos;
        private boolean mSetTheme;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.tv_item)
            TextView tvItem;

            public ViewHolder(View v) {
                super(v);
                ButterKnife.bind(this, v);
            }
        }

        public DemoAdapter(boolean setTheme, List<String> data) {
            mSetTheme = setTheme;
            setData(data);
        }

        public void setData(List<String> data) {
            mInfos = data;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            Context context = parent.getContext();
            Context context = parent.getContext().getApplicationContext();
            return new ViewHolder(LayoutInflater.from(context)
                    .inflate(mSetTheme ? R.layout.list_item : R.layout.list_item_theme_off, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.tvItem.setText(mInfos.get(position));
        }

        @Override
        public int getItemCount() {
            return mInfos.size();
        }
    }
}
