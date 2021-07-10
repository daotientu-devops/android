package com.example.helloworld.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("" +
                "1) java folder: it includes an MainActivity.java source file having an activity class that runs whwn your app is launched using the app icon" +
                "2) res/drawalbe-hdpi: this is a directory for drawable objects that are deaigned for high-density screens" +
                "3) res/layout: this is a directory for files that define your app's user interface" +
                "4) res/values: this is a directory for other various XML files that contain a collection of resources, such as strings and colors definitions" +
                "5) AndroidManifest.xml: this is the manifest file which describes the fundamential characteristics of the app and defines each of its components" +
                "6) build.gradle: this is an auto generated file which contains compileSdkVersion, buildToolsVersion, applicationId, minSdkVersion, targetSdkVersion, versionCode and versionName");
    }

    public LiveData<String> getText() {
        return mText;
    }
}