package Game;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
public class GameTicTacToe extends JFrame implements ActionListener {
	boolean win = false;
	String icon;
	int point1 = 0, point2 = 0;
	int start = 0;
	String str = "";
	int count;
	int countH[] = {0, 0};
	String text[] = {"X", "O"};
	private Color background_cl = Color.white;
	private Color number_cl[] = {Color.red, Color.black};
	private JButton bt[] = new JButton[25];
	private JButton point1_bt, point2_bt;
	private JLabel luot;
	private int a[] = new int[25];	
	private JButton newGame_bt;
	private JPanel pn0, pn, pn2;
	Container cn;
	Timer timer;
	public GameTicTacToe(String s, int Point1, int Point2) {
		super(s);
		point1 = Point1;
		point2 = Point2;
		cn = init();
		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!win) {
					addPoint(autoPoint());
					timer.stop();
				}
			}
		});
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		pn0 = new JPanel();
		pn0.setLayout(new FlowLayout());
		luot = new JLabel("Lượt của X");
		luot.setFont(new Font("UTM Micra", 1, 20));
		pn0.add(luot);
		pn = new JPanel();
		pn.setLayout(new GridLayout(5, 5));
		for (int i = 0; i <25; i++) {
			bt[i] = new JButton(" ");
			pn.add(bt[i]);
			bt[i].setActionCommand(String.valueOf(i));
			bt[i].setBackground(background_cl);
			bt[i].addActionListener(this);
			bt[i].setFont(new Font("Antique", 1, 120));
			a[i] = 2;
		}
	
		pn2 = new JPanel();
		pn2.setLayout(new FlowLayout());
		
		newGame_bt = new JButton("New game");
		newGame_bt.setForeground(Color.black);
		newGame_bt.addActionListener(this);
		newGame_bt.setFont(new Font("UTM Swiss 721 Black Condensed", 1, 18));
		newGame_bt.setActionCommand("-1");
		
		point1_bt = new JButton(String.valueOf(point1));
		point1_bt.setFont(new Font("UTM Nokia", 1, 25));
		
		point2_bt = new JButton(String.valueOf(point2));
		point2_bt.setFont(new Font("UTM Nokia", 1, 25));
		
		pn2.add(point1_bt);
		pn2.add(newGame_bt);
		pn2.add(point2_bt);
		
		cn.add(pn0, "North");
		cn.add(pn);
		cn.add(pn2, "South");
		this.setVisible(true);
		this.setSize(720,480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		count = countH[0] = countH[1] = 0;
		return cn;
	}
	
	public int autoPoint() {
		int k = checkPoint1();
		if (k != -1)
			return k;
		else {
			k = checkPoint2();
			if (k != -1) 
				return k;
			else
				return creatPointRandom();
		}
	}
	
	public void updateMatrix() {
		for (int i = 0; i <25; i++)
			if (bt[i].getText() == "X")
				a[i] = 0;
			else if (bt[i].getText() == "O")
				a[i] = 1;
			else
				a[i] = 2;
	}
	
	public int checkWin() {
	    updateMatrix();
	    // trong ham check win nay gia tri 2 dai dien cho cac o chua dc danh giau 
	    // Check hang de kiem tra dieu kien thang
	    for (int i = 0; i < 25; i += 5) {
	        for (int j = 0; j < 2; j++) {
	            int idx1 = i + j, idx2 = i + j + 1, idx3 = i + j + 2, idx4 = i + j + 3;
	            if (a[idx1] != 2 && a[idx1] == a[idx2] && a[idx2] == a[idx3] && a[idx3] == a[idx4]) {
	                return a[idx1];
	            }
	        }
	    }
	    
	    // Check cot de kiem tra dieu kien thang
	    for (int j= 0; j < 5; j++) {
	        for (int i = 0; i < 2; i++) {
	            int idx1 = i*5+j, idx2 = (i+1)*5+j, idx3 = (i+2)*5+j, idx4 = (i+3)*5+j;
	            if (a[idx1] != 2 && a[idx1] == a[idx2] && a[idx2] == a[idx3] && a[idx3] == a[idx4]) {
	                return a[idx1];
	            }
	        }
	    }
	    
	    // Check 2  cua mang duong cheo  de kiem tra dieu kien thang
	    // duong cheo chinh
	    if ((a[0] != 2 && a[0] == a[6] && a[6] == a[12] && a[12] == a[18])||(a[6] == a[12] && a[12] == a[18] && a[18] == a[24] && a[6] != 2)) {
	        return a[6];
	    }
	    // duong cheo phu
	    if ((a[4] != 2 && a[4] == a[8] && a[8] == a[12] && a[12] == a[16])||(a[8] != 2 && a[8] == a[12] && a[12] == a[16] && a[16] == a[20])) {
	        return a[8];
	    }
	    if (a[5] != 2 && a[5] == a[11] && a[11] == a[17] && a[17] == a[23]) {
	        return a[5];
	    }
	    if (a[9] != 2 && a[9] == a[13] && a[13] == a[17] && a[17] == a[21]) {
	        return a[9];
	    }
	    if (a[1] != 2 && a[1] == a[7] && a[7] == a[13] && a[13] == a[19]) {
	        return a[1];
	    }
	    if (a[3] != 2 && a[3] == a[7] && a[7] == a[11] && a[11] == a[15]) {
	        return a[3];
	    }
	    // Check de xem cac o trong mang da dc danh giau het hay chua neu da danh giau het thi hoa
	    for (int i = 0; i < 25; i++) {
	        if (a[i] == 2) {
	            return -1;
	        }
	    }

	    // neu ko thang hoac hoa thi tiep tuc choi
	    return 2;
	}


	 // trong ham check win nay gia tri 2 dai dien cho cac o chua dc danh giau 
    // Check hang de kiem tra dieu kien thang
    // sau đó kiểm tra xem nếu máy tính đánh ở ô đó, liệu nó có thể dẫn đến chiến thắng hay không.
    // Nếu đánh vào ô đó có thể dẫn đến chiến thắng, hàm sẽ trả về vị trí đó.//
    //Nếu không, hàm sẽ đặt lại giá trị của ô đó và tiếp tục vòng lặp để thử các ô khác.
    //Cuối cùng, nếu tất cả các ô trống đều đã được kiểm tra và không có nước đi nào có thể dẫn đến chiến thắng, hàm sẽ trả về -1 để thông báo cho hàm gọi biết rằng không có nước đi nào thỏa mãn yêu cầu.
	public int checkPoint1() {
		for (int i = 0; i <25; i++)
			if (a[i] == 2) {
				a[i] = count;
				bt[i].setText(text[count]);
				if (checkWin() != -1) {
					a[i] = 2;
					bt[i].setText(" ");
					return i;
				}
				a[i] = 2;
				bt[i].setText(" ");
			}
		return -1;
	}
	//Hàm checkPoint2() tương tự như checkPoint1() nhưng thay vì gán giá trị count cho a[i], ta gán giá trị 1 - count. 
	//Điều này có nghĩa là khi count bằng 0 thì a[i] sẽ được gán giá trị 1 
	//và ngược lại, khi count bằng 1 thì a[i] sẽ được gán giá trị 0.
	//Sau đó, giống như trong checkPoint1()
	//ta kiểm tra nếu người chơi đánh vào vị trí i thì có thắng hay không. //Nếu có, ta trả về giá trị i. Nếu không, ta trả lại giá trị -1.
	
	public int checkPoint2() {
		for (int i = 0; i <25; i++)
			if (a[i] == 2) {
				a[i] = 1 - count;
				bt[i].setText(text[1 - count]);
				if (checkWin() != -1) {
					a[i] = 2;
					bt[i].setText(" ");
					return i;
				}
				a[i] = 2;
				bt[i].setText(" ");
			}
		return -1;
	}
	
	public void addPoint(int k) {	
		if (!win) {
			if (a[k] == 2) {
				a[k] = count;
				bt[k].setForeground(number_cl[count]);
				bt[k].setText(text[count]);
				countH[count]++;
				count = 1 - count;
			}
			if (count == 0)
				luot.setText("Lượt của X");
			else
				luot.setText("Lượt của O");
			if (checkWin() != -1) {
				String mess = "";
				int cw = checkWin();
				if (cw == 2) {
					mess = "Hòa!";
					point1+=point1+5;
					point2+=point2+5;
				}
				else {
					if (icon != text[count]) {
						mess = "Bạn đã thắng!";
						point1 += 10;
					}
					else {
						mess = "Bạn đã thua!";
						point2 += 10;
					}
				}
				win = true;
				JOptionPane.showMessageDialog(null, mess);
			}
		}
	}
	
	public int creatPointRandom() {
		// sử dụng một mảng chứa chỉ số của các điểm chưa được đánh dấu thay
		// 2 đại diện cho ô chưa đc đánh dấu
		//Trong hàm này, ta sử dụng một List<Integer> để lưu các chỉ số của các điểm chưa được đánh dấu.
		//Nếu tất cả các điểm đã được đánh dấu, ta sẽ trả về giá trị 0 để kết thúc trò chơi. 
		//Nếu điểm giữa (chỉ số 12) chưa được đánh dấu, ta sẽ đánh dấu nó.
		// Nếu không, ta sẽ lấy ngẫu nhiên một chỉ số trong danh sách các điểm chưa được đánh dấu và trả về nó.
	    List<Integer> availablePoints = new ArrayList<>();
	    for (int i = 0; i < 25; i++) {
	        if (a[i] == 2) {
	            availablePoints.add(i);
	        }
	    }
	    if(a[12]==2) {
	    	return 12;
	    }
	   
	    if (availablePoints.size() == 0) {
	        return 0;
	    }
	   
	    int randomIndex = (int) (Math.random() * availablePoints.size());
	    return availablePoints.get(randomIndex);
	}
	public void actionPerformed(ActionEvent e) {
		//Hàm actionPerformed được sử dụng để xử lý sự kiện khi người dùng tương tác với giao diện người dùng.
		// Trong trường hợp này, hàm sử lý sự kiện cho các button trên giao diện.
		//Nếu người dùng nhấn nút New Game, một đối tượng GameTicTacToe mới được tạo ra, 
		//và nếu ngẫu nhiên số được tạo ra lớn hơn hoặc bằng 0,5, 
		//thì đối tượng GameTicTacToe sẽ được thiết lập để máy tính đi trước và biểu tượng sử dụng là "O", 
		//ngược lại sẽ là biểu tượng "X".
		//Nếu người dùng chưa thắng và nhấn một trong các ô trên bàn cờ, thì hàm sẽ thực hiện phương thức addPoint() để đánh dấu ô đó, và bắt đầu đếm thời gian với timer.start().
		if (e.getActionCommand() == newGame_bt.getActionCommand()) {
			GameTicTacToe k = new GameTicTacToe(" GameTicTacToe", point1, point2);
			if (Math.random() >= 0.5) {
				k.timer.start();
				k.icon = "O";
			}
			else 
				k.icon = "X";
			this.dispose();
		}
		else if (!win) {
			int k = Integer.parseInt(e.getActionCommand());
			if (a[k] == 2) {
				addPoint(k);
				timer.start();
			}
		}
	}
	
}