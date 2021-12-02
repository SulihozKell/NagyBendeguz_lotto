package com.example.nagybendeguz_lotto;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.*;

public class HelloController {
    @FXML
    private Button btnSorsolRendez;
    @FXML
    private Label nagySzam;
    @FXML
    private Label kiirtSzamok;
    private Timer timer;
    private Timer timerAnimacio;
    private int feladat;
    private List<Integer> lista = new ArrayList<>();

    @FXML
    public void initialize() {
        feladat = 0;
    }

    public void sorsolKatt() {
        if (feladat == 0) {
            btnSorsolRendez.setText("Sorsol");
            Random rAnimaciohoz = new Random();
            timerAnimacio = new Timer();
            TimerTask timerTaskAnimacio = new TimerTask() {
                @Override
                public void run() {
                    int eredmeny2 = rAnimaciohoz.nextInt(90) + 1;
                    Platform.runLater(() -> nagySzam.setText(String.format("%2d", eredmeny2)));
                }
            };
            timerAnimacio.scheduleAtFixedRate(timerTaskAnimacio, 1, 1);

            Random r = new Random();
            int eredmenyRandom = r.nextInt(90) + 1;
            if (lista.contains(eredmenyRandom)) {
                do {
                    eredmenyRandom = r.nextInt(90) + 1;
                }
                while (lista.contains(eredmenyRandom));
            }
            int eredmeny = eredmenyRandom;

            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    timerAnimacio.cancel();
                    lista.add(eredmeny);
                    Platform.runLater(() -> nagySzam.setText(String.format("%d", eredmeny)));
                    Platform.runLater(() -> kiirtSzamok.setText(kiirtSzamok.getText() + String.format(
                            "%d ", lista.get(lista.size() - 1))));
                    if (lista.size() >= 5) {
                        timer.cancel();
                        Platform.runLater(() -> btnSorsolRendez.setText("Rendez"));
                        feladat++;
                    }
                }
            };
            timer.schedule(timerTask, 1000);
        }
        else if (feladat == 1) {
            Collections.sort(lista);
            kiirtSzamok.setText(String.format("%d %d %d %d %d",
                    lista.get(0), lista.get(1), lista.get(2), lista.get(3), lista.get(4)));
            btnSorsolRendez.setText("Újra");
            feladat++;
        }
        else {
            lista.clear();
            kiirtSzamok.setText("");
            nagySzam.setText("");
            btnSorsolRendez.setText("Sorsol");
            feladat = 0;
        }
    }
}