import javax.swing.JFrame;
public class GameFrame extends JFrame
{
    public GameFrame() // cria a janela do jogo
    {
        this.add(new GamePanel());

        // configs da janela
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}