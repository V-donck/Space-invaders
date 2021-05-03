package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Java2DPlayership extends Playership{
    private Java2DFactory F;

    public Java2DPlayership(Java2DFactory F, int PlayershipHeight,int PlayershipWidth, int GameWidth,int GameHeight) {
        super(PlayershipHeight,PlayershipWidth, GameWidth, GameHeight);
        this.F = F;

/*
        JLabel labelps = new JLabel();
        labelps.setIcon(new ImageIcon("src/resource/playership.png"));
        this.labelps = labelps;

        System.out.println(frame);
        System.out.println("ship geladen");
        panel.add(labelps);

   /*
        lbl1.setLayout(null);
        Dimension size = lbl1.getPreferredSize();
        lbl1.setBounds(450, 10, 1000, 5);
        frame.setLocationRelativeTo(frame);

        frame.setSize(500, 500);
        frame.setVisible(true);
*/

    }

    public void visualise (){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getPlayerShipIm(),(int)(this.getMovementComponent().getxCoord()*F.getFactorx()),(int)(this.getMovementComponent().getyCoord()*F.getFactory()),null);
    /*    System.out.println(this.getMovementComponent().getCoord());
        int x = this.getMovementComponent().getxCoord();
        int y =  this.getMovementComponent().getyCoord();
        Dimension size = labelps.getPreferredSize();
        labelps.setBounds(x,y, size.width, size.height);
       frame.setVisible(true);
       */

    }
}
