package face;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JTextArea jTextArea0;
	private JScrollPane jScrollPane0;
	private JTextArea jTextArea1;
	private JScrollPane jScrollPane1;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public Main() {
		initComponents();
		initEvent();

	}

	private void initEvent() {
		this.jButton0.addActionListener(this);
		this.jButton1.addActionListener(this);
		this.jButton2.addActionListener(this);

	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(8, 666, 10, 10), new Leading(8, 155, 10, 10)));
		add(getJButton0(), new Constraints(new Leading(18, 10, 10), new Leading(166, 215, 225)));
		add(getJButton1(), new Constraints(new Leading(253, 10, 10), new Leading(166, 215, 225)));
		add(getJButton2(), new Constraints(new Leading(475, 10, 10), new Leading(166, 215, 225)));
		add(getJPanel1(), new Constraints(new Leading(10, 662, 12, 12), new Bilateral(212, 12, 191)));
		setSize(684, 458);
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Unicode转ASCII");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("转Unicode(10进制)");
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("转Unicode(16进制)");
		}
		return jButton0;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTextArea1());
		}
		return jScrollPane1;
	}

	private JTextArea getJTextArea1() {
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
			jTextArea1.setText("");
		}
		return jTextArea1;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTextArea0());
		}
		return jScrollPane0;
	}

	private JTextArea getJTextArea0() {
		if (jTextArea0 == null) {
			jTextArea0 = new JTextArea();
			jTextArea0.setText("");
		}
		return jTextArea0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJScrollPane1(), new Constraints(new Leading(8, 648, 10, 10), new Leading(6, 213, 10, 10)));
		}
		return jPanel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(7, 651,
					10, 10), new Leading(6, 144, 10, 10)));
		}
		return jPanel0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class. Note: This class is only created so that you can
	 * easily preview the result at runtime. It is not expected to be managed by
	 * the designer. You can modify it as you like.
	 */
	public static void main(String[] args) {

		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Main frame = new Main();
				frame.setDefaultCloseOperation(Main.EXIT_ON_CLOSE);
				frame.setTitle("转Unicode小工具");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(jButton0)) {
			this.jTextArea1.setText(this.convert(this.jTextArea0.getText(),
					false));

		} else if (e.getSource().equals(jButton1)) {
			this.jTextArea1.setText(this.convert(this.jTextArea0.getText(),
					true));
		} else if (e.getSource().equals(jButton2)) {

			this.jTextArea1.setText(this.toAscii(this.jTextArea0.getText()));
		}
	}

	/**
	 * @param str
	 * @param algorism
	 *            为True时 转成十进制 , 为False转成十六进制
	 * @return
	 */
	private String convert(String str, boolean algorism) {
		if (str == null || str.equals(""))
			return "";
		StringBuilder sb = new StringBuilder();
		if (!algorism) {
			for (int i = 0; i < str.length(); i++) {
				sb.append("\\u").append(Integer.toHexString(str.charAt(i)));
			}
		} else {
			for (int i = 0; i < str.length(); i++) {
				sb.append("&#").append(Integer.toString(str.charAt(i))).append(
						";");
			}
		}
		return sb.toString();
	}

	private String toAscii(String str) {
		if (str == null || str.equals(""))
			return "";
		try {
			if (str.startsWith("\\u")) {
				str = str.replaceAll("\\\\u", ",0X");
				String[] chars = str.split(",");
				StringBuilder sb = new StringBuilder();
				for (String c : chars) {
					if (c.equals(""))
						continue;
					int i = Integer.decode(c);
					sb.append((char) i);
				}
				return sb.toString();
			} else if ((str.startsWith("&#") && str.endsWith(";"))) {
				str = str.replace("&#", "");
				String[] chars = str.split(";");
				StringBuilder sb = new StringBuilder();
				for (String c : chars) {
					if (c.equals(""))
						continue;
					int i = Integer.decode(c);
					sb.append((char) i);
				}
				return sb.toString();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "您输入了不正确的编码", "错误",
				JOptionPane.ERROR_MESSAGE);

		return "";

	}

}
