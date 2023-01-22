// В этом коде создаётся сетка 3x3 кнопок с использованием класса `JButton` и `GridLayout` из библиотеки Swing.
// Каждой кнопке назначается слушатель события, который обновляет текст кнопки на "X" или "O" в зависимости от того, кто сейчас ходит.
// Также проверяется победа и ничья.


import java.awt.GridLayout; // импорт класса GridLayout для создания сетки кнопок
import java.awt.event.ActionEvent; // импорт класса ActionEvent для события нажатия кнопки
import java.awt.event.ActionListener; // импорт интерфейса ActionListener для обработки события нажатия кнопки

import javax.swing.JButton; // импорт класса JButton для создания кнопок
import javax.swing.JFrame; // импорт класса JFrame для создания окна
import javax.swing.JOptionPane; // импорт класса JOptionPane для вывода сообщения о победе или ничьей

public class TicTacToe extends JFrame implements ActionListener {
// Создание массива кнопок и переменной для определения хода
private JButton[][] buttons;
private boolean xTurn;
public TicTacToe() {
    setTitle("Крестики-нолики"); // Название окна
    setSize(300, 300); // Размер окна
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Закрытие программы при закрытии окна

    buttons = new JButton[3][3];
    xTurn = true;

    setLayout(new GridLayout(3, 3)); // Установка сетки 3x3 для кнопок
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            buttons[row][col] = new JButton();
            buttons[row][col].addActionListener(this); // Добавление слушателя события на каждую кнопку
            add(buttons[row][col]); // Добавление кнопки на окно
        }
    }

    setVisible(true); // Отображение окна
}

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        // Проверка, что кнопка еще не была нажата
        if (button.getText().equals("")) {
            if (xTurn) {
                button.setText("X"); // Установка Х, если ход Х
            } else {
                button.setText("O"); // Установка О, если ход О
            }
            xTurn = !xTurn; // Смена хода
        }

        checkWin(); // Проверка победы
    }

    private void checkWin() {
        String winner = "";
        boolean draw = true;

        // Проверка победы по строкам
        for (int row = 0; row < 3; row++) {
            if (!buttons[row][0].getText().equals("") && buttons[row][0].getText().equals(buttons[row][1].getText())
                    &&buttons[row][1].getText().equals(buttons[row][2].getText())) {
                winner = buttons[row][0].getText();
                break;
            }
        }// Проверка победы по столбцам
        if (winner.equals("")) {
            for (int col = 0; col < 3; col++) {
                if (!buttons[0][col].getText().equals("") && buttons[0][col].getText().equals(buttons[1][col].getText())
                        && buttons[1][col].getText().equals(buttons[2][col].getText())) {
                    winner = buttons[0][col].getText();
                    break;
                }
            }
        }

        // Проверка победы по диагоналям
        if (winner.equals("")) {
            if (!buttons[0][0].getText().equals("") && buttons[0][0].getText().equals(buttons[1][1].getText())
                    && buttons[1][1].getText().equals(buttons[2][2].getText())) {
                winner = buttons[0][0].getText();
            } else if (!buttons[0][2].getText().equals("") && buttons[0][2].getText().equals(buttons[1][1].getText())
                    && buttons[1][1].getText().equals(buttons[2][0].getText())) {
                winner = buttons[0][2].getText();
            }
        }

        // Проверка ничьей
        if (winner.equals("")) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (buttons[row][col].getText().equals("")) {
                        draw = false;
                        break;
                    }
                }
                if (!draw) {
                    break;
                }
            }
        }

        // Вывод сообщения о победе или ничье
        if (!winner.equals("")) {
            JOptionPane.showMessageDialog(this, winner + " выиграл!");
            reset();
        } else if (draw) {
            JOptionPane.showMessageDialog(this, "Ничья!");
            reset();
        }
    }
    private void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

