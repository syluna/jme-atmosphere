package com.jayfella.jme.atmosphere.debug;

import com.jayfella.jme.atmosphere.NewAtmosphereState;
import com.jayfella.jme.jfx.JavaFxUI;
import com.jayfella.jme.jfx.impl.JmeUpdateLoop;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class JfxAtmosphereControls extends GridPane implements JmeUpdateLoop {

    private final NewAtmosphereState atmosphereState;

    public JfxAtmosphereControls(NewAtmosphereState atmosphereState) {
        super();

        this.atmosphereState = atmosphereState;

        Label timeLabel = new Label("Hour of Day");
        Slider time = new Slider(0, 23, 12);
        time.valueProperty().addListener((observable, oldValue, newValue) -> {
            atmosphereState.getCalendar().setHour(newValue.intValue());
        });
        addRow(getRowCount(), timeLabel, time);

        Label daySpeedLabel = new Label("Day Speed");
        Slider daySpeed = new Slider(1, 3600, 1);
        daySpeed.valueProperty().addListener((observable, oldValue, newValue) -> {
            atmosphereState.getCalendar().setTimeMult(newValue.floatValue());
        });
        addRow(getRowCount(), daySpeedLabel, daySpeed);

        Label windLabel = new Label("Wind Speed");
        Slider wind = new Slider(0, 3, 0.5);
        wind.valueProperty().addListener((observable, oldValue, newValue) -> {
            atmosphereState.getCloudSettings().setWindSpeed(newValue.floatValue());
        });
        addRow(getRowCount(), windLabel, wind);

        Label coverLabel = new Label("Cloud Cover");
        Slider cover = new Slider(0, 1, 0.1);
        cover.valueProperty().addListener((observable, oldValue, newValue) -> {
            atmosphereState.getCloudSettings().setCover(newValue.floatValue());
            System.out.println(newValue);
        });
        addRow(getRowCount(), coverLabel, cover);

        Label dirxLabel = new Label("Direction X");
        Slider dirX = new Slider(-1, 1, -1);
        dirX.valueProperty().addListener((observable, oldValue, newValue) -> {
            Vector2f dir = atmosphereState.getCloudSettings().getWindDirection().clone();
            dir.setX(newValue.floatValue());
            atmosphereState.getCloudSettings().setWindDirection(dir);
        });
        addRow(getRowCount(), dirxLabel, dirX);

        Label dirzLabel = new Label("Direction Z");
        Slider dirZ = new Slider(-1, 1, -1);
        dirZ.valueProperty().addListener((observable, oldValue, newValue) -> {
            Vector2f dir = atmosphereState.getCloudSettings().getWindDirection().clone();
            dir.setY(newValue.floatValue());
            atmosphereState.getCloudSettings().setWindDirection(dir);
        });
        addRow(getRowCount(), dirzLabel, dirZ);

        Label phaseLabel = new Label("Moon Phase");
        Slider phase = new Slider(-FastMath.TWO_PI, FastMath.TWO_PI, 0);
        phase.valueProperty().addListener((observable, oldValue, newValue) -> {
            atmosphereState.getMoon().setPhase(newValue.floatValue());
        });
        addRow(getRowCount(), phaseLabel, phase);

        dayPartLabel = new Label("");
        addRow(getRowCount(), new Label("Time: "), dayPartLabel);
    }
    Label dayPartLabel;

    @Override
    public void update(float tpf) {
        JavaFxUI.getInstance().runInJavaFxThread(() -> {
            dayPartLabel.setText(
                     atmosphereState.getCalendar().getTimeString() +
                            " / " + atmosphereState.getCalendar().getDayPart()
            );
        });

    }
}
