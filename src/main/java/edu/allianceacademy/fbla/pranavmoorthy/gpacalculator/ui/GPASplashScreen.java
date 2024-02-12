package edu.allianceacademy.fbla.pranavmoorthy.gpacalculator.ui;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.SplashScreen;


public class GPASplashScreen {
    private void renderSplashFrame(Graphics2D g) {
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(120,140,200,40);
        g.setPaintMode();
    }

    public GPASplashScreen(){
        final SplashScreen splash = SplashScreen.getSplashScreen();
        Graphics2D g = splash.createGraphics();
        for(int i = 0; i < 50; i++) {
            renderSplashFrame(g);
            splash.update();
            try {
                Thread.sleep(90);
            }
            catch(InterruptedException e) {
            }
        }
        splash.close();
    }

    public static void main (String args[]) {
        GPASplashScreen test = new GPASplashScreen();
    }
}
