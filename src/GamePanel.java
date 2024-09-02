import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.Graphics;
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
    private int comidasConsumidas;
    private int comidaX;
    private int comidaY;
    private char direcao = 'D'; // U pra cima D pra baixo L pra esquerda e R pra direita
    private boolean rodando = false;
    private Timer timer;
    private Random random; // posição aleatoria da comida

    public GamePanel()
    {
    random = new Random();

    this.setPreferredSize(new Dimension(TELA_LARGURA, TELA_ALTURA));
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(new AdaptadorTeclado());
    startGame();
    }

    public void startGame()
    {
        novaComida(); // gera a primeira comida
        rodando = true; // define se o jogo está sendo execultado
        timer = new Timer(ATRASO,this);
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g)
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

            String scoreText = "Score: " + comidasConsumidas;
            g.drawString(scoreText, (TELA_LARGURA - metrics.stringWidth(scoreText)) / 2, g.getFont().getSize());
        }else
        {
            gameOver(g); // se o jogo não está rodando ele exibe essa tela
        }
    }

    public void novaComida(){
        comidaX = random.nextInt((int) (TELA_LARGURA/TAMANHO_UNITARIO)) * TAMANHO_UNITARIO;
        comidaY = random.nextInt((int) (TELA_ALTURA/TAMANHO_UNITARIO)) * TAMANHO_UNITARIO;

    }
    public void mover() {
        for (int i = partesCobra; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direcao) {
            case 'u':
                y[0] = y[0] - TAMANHO_UNITARIO;
                break;
            case 'D':
                y[0] = y[0] + TAMANHO_UNITARIO;
                break;
            case 'L':
                x[0] = x[0] - TAMANHO_UNITARIO;
                break;
            case 'R':
                x[0] =x[0] + TAMANHO_UNITARIO;
                break;
        }
    }
    public void verificarComida()
    {
        if ((x[0] == comidaX) && (y[0] == comidaY))
        {
            partesCobra++;
            comidasConsumidas++;
            novaComida();
        }
    }
    public void verificarColisao() {
        for (int i = partesCobra; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                rodando = false;
            }
        }
        if (x[0] < 0 || x[0] >= TELA_LARGURA || y[0] < 0 || y[0] >= TELA_ALTURA) {
            rodando = false;
        }
        if (!rodando) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g)
    {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD,75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game over", (TELA_LARGURA - metrics1.stringWidth("Game over"))/2, TELA_ALTURA/2);

        g.setColor(Color.yellow);
        g.setFont(new Font("Ink Free", Font.BOLD,40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + comidasConsumidas, (TELA_LARGURA - metrics2.stringWidth("Score: " + comidasConsumidas)) / 2, g.getFont().getSize());
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (rodando)
        {
            mover();
            verificarComida();
            verificarColisao();
        }
        repaint();
    }
    public class AdaptadorTeclado extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    if (direcao != 'R')
                    {
                        direcao = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direcao != 'L')
                    {
                        direcao = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direcao != 'D')
                    {
                        direcao = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direcao != 'U')
                    {
                        direcao= 'D';
                    }
                    break;
            }
        }
    }
}
// arrumar problema de abstração na linha 7 e fazer um metodo timer para o jogo atualizar