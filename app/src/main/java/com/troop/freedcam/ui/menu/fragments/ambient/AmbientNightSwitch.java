package com.troop.freedcam.ui.menu.fragments.ambient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.troop.freedcam.R;
import com.troop.freedcam.ui.AppSettingsManager;
import com.troop.freedcam.ui.switches.NightModeSwitchHandler;

/**
 * Created by George on 3/17/2015.
 */
public class AmbientNightSwitch extends NightModeSwitchHandler {
    ImageView textView;
    LinearLayout HouseNight;
    ImageView On;
    ImageView Off;
    ImageView Tripod;

    public AmbientNightSwitch(View activity, AppSettingsManager appSettingsManager)
    {
        super(activity, appSettingsManager);
    }

    @Override
    protected void init()
    {
        textView = (ImageView)activity.findViewById(R.id.imageViewNight);

        textView.setOnClickListener(this);


        HouseNight = (LinearLayout)activity.findViewById(R.id.scrollViewNight);
        HouseNight.setVisibility(View.GONE);

        Off = (ImageView)activity.findViewById(R.id.btnNight_off);



        On = (ImageView)activity.findViewById(R.id.btnNight_on);



        Tripod = (ImageView)activity.findViewById(R.id.btnNight_torch);

    }

    ImageView.OnClickListener OnView = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //  LongEx.startAnimation(out);
            // HDR.startAnimation(out);
            // Movie.startAnimation(out);
            // Picture.startAnimation(out);
            cameraUiWrapper.camParametersHandler.NightMode.SetValue("on", true);
            appSettingsManager.setString(AppSettingsManager.SETTING_NIGHTEMODE, "on");


            //moduleView.setBackground(activity.findViewById(R.drawable.nubia_ui_mode_pic));
            Bitmap tmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.material_ui_night_on);
            textView.setImageBitmap(tmp);


            HouseNight.setVisibility(View.GONE);

        }
    };

    ImageView.OnClickListener OffView = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // LongEx.startAnimation(out);
            //  HDR.startAnimation(out);
            //  Movie.startAnimation(out);
            //  Picture.startAnimation(out);
            cameraUiWrapper.camParametersHandler.NightMode.SetValue("off", true);
            appSettingsManager.setString(AppSettingsManager.SETTING_NIGHTEMODE, "off");


            Bitmap tmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.material_ui_night_off);
            textView.setImageBitmap(tmp);


            HouseNight.setVisibility(View.GONE);

        }
    };

    ImageView.OnClickListener TripodView = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // LongEx.startAnimation(out);
            // HDR.startAnimation(out);
            // Movie.startAnimation(out);
            // Picture.startAnimation(out);
            cameraUiWrapper.camParametersHandler.NightMode.SetValue("tripod", true);
            appSettingsManager.setString(AppSettingsManager.SETTING_NIGHTEMODE, "tripod");


            Bitmap tmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.material_ui_night_tripod);
            textView.setImageBitmap(tmp);


            HouseNight.setVisibility(View.GONE);

        }
    };

    @Override
    public void onClick(View v)
    {
        if(HouseNight.getVisibility() == View.GONE)
        {
            LinearLayout Flash = (LinearLayout)activity.findViewById(R.id.scrollViewFlash);
            LinearLayout Module = (LinearLayout)activity.findViewById(R.id.scrollViewModule);
            Flash.setVisibility(View.GONE);
            Module.setVisibility(View.GONE);
            Off.setOnClickListener(OffView);
            On.setOnClickListener(OnView);
            Tripod.setOnClickListener(TripodView);

            HouseNight.setVisibility(View.VISIBLE);
        }
        else
        {
            HouseNight.setVisibility(View.GONE);
        }
    }

    @Override
    public void ParametersLoaded()
    {
        activity.post(new Runnable() {
            @Override
            public void run() {
                if (cameraUiWrapper.camParametersHandler.NightMode != null && cameraUiWrapper.camParametersHandler.NightMode.IsSupported())
                {
                    textView.setVisibility(View.VISIBLE);
                    String appSet = appSettingsManager.getString(AppSettingsManager.SETTING_NIGHTEMODE);
                    String para = cameraUiWrapper.camParametersHandler.NightMode.GetValue();
                    if (para == null || para.equals(""))
                        para = "off";
                    if (appSet.equals("")) {
                        appSet = cameraUiWrapper.camParametersHandler.NightMode.GetValue();
                        appSettingsManager.setString(AppSettingsManager.SETTING_NIGHTEMODE, para);
                    }
                    if (!appSet.equals(para))
                        cameraUiWrapper.camParametersHandler.NightMode.SetValue(appSet, true);

                }
                else
                {
                    textView.setVisibility(View.GONE);
                }
            }
        });

    }
}
