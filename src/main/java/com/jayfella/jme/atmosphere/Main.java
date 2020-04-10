package com.jayfella.jme.atmosphere;

import com.jayfella.jme.atmosphere.debug.JfxAtmosphereControls;
import com.jayfella.jme.atmosphere.debug.JfxAtmosphereData;
import com.jayfella.jme.jfx.JavaFxUI;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.TranslucentBucketFilter;
import com.jme3.system.AppSettings;
import com.jme3.water.WaterFilter;
import javafx.scene.layout.AnchorPane;

public class Main extends SimpleApplication {

    public static void main(String... args) {

        Main main = new Main();

        AppSettings appSettings = new AppSettings(true);
        // appSettings.setResolution(1280, 720);
        // appSettings.setFrameRate(120);
        appSettings.setTitle("Test :: Sky");

        main.setSettings(appSettings);
        main.setShowSettings(true);
        main.setPauseOnLostFocus(false);

        main.start();

    }

    private WaterFilter waterFilter;
    private NewAtmosphereState atmosphereState;

    @Override
    public void simpleInitApp() {

        cam.setFrustumFar(10000);
        flyCam.setMoveSpeed(50);
        flyCam.setDragToRotate(true);
        cam.setLocation(new Vector3f(0, 10, 0));

        // SkyState skyState = new SkyState();
        atmosphereState = new NewAtmosphereState(rootNode);
        stateManager.attach(atmosphereState);

        waterFilter = new WaterFilter();
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);

        fpp.addFilter(waterFilter);
        fpp.addFilter(new TranslucentBucketFilter());
        //viewPort.addProcessor(fpp);

        JavaFxUI.initialize(this);

       JfxAtmosphereControls atmosphereControls = new JfxAtmosphereControls(atmosphereState);
       JavaFxUI.getInstance().attachChild(atmosphereControls);

        JfxAtmosphereData atmosphereData = new JfxAtmosphereData(atmosphereState);
        AnchorPane.setTopAnchor(atmosphereData, 0d);
        AnchorPane.setRightAnchor(atmosphereData, 0d);

        JavaFxUI.getInstance().attachChild(atmosphereData);
    }



    @Override
    public void simpleUpdate(float tpf) {
        //waterFilter.setLightDirection(getStateManager().getState(SkyState.class).getSun().getDirection());

    }



}
