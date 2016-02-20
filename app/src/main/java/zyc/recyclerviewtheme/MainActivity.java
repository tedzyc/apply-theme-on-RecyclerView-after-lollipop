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
        mRecyclerView.setAdapter(new DemoAdapter(this, true, Arrays.asList("a", "b", "c")));
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
        mRecyclerView.setAdapter(new DemoAdapter(this, setTheme, Arrays.asList("a", "b", "c")));
    }

    public static class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {
        private List<String> mInfos;
        private Context mContext;
        private boolean mSetTheme;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.tv_item)
            TextView tvItem;

            public ViewHolder(View v) {
                super(v);
                ButterKnife.bind(this, v);
            }
        }

        public DemoAdapter(Context context, boolean setTheme, List<String> data) {
            mContext = context.getApplicationContext();
            mSetTheme = setTheme;
            setData(data);
        }

        public void setData(List<String> data) {
            mInfos = data;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext)
                    .inflate(mSetTheme ? R.layout.list_item : R.layout.list_item_theme_off, viewGroup, false));
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
