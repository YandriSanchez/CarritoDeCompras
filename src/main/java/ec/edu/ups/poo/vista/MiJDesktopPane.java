package ec.edu.ups.poo.vista;

import javax.swing.*;
import java.awt.*;

public class MiJDesktopPane extends JDesktopPane {

    public MiJDesktopPane() {
        setOpaque(true);
        setBackground(new Color(33, 37, 43)); // Fondo gris oscuro
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelW = getWidth();
        int panelH = getHeight();

        int dibujoW = 650;
        int dibujoH = 550;

        int offsetX = (panelW - dibujoW) / 2;
        int offsetY = (panelH - dibujoH) / 2;

        g2.translate(offsetX, offsetY);

        drawSilhouetteCart(g2, dibujoW, dibujoH);

        g2.dispose();
    }

    private void drawSilhouetteCart(Graphics2D g2, int w, int h) {
        // Fondo amarillo
        g2.setColor(Color.BLUE);
        g2.fillRoundRect(0, 0, w, h, 60, 60);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(8));
        g2.drawRoundRect(0, 0, w, h, 60, 60);

        // Coordenadas base
        int baseX = w / 4;
        int baseY = h / 2 + 50;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // Cabeza
        int headSize = 50;
        g2.fillOval(baseX, baseY - 180, headSize, headSize);

        // Cuerpo (línea vertical)
        g2.drawLine(baseX + headSize / 2, baseY - 130, baseX + headSize / 2, baseY - 50);

        // Brazo (línea al carrito)
        g2.drawLine(baseX + headSize / 2, baseY - 110, baseX + 120, baseY - 80);

        // Pierna izquierda
        g2.drawLine(baseX + headSize / 2, baseY - 50, baseX - 30, baseY + 60);

        // Pierna derecha
        g2.drawLine(baseX + headSize / 2, baseY - 50, baseX + 60, baseY + 60);

        // Carrito
        g2.setStroke(new BasicStroke(15, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int cartX = baseX + 120;
        int cartY = baseY - 100;
        int cartW = 250;
        int cartH = 100;

        // Contorno principal
        g2.drawLine(cartX, cartY, cartX + cartW, cartY);
        g2.drawLine(cartX, cartY, cartX + 40, cartY + cartH);
        g2.drawLine(cartX + 40, cartY + cartH, cartX + cartW, cartY + cartH);
        g2.drawLine(cartX + cartW, cartY, cartX + cartW, cartY + cartH);

        // Líneas horizontales del carrito
        g2.drawLine(cartX + 10, cartY + 30, cartX + cartW - 10, cartY + 30);
        g2.drawLine(cartX + 15, cartY + 60, cartX + cartW - 10, cartY + 60);

        // Ruedas
        int wheelSize = 30;
        g2.fillOval(cartX + 15 - wheelSize / 2, cartY + cartH, wheelSize, wheelSize);
        g2.fillOval(cartX + cartW - 20 - wheelSize / 2, cartY + cartH, wheelSize, wheelSize);
    }
}
