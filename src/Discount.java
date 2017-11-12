import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Discount extends JFrame {

	private JPanel contentPane;
	private JTextField Price;
	private JTextField Discount;
	private JLabel Answer;

	int pnum;//金額（入力）
	int dnum;//割引額（入力）
	int result;//アンサーラベルに表示する割引後の金額
	int waribiki;//割引額（表示）

	double dnumoff;//％引き専用の割引額（入力）
	double off;//％引き（表示）

	private final Action action1 = new Button1();
	private final Action action2 = new Button2();
	private final Action action3 = new Button3();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Discount frame = new Discount();
					frame.setTitle("割引計算機");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Discount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		//金額入力
		Price = new JTextField();
		Price.setFont(new Font("Monospaced", Font.PLAIN, 20));
		Price.setBounds(22, 10, 108, 30);
		panel.add(Price);
		Price.setColumns(10);

		JLabel label = new JLabel("円の");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(136, 18, 29, 22);
		panel.add(label);

		//割引率入力
		Discount = new JTextField();
		Discount.setFont(new Font("Monospaced", Font.PLAIN, 30));
		Discount.setBounds(22, 50, 56, 56);
		panel.add(Discount);
		Discount.setColumns(10);

		//以下ボタン
		JButton Button1 = new JButton("割引");
		Button1.setFont(new Font("Monospaced", Font.PLAIN, 12));
		Button1.setAction(action1);
		Button1.setBounds(90, 50, 75, 23);
		panel.add(Button1);

		JButton Button2 = new JButton("％OFF");
		Button2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		Button2.setAction(action2);
		Button2.setBounds(90, 83, 75, 23);
		panel.add(Button2);

		JButton Button3 = new JButton("リセット");
		Button3.setFont(new Font("Monospaced", Font.PLAIN, 12));
		Button3.setAction(action3);
		Button3.setBounds(22, 229, 143, 23);
		panel.add(Button3);

		//回答を表示するラベル
		Answer = new JLabel();
		Answer.setFont(new Font("Monospaced", Font.BOLD, 13));
		Answer.setBounds(22, 116, 143, 103);
		panel.add(Answer);

		reset();//初期化処理
	}

	//割引率の計算(ボタン１）
	public void Button1() {
		waribiki =  pnum * dnum / 10;//割引率＝金額＊割引/10
		result =  pnum - waribiki;
	}

	//％OFFの計算（ボタン２）
	public void Button2() {
		off =  pnum * dnumoff / 100;//off＝金額＊％オフ/100
		result = (int) (pnum - off);
	}

	//リセット処理（ボタン3）
	public void reset() {
		Answer.setText("");
		Price.setText("");
		Discount.setText("");
		pnum = 0;
		dnum = 0;
		result = 0;
		waribiki = 0;
		dnumoff =0;
		off = 0;
	}

	//ボタン1（割引率）
	private class Button1 extends AbstractAction {
		public Button1() {
			putValue(NAME, "割引");
		}
		public void actionPerformed(ActionEvent e) {
			pnum = Integer.parseInt(Price.getText());
			dnum = Integer.parseInt(Discount.getText());
			Button1();//計算処理実行
			Answer.setText("<html>割引率は" + waribiki + "円です" + "<br>割引後の金額は<br>" + result + "円<br>になりました" + "<br>");
		}
	}

	//ボタン2（％オフ）
	private class Button2 extends AbstractAction {
		public Button2() {
			putValue(NAME, "％OFF");
		}
		public void actionPerformed(ActionEvent e) {
			pnum = Integer.parseInt(Price.getText());
			dnumoff = Integer.parseInt(Discount.getText());
			Button2();//計算処理実行
			Answer.setText("<html>割引率は" + off + "円です" + "<br>割引後の金額は<br>" + result + "円<br>になりました" + "<br>");
		}
	}

	//ボタン3（リセット）
	private class Button3 extends AbstractAction {
		public Button3() {
			putValue(NAME, "リセット");
		}
		public void actionPerformed(ActionEvent e) {
        	reset();//リセット処理
		}
	}
}