package lollypop_tips.saulmm.lollipoptips;
import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Outline;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lollypop_tips.saulmm.lollipoptips.entities.Result;
import lollypop_tips.saulmm.lollipoptips.entities.User;
import lollypop_tips.saulmm.lollipoptips.entities.UserEntities;
import lollypop_tips.saulmm.lollipoptips.tools.TextTools;


public class MainActivity extends Activity {
    public static SparseArray<Bitmap> photoCache = new SparseArray<Bitmap>(1);
    private List<Result> users;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline outline = new Outline();
        outline.setOval(0, 0, size, size);
        findViewById(R.id.fab).setOutline(outline);

        String jsonUsers = TextTools.getJSONString(R.raw.users, this);
        Gson gson = new Gson ();
        users = gson.fromJson(jsonUsers, UserEntities.class).getResults();


        GridView gridview = (GridView) findViewById(R.id.user_grid);
        gridview.setFocusable(false);

        gridview.setOnItemClickListener(userClickListener);
        gridview.setAdapter (new UserAdapter(this, users));
    }



    private AdapterView.OnItemClickListener userClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d("a", "hi");

            Intent detailIntent = new Intent();
            detailIntent.putExtra("user", users.get(i).getUser());
            detailIntent.setClass(MainActivity.this, DetailActivity.class);
            detailIntent.putExtra("position", i);

            ImageView userImage = (ImageView) view.findViewById(R.id.user_image);
            ((ViewGroup) userImage.getParent()).setTransitionGroup(false);

                photoCache.put(R.drawable.photo1,
                        ((BitmapDrawable) userImage.getDrawable()).getBitmap());

                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view, "photo" + i);
                startActivity(detailIntent, options.toBundle());

        }
    };

    public void onFabClicked(View view) {
        Toast.makeText(this, "Omg, you pressed that button...", Toast.LENGTH_LONG).show();;
    }
}


class UserAdapter extends BaseAdapter {
    private Context mContext;
    private List<Result> users;

    public UserAdapter(Context c, List<Result> users) {
        mContext = c;
        this.users = users;
    }

    class ViewHolder {
        TextView userTitle;
        ImageView userImage;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {

            // inflate the layout
            convertView = LayoutInflater.from(mContext).inflate(R.layout.user_card, null);

            // Set up the view holder
            viewHolder = new ViewHolder();
            viewHolder.userTitle = (TextView) convertView.findViewById(R.id.user_name);
            viewHolder.userImage = (ImageView) convertView.findViewById(R.id.user_image);

            // store the viewholder with the view
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User currentUser = users.get(position).getUser();
        viewHolder.userTitle.setText(currentUser.getName().getFirst());

        Picasso.with(mContext)
            .load(currentUser.getPicture())
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.userImage);

        convertView.setViewName("photo" + position);
        return convertView;
    }
}
