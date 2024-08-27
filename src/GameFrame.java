import javax.swing.JFrame; // JFrame é uma classe do Swing que cria janelas
public class GameFrame extends JFrame
{
    public GameFrame()
    {
        this.add(new GamePanel()); // cria a janela do jogo

        // configs da janela
        this.setTitle("Snake"); //titulo
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ao fechar a tela, o programa fecha tbm
        this.setVisible(true);//janela se torna visivel
        this.setLocationRelativeTo(null);// a tela sempre vai estar centralizada
    }
}