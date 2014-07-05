package lollypop_tips.saulmm.lollipoptips;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.PaletteItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lollypop_tips.saulmm.lollipoptips.entities.User;

public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        getActionBar().hide();

        User selectedUser = (User) getIntent().getSerializableExtra("user");

        int bitMapResourcePath = getIntent().getIntExtra("photo", R.drawable.photo1);
        Bitmap bitmap = MainActivity.photoCache.get(bitMapResourcePath);

        ImageView placeHolderImage = (ImageView) findViewById(R.id.photo);
        placeHolderImage.setViewName("photo"+getIntent().getIntExtra("position",0));
        placeHolderImage.setImageBitmap (bitmap);

        if (bitmap != null) {
            Palette palette = Palette.generate(bitmap);
            setUITextAndColor(palette, selectedUser);
        }
    }

    public void setUITextAndColor(Palette palette, User selectedUser) {
        List<PaletteItem> availablePaleteItems = palette.getPallete();
        int pSize = availablePaleteItems.size();

        getWindow().setBackgroundDrawable(new ColorDrawable(palette.getDarkVibrantColor().getRgb()));

        TextView nameTV = (TextView) findViewById(R.id.name);
        nameTV.setText(selectedUser.getName().getFirst() + " " + selectedUser.getName().getLast());

        TextView emailTV = (TextView) findViewById(R.id.mail);
        emailTV.setText(selectedUser.getEmail());
        emailTV.setTextColor(availablePaleteItems.get(3 % pSize).getRgb());

        TextView usernameTitleTv = (TextView) findViewById(R.id.nickname_title);
        usernameTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());

        TextView phoneTitleTv = (TextView) findViewById(R.id.phone_title);
        phoneTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());

        TextView biographyTitleTv = (TextView) findViewById(R.id.biography_title);
        biographyTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());

        ((TextView) findViewById(R.id.phone)).setText(selectedUser.getPhone());
        ((TextView) findViewById(R.id.nickname)).setText(selectedUser.getUsername());
    }
}
