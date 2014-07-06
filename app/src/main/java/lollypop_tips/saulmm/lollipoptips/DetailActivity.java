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

        ImageView placeHolderImage = (ImageView) findViewById(R.id.detail_iv_user_photo);
        placeHolderImage.setViewName("photo"+getIntent().getIntExtra("position",0));
        placeHolderImage.setImageBitmap (bitmap);

        if (bitmap != null) {
            // With palette you can get the main colors of the give bitmap
            Palette palette = Palette.generate(bitmap);
            setUITextAndColor(palette, selectedUser);
        }
    }

    public void setUITextAndColor(Palette palette, User selectedUser) {
        List<PaletteItem> availablePaleteItems = palette.getPallete();
        PaletteItem lightVibrant = palette.getLightVibrantColor();
        PaletteItem lightMuted = palette.getLightMutedColor();
        int pSize = availablePaleteItems.size();

        getWindow().setBackgroundDrawable(new ColorDrawable(palette.getDarkVibrantColor().getRgb()));

        TextView nameTV = (TextView) findViewById(R.id.detail_tv_name);
        TextView emailTV = (TextView) findViewById(R.id.detail_tv_mail);
        TextView usernameTitleTv = (TextView) findViewById(R.id.detail_tv_nickname_title);
        TextView phoneTitleTv = (TextView) findViewById(R.id.detail_tv_phone_title);
        TextView biographyTitleTv = (TextView) findViewById(R.id.android_tv_biography_title);

        nameTV.setText(selectedUser.getName().getFirst() + " " + selectedUser.getName().getLast());
        emailTV.setText(selectedUser.getEmail());

        ((TextView) findViewById(R.id.detail_tv_phone)).setText(selectedUser.getPhone());
        ((TextView) findViewById(R.id.nickname)).setText(selectedUser.getUsername());

        if (lightVibrant != null)
            nameTV.setTextColor(lightVibrant.getRgb());

        if (lightMuted != null)
            emailTV.setTextColor(lightMuted.getRgb());

        usernameTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());
        phoneTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());
        biographyTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());
    }
}
