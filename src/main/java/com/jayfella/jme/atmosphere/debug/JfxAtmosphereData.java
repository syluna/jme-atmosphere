package com.jayfella.jme.atmosphere.debug;

import com.jayfella.jme.atmosphere.NewAtmosphereState;
import com.jayfella.jme.jfx.JavaFxUI;
import com.jayfella.jme.jfx.impl.JmeUpdateLoop;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class JfxAtmosphereData extends GridPane implements JmeUpdateLoop {

    private final NewAtmosphereState atmosphereState;

    private Slider ambientSlider;
    private Slider directionalSlider;

    public JfxAtmosphereData(NewAtmosphereState atmosphereState) {
        super();

        this.atmosphereState = atmosphereState;

        Label ambientLabel = new Label("Ambient");
        ambientSlider = new Slider(0, 1, 0);
        addRow(getRowCount(), ambientLabel, ambientSlider);

        Label directionalLabel = new Label("Directional");
        directionalSlider = new Slider(0, 1, 0);
        addRow(getRowCount(), directionalLabel, directionalSlider);

    }

    @Override
    public void update(float tpf) {
        JavaFxUI.getInstance().runInJavaFxThread(() -> {

            float ambientValue = (
                            atmosphereState.getAmbientLight().getColor().r +
                            atmosphereState.getAmbientLight().getColor().g +
                            atmosphereState.getAmbientLight().getColor().b) / 3;

            ambientSlider.setValue(ambientValue);


            float directionalValue = (
                            atmosphereState.getDirectionalLight().getColor().r +
                            atmosphereState.getDirectionalLight().getColor().g +
                            atmosphereState.getDirectionalLight().getColor().b) / 3;

            directionalSlider.setValue(directionalValue);


        });

    }

}
