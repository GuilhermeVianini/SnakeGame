import com.sun.prism.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class GamePanel extends JPanel implements ActionListener
{
    // constantes das configs
    private static final int TELA_ALTURA = 500;
    private static final int TELA_LARGURA = 500;
    private static final int TAMANHO_UNITARIO = 25;
    private static final int UNIDADES = (TELA_ALTURA * TELA_LARGURA);
    private  Random random; // a comida vai aparecer em posições aleatorias
}
    public GamePanel()
    {
    random = new Random();

    this.setPreferredSize(new Dimension(TELA_LARGURA, TELA_ALTURA));
    this.setBackground(Color.BLACK);
    startGame();
    }

    public void startGame()
    {
        novaComida();
        rodando = true;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        desenhar(g);
        // metodo que desenha o jogo
    }

    public void desenhar(Graphics g) // metodo que desenha a cobra e comida
    {
        if(rodando) // comida
        {
            g.fillOval(comidaX, comidaY, TAMANHO_UNITARIO);
            for (int i = 0; i < partesCobra; i++)
            {
                if (i == 0)
                {
                    g.setColor(Color.green); //cabeça da cobra hihihiihi
                } else {
                    g.setColor(new Color(45, 180, 0)); //corpo da cobra (tenho que criar as variaveis para recebar a cor ainda)
                }
                g.fillRect(x[i], y[1], TAMANHO_UNITARIO);
            }
            g.setColor(Color.red);
        }else
        {
            gameOver(g); // preciso criar ainda
        }
    }
}
// talvez eu tenha implementado coisas de mais sem testar.