package com.tv.unitplug;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;


/**
 * This class echoes a string called from JavaScript.
 */
public class UnitInit extends CordovaPlugin implements IUnityAdsListener {
    private String TAG = UnitInit.class.getName();
    private SharedPreferences mPrefs;
    private static final String PREFERENCES = "settings";
    private String ADS_KEY = "ADSKEY";

    /**
     *
     * @param action          The action to execute.
     * @param args            The exec() arguments.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return
     * @throws JSONException
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Context context = this.cordova.getContext();
        mPrefs = context.getSharedPreferences(PREFERENCES, 0);

        /**
         * Call Method which equal to action coming from javascript
         */
        if (action.equals("__setUpAd")) {
            Log.d(TAG, "__setUpAd : " + args.getString(0));
            __InitAds(args);
            return true;
        } else if (action.equals("__showAds")) {
            Log.d(TAG, "__showAds called...");
            __ShowAds();
        }
        return false;
    }

    /**
     * this will called when call __setUpAd method will called
     * it will get gameId from argument and store in preference so, no need to store it every time
     * than after it will call initialize method of UnityAds Class
     * @param args value given by user in Jsonarray
     */
    private void __InitAds(JSONArray args) {
        String gameId = getAds_Key();
        if (gameId.equals("null")) {
            Log.d(TAG, " : IF");
            try {
                String gameIdArgs = args.getString(0);

                // TODO: 10/07/18 intialize unityads
                UnityAds.initialize(cordova.getActivity(), gameIdArgs, this);
                setAds_Key(gameIdArgs);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, " : ELSE");
            // TODO: 10/07/18 intialize unityads
            UnityAds.initialize(cordova.getActivity(), gameId, this);
        }
    }

    /**
     * it will called when __ShowAds method will called by user
     * it will check if unityads is ready than after it will show #show method of UnityAds
     */
    private void __ShowAds() {
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
        Log.d(TAG, " : onUnityAdsReady");
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

    // TODO: 10/07/18 store string preference
    private void putString(String name, String value) {
        mPrefs.edit().putString(name, value).apply();
    }

    // TODO: 10/07/18 setAds Key in preference
    private void setAds_Key(String key) {
        putString(ADS_KEY, key);
    }

    // TODO: 10/07/18 getAdskey
    private String getAds_Key() {
        return mPrefs.getString(ADS_KEY, "null");
    }
}