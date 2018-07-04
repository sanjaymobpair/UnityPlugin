package com.tv.unitplug;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;


/**
 * This class echoes a string called from JavaScript.
 */
public class UnitInit extends CordovaPlugin implements IUnityAdsListener {
    String TAG = UnitInit.class.getName();

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("__setUpAd")) {
            Log.d(TAG, "hello" + args.getString(0));
            String gameId = args.getString(0);
            UnityAds.initialize(cordova.getActivity(), gameId, this);
            __ShowAds();
            return true;
        }
        return false;
    }

    public void __ShowAds() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, " : inSide");
                if (UnityAds.isReady()) {
                    Log.d(TAG, " : isReady");
                    UnityAds.show(cordova.getActivity());
                }
            }
        }, 10000);
    }

    @Override
    public void onUnityAdsReady(String placementId) {
    }

    @Override
    public void onUnityAdsStart(String placementId) {

    }

    @Override
    public void onUnityAdsFinish(String placementId, UnityAds.FinishState result) {

    }

    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {

    }
}