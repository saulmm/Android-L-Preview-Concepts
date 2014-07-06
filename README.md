Android L preview example
=========================

### Description

This project is focused on the sample using the API's new preview version of Android-L, use of transitions, shadows etc...,

![Alt text](https://googledrive.com/host/0B62SZ3WRM2R2VHJFNk1JOWNCbHM)


### UI

This app is focused to use the new Material Design released at the Google I/0 14, to use the `Material` theme on your app, you only 
have to set the parent of your app style to `@android:style/Theme.Material` or `@android:style/Theme.Material.Light` and `@android:style/Theme.Material.DarkActionBar`.

#### Customize the Color Palette of your app

In the `theme.xml` the `MainActivity` them looks like:

```xml
    <!-- App colors -->
    <color name="lollipop_theme_default_primary">#3F51B5</color>
    <color name="lollipop_theme_default_primary_dark">#303F9F</color>
    <color name="lollipop_theme_default_accent">#C5CAE9</color>
    <color name="fab_color">#FF4081</color>


    <!-- Main theme colors -->
    <style name="MainLollipopTheme" parent="android:Theme.Material.Light">
        <item name="android:colorPrimary">@color/lollipop_theme_default_primary</item>
        <item name="android:colorPrimaryDark">@color/lollipop_theme_default_primary</item>
        <item name="android:statusBarColor">@color/lollipop_theme_default_primary_dark</item>
        <item name="android:colorAccent">@color/lollipop_theme_default_accent</item>
        <item name="android:colorControlHighlight">@color/lollipop_theme_default_accent</item>
        <item name="android:navigationBarColor">@color/lollipop_theme_default_primary</item>

        <!-- Main theme action bar-->
        <item name="android:actionBarStyle">@style/MainActionBar</item>
        <item name="android:actionBarTheme">@style/MainActionBarThemeOverlay</item>


        <!-- lag indicating whether this window requests that content changes be performed
             as scene changes with transitions -->
        <item name="android:windowContentTransitions">true</item>

        <!-- Flag indicating whether this Window's transition should overlap with
             the exiting transition of the calling Activity. -->
        <item name="android:windowAllowEnterTransitionOverlap">true</item>

        <!--Flag indicating whether this Window's transition should overlap with
             the exiting transition of the called Activity when the called Activity
             finishes. -->
        <item name="android:windowAllowExitTransitionOverlap">true</item>

        <!-- Flag indicating whether this window's Action Bar should overlay -->
        <item name="android:windowActionBarOverlay">false</item>

        <!-- Flag indicating whether this window's Action Bar content should overlay -->
        <item name="android:windowContentOverlay">@null</item>
    </style>
```

[Customizing the material theme](https://camo.githubusercontent.com/898d8cbb7bd0a7b0b06d9fde7f60296fd55ecada/687474703a2f2f646576656c6f7065722e616e64726f69642e636f6d2f707265766965772f6d6174657269616c2f696d616765732f5468656d65436f6c6f72732e706e67) <- 
This easy picture on [Android Developers](http://developer.android.com/preview/material/theme.html/) explains where are the attributes that of the previous code.

### The FAB Button

Android-L introduces a new way to manage view with elevation and shadows, _"Outlines represent the outer shape of a graphics object and define the ripple area for touch feedback."_ [Android Developers](https://developer.android.com/preview/material/views-shadows.html), the `fab` button is defined as a `ImageButton` in the xml:

```xml
...
    <!-- fab button -->
    <ImageButton
        android:id="@+id/fab"
        android:layout_alignParentRight="true"
        android:layout_alignParentBottom="true"
        android:layout_width="@dimen/fab_size"
        android:layout_height="@dimen/fab_size"
        android:layout_gravity="bottom|right"
        android:layout_marginRight="16dp"
        android:layout_marginBottom="16dp"
        android:background="@drawable/fab_ripple"
        android:elevation="@dimen/button_elevation"
        android:onClick="onFabClicked"
        android:src="@drawable/ic_action_add"/>
...
```

```java
        // Configure the FAB button
        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline outline = new Outline();
        outline.setOval(0, 0, size, size);
        findViewById(R.id.fab).setOutline(outline);
```

### Activity transitions

Now, with Android-L you can specify custom animations for enter and exit transtioins and for transition of shared elemenets between activities, to do that, first, you have to enable the window content transitions, in my case, at `themes.xml`


```xml
    <style name="MainLollipopTheme" parent="android:Theme.Material.Light">
        ...
        <!-- lag indicating whether this window requests that content changes be performed
       as scene changes with transitions -->
        <item name="android:windowContentTransitions">true</item>

        <!-- Flag indicating whether this Window's transition should overlap with
             the exiting transition of the calling Activity. -->
        <item name="android:windowAllowEnterTransitionOverlap">true</item>

        <!--Flag indicating whether this Window's transition should overlap with
             the exiting transition of the called Activity when the called Activity
             finishes. -->
        <item name="android:windowAllowExitTransitionOverlap">true</item>

        <!-- Flag indicating whether this window's Action Bar should overlay -->
        <item name="android:windowActionBarOverlay">false</item>

        <!-- Flag indicating whether this window's Action Bar content should overlay -->
        <item name="android:windowContentOverlay">@null</item>
        ...
    </style>
    
```

```java
        // Setup the transition to the detail activity
        ActivityOptions options =  ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view, "photo" + i);
        startActivity(detailIntent, options.toBundle());
```


### Tools & References:

- [Android Studio](https://developer.android.com/sdk/installing/studio.html)
- [Romain Gui - Google I/O 2014 app] (http://www.curious-creature.org/2014/06/26/google-io-2014-slides-and-demo/)
- [Gradle Please](http://gradleplease.appspot.com/)
- [Random User generator api] (http://randomuser.me/)
- [Gson] (https://code.google.com/p/google-gson/)
- [Gabriel Mariotti] (https://plus.google.com/u/0/+GabrieleMariotti/posts)

