import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryMatch extends JFrame {

    private final String[] symbols = {
            "images/Apple.jpg", "images/Apple.jpg",
            "images/banana.jpg", "images/banana.jpg",
            "images/download.jpg", "images/download.jpg",
            "images/cherry.jpg", "images/cherry.jpg",
            "images/download (1).jpg", "images/download (1).jpg",
            "images/kiwi.jpg", "images/kiwi.jpg",
            "images/download (2).jpg", "images/download (2).jpg",
            "images/download (3).jpg", "images/download (3).jpg"
    };

    private final JButton[] buttons = new JButton[16];
    private String[] board;
    private JButton firstCard = null;
    private JButton secondCard = null;
    private int matchesFound = 0;

    public MemoryMatch() {
        setTitle("NIIT Modern Memory Match");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4, 10, 10));

        setupGame();
        setVisible(true);
    }

    // 🔥 Load and resize images properly
    private ImageIcon loadImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private int getIndex(JButton button) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == button) return i;
        }
        return -1;
    }

    private void setupGame() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, symbols);
        Collections.shuffle(list);
        board = list.toArray(new String[0]);

        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusPainted(false);
            buttons[i].setIcon(loadImage("/images/back.png")); // ✅ fixed path

            final int index = i;
            buttons[i].addActionListener(e -> handleCardClick(buttons[index], index));

            add(buttons[i]);
        }
    }

    private void handleCardClick(JButton clickedButton, int index) {

        // ❌ Removed getText() check (we're using icons now)
        if (clickedButton == firstCard || secondCard != null) return;

        clickedButton.setIcon(loadImage(board[index]));

        if (firstCard == null) {
            firstCard = clickedButton;
        } else {
            secondCard = clickedButton;

            Timer timer = new Timer(800, e -> checkMatch());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void checkMatch() {
        if (board[getIndex(firstCard)].equals(board[getIndex(secondCard)])) {
            firstCard.setEnabled(false);
            secondCard.setEnabled(false);
            matchesFound++;

            if (matchesFound == 8) {
                JOptionPane.showMessageDialog(this, "Master Developer, You Won!");
            }
        } else {
            firstCard.setIcon(loadImage("/images/back.png"));
            secondCard.setIcon(loadImage("/images/back.png"));
        }

        firstCard = null;
        secondCard = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemoryMatch::new);
    }
}