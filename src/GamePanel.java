import com.sun.prism.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class GamePanel extends JPanel implements ActionListener
{
    // constantes das configs e logica do jogo
    private static final int TELA_ALTURA = 500;
    private static final int TELA_LARGURA = 500;
    private static final int TAMANHO_UNITARIO = 25;
    private static final int UNIDADES = (TELA_ALTURA * TELA_LARGURA)/(TAMANHO_UNITARIO * TAMANHO_UNITARIO);
    private static final int ATRASO = 75; //tempo de atraso de atualização

    //Armazena as coordenadas x e y da cobra
    private final int[] x = new int[UNIDADES];
    private final int[] y = new int[UNIDADES];

    //Variáveis de "estado do jogo"
    private int partesCobra = 3;// tamanho inicial
    private int comidasConsumidas; //contador de comidas comidas
    private int comidaX;
    private int comidaY;
    private Random random; // posição aleatoria da comida
    public GamePanel()
    {
    random = new Random();

    this.setPreferredSize(new Dimension(TELA_LARGURA, TELA_ALTURA));
    this.setBackground(Color.BLACK);
    this.addKeyListener(new AdaptadorTeclado());
    startGame();
    }

    public void startGame()
    {
        novaComida(); // gera a primeira comida
        rodando = true; // define se o jogo está sendo execultado
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
            g.setColor(Color.yellow);
            g.fillOval(comidaX, comidaY, TAMANHO_UNITARIO, TAMANHO_UNITARIO);
            for (int i = 0; i < partesCobra; i++)
            {
                if (i == 0)
                {
                    g.setColor(Color.green); //cabeça da cobra hihihiihi
                } else {
                    g.setColor(new Color(45, 180, 0)); //corpo da cobra (tenho que criar as variaveis para recebar a cor ainda)
                }
                g.fillRect(x[i], y[1], TAMANHO_UNITARIO, TAMANHO_UNITARIO);
            }
            // desenha pontuacao
            g.setColor(Color.yellow);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: ") + comidasConsumidas,(TELA_LARGURA - metrics.charsWidth("Score: " + comidasConsumidas))/2, g.getFont().getSize());
        }else
        {
            gameOver(g); // se o jogo não está rodando ele exibe essa tela
        }
    }

    public void novaComida(){
        comidaX = random.nextInt((int) (TELA_LARGURA/TAMANHO_UNITARIO)) * TAMANHO_UNITARIO;
        comidaY = random.nextInt((int) (TELA_ALTURA/TAMANHO_UNITARIO)) * TAMANHO_UNITARIO;

    }
}
// arrumar problema de abstração na linha 7 e fazer um metodo timer para o jogo atualizar