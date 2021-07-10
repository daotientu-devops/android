package com.example.helloworld.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment" +
                "Organize resource in Android Studio" +
                "1) anim: XML files that define property animations. They are saved in res/anim/ folder and accessed from the R.anim class" +
                "2) color: XML files that define a state list of colors. They are saved in res/color/ and accessed from the R.color class" +
                "3) drawable: image files like .png, .jpg, .gif or XML files that are compiled into bitmaps, state lists, shapes, animation drawable. They are saved in res/drawable/ and accessed from the R.drawable class" +
                "4) layout: XML files that define a user interface layout. They are saved in res/layout/ and accessed from the R.layout class" +
                "5) menu: XML files that define application menus, such as an option menu, context menu or sub menu" +
                "6) raw: arbitrary files (tệp tùy ý) to save in their raw form. You need to call Resources.openRawResource() with the resource ID, which is R.raw.filename to open such raw files" +
                "7) values: arrays, integers, bools, colors, dimens, strings, styles.xml" +
                "8) xml: arbitrary XML files that can be read at runtime by calling Resources.getXML(). You can save various configuration files here which will be used at run time");
    }

    public LiveData<String> getText() {
        return mText;
    }
}