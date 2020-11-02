import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class Start extends JFrame 
{
	JLabel label;
	JTextField text;
	JButton btn;
	JPanel panel,labelpanel, textpanel,btnpanel; 
	
	public Start()
	{
		setTitle("시작");
		setSize(300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel(new GridLayout(3,0));
		labelpanel = new JPanel();
		textpanel = new JPanel();
		btnpanel = new JPanel();
		
		label = new JLabel("실행할 게임 숫자 입력");
		labelpanel.add(label);
		
		
		text = new JTextField(10);
		textpanel.add(text);
		
		btn = new JButton("입력");
		btnpanel.add(btn);
		
		panel.add(labelpanel);
		panel.add(textpanel);
		panel.add(btnpanel);
		add(panel);
		
		btn.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == btn)
				{
					new Select(Integer.valueOf(text.getText()));
					setVisible(false);
				}
			}
		});
		setVisible(true);
	}
}

//입력받은 수 만큼 패널 추가하기
class Select extends JFrame implements ActionListener
{
	int count;
	AddPanel gamepanel;
	JPanel southpanel;
	JButton startbtn, resetbtn, backbtn;
	List<AddPanel> gamelist;
	
	public Select(int a)
	{
		count = a;
		setTitle("LottoGame");
		gamelist = new ArrayList<>();
		JPanel backpanel = new JPanel(new GridLayout(0,1));
		startbtn = new JButton("Start");
		resetbtn = new JButton("Reset");
		backbtn = new JButton("Back");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		southpanel = new JPanel();
		
		southpanel.add(startbtn);
		southpanel.add(resetbtn);
		southpanel.add(backbtn);
		startbtn.addActionListener(this);
		resetbtn.addActionListener(this);
		backbtn.addActionListener(this);
		
		
		//입력받은 수만큼 게임 출력
		for(int i = 1; i <= count; i++)
		{
			gamepanel = new AddPanel(i);
			gamelist.add(gamepanel);
			backpanel.add(gamepanel);
		}
		backpanel.add(southpanel);
		add(backpanel);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//결과창 띄우기
		if(e.getSource() == startbtn)
		{
			ResultGame();
		}
		//입력받은 수 만큼 패널의 텍스트 필드 비우기
		if(e.getSource() == resetbtn)
		{
			for(int i = 0; i < gamelist.size(); i++)
			{			
				for(int j = 0; j < gamepanel.numlist.size(); j++)
				{
					gamelist.get(i).numlist.get(j).setText("");
				}
			
			}
		}
		//초기화면
		if(e.getSource() == backbtn)
		{
			setVisible(false);
			new Start();
		}
	}
	
	//결과창
	public void ResultGame()
	{
		JFrame result = new JFrame();
		
		//바탕패널
		JPanel backpanel = new JPanel(new GridLayout(0,1));
		
		//당첨번호 출력 패널
		JPanel resultpanel = new JPanel();
		
		
	
		JPanel resultnum = new JPanel();
		
		resultnum.setBorder(BorderFactory.createTitledBorder("당첨번호"));
		
		JPanel bouns = new JPanel();
		
		bouns.setBorder(BorderFactory.createTitledBorder("보너스"));

		JPanel bonun2 = new JPanel();
		
		JLabel bonusnum = new JLabel();
		bonusnum.setOpaque(true);
		bonusnum.setPreferredSize(new Dimension(70, 50));
		bonusnum.setHorizontalAlignment(JLabel.CENTER);
		bonusnum.setFont(new Font("DIALOG",Font.BOLD,35));
		bonusnum.setBackground(Color.GREEN);

		//당첨번호
		List<Integer> list = new ArrayList<Integer>();
		
			for(int i = 0; i < 6; i++)	
			{
				Random rd = new Random();
				int a = rd.nextInt(45) + 1;
				//포함되있으면 한번 더 시키기
				if(list.contains(a))
				{
					i = i - 1;
				}
				else
				{
					list.add(i, a);
				}
			}
			Collections.sort(list);
			
			//보너스 번호 추출기
			Random rd = new Random();
			int b = rd.nextInt(45) + 1;
			
			while(list.contains(b))
			{
				b = rd.nextInt(45) + 1;
				continue;
			}
			list.add(6, b);
			
			//값 넣기
			for(int i = 0; i < 6; i++)
			{
				JLabel resnum = new JLabel();
				resnum.setText("" + list.get(i));
				resnum.setOpaque(true);
				resnum.setPreferredSize(new Dimension(70, 50));
				resnum.setFont(new Font("DIALOG",Font.BOLD,35));
				resnum.setHorizontalAlignment(JLabel.CENTER);
				resnum.setBackground(Color.LIGHT_GRAY);
				
				resultnum.add(resnum);
			}
			
			bonusnum.setText("" + list.get(6));

			bouns.add(bonusnum);
			bonun2.add(bouns);
			
			resultpanel.add(resultnum);
			resultpanel.add(bonun2);
			
			//당첨번호 출력
			backpanel.add(resultpanel);
				
		for(int i = 0; i < count; i++)
		{
			//선택번호, 일치번호 출력 패널
			JPanel winpanel = new JPanel();
			JLabel selnum = new JLabel("선택 번호 : ");
			
			JLabel samenum = new JLabel("일치 번호 : ");
			
			JPanel ee = new JPanel(new GridLayout(2,0));
			
			//패널위의 게임 명
			winpanel.setBorder(BorderFactory.createTitledBorder((i+1) +"번째 게임"));

			//선택숫자 출력패널
			JPanel selectnums = new JPanel();
			selectnums.add(selnum);
			selectnums.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
			
			//가져온 번호
			List<Integer> samelist = new ArrayList<>();
			
			for(int j = 0; j < 6; j++)
			{
				String aa = gamelist.get(i).numlist.get(j).getText();
				samelist.add(Integer.valueOf(aa));
				
				JLabel ssnn = new JLabel(aa);
				ssnn.setFont(new Font("DIALOG",Font.BOLD,22));
				ssnn.setHorizontalAlignment(JLabel.CENTER);
				ssnn.setPreferredSize(new Dimension(40, 30));
				ssnn.setBackground(Color.WHITE);
				
				selectnums.add(ssnn);
			}
			
			//일치 번호 출력
			JPanel samenums = new JPanel();
			samenums.add(samenum);
			samenums.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
			
			// list -> 당첨번호 리스트
			// samelist -> 가져온번호 리스트
			// winlist -> 일치번호 가져오기
			
			List<Integer> winlist = new ArrayList<>(); 
			
			for(int k = 0; k < 6; k++)
			{
				String aa = gamelist.get(i).numlist.get(k).getText();
			
				if(list.contains(samelist.get(k)))
				{
					winlist.add(samelist.get(k));
				}
			}
				
			//일치번호 출력
			if(winlist.isEmpty())
			{
				JLabel qwe = new JLabel("당첨번호 없음");
				qwe.setFont(new Font("DIALOG",Font.BOLD,20));
				samenums.add(qwe);
			}
			else
			{
				for(int x = 0; x < winlist.size(); x++)
				{
					
						JLabel qwe = new JLabel(""+winlist.get(x));
						qwe.setOpaque(true);
						qwe.setFont(new Font("DIALOG",Font.BOLD,22));
						qwe.setHorizontalAlignment(JLabel.CENTER);
						qwe.setPreferredSize(new Dimension(40, 30));
						qwe.setBackground(Color.YELLOW);
						samenums.add(qwe);
				}
			}
			
			JPanel winsum = new JPanel();
			JLabel winsumlabel = new JLabel();
			winsumlabel.setOpaque(true);
			winsumlabel.setFont(new Font("DIALOG",Font.BOLD,35));
			winsumlabel.setHorizontalAlignment(JLabel.CENTER);
			winsumlabel.setPreferredSize(new Dimension(70, 50));
			winsumlabel.setBackground(Color.WHITE);
			winsum.add(winsumlabel);
			
			//등수 조건
			switch(winlist.size())
			{
			//꽝
			case 0 :
				winsumlabel.setText("꽝");
				break;
			//꽝
			case 1 :
				winsumlabel.setText("꽝");
				break;
			//꽝
			case 2 :
				winsumlabel.setText("꽝");
				break;
			//5등	
			case 3 :
				winsumlabel.setText("5등");
				break;
			//4등
			case 4 :
				winsumlabel.setText("4등");
				break;
			//3등
			case 5 :
				winsumlabel.setText("3등");
				break;
			//2등 or 1등
			case 6 :
				//2등
				if(winlist.contains(list.get(6)))
				{
					winsumlabel.setText("2등");
				}
				//1등
				else
				{
					winsumlabel.setText("1등");
				}
				break;
			}
			
			ee.add(selectnums);
			ee.add(winsum);
			ee.add(samenums);
			
			winpanel.add(ee);
			//일치번호
			backpanel.add(winpanel);
		}	
		result.add(backpanel);
		result.pack();
		result.setVisible(true);
	}
}

//패널 추가 클래스
class AddPanel extends JPanel 
{
//로또 숫자 출력 텍스트 필드
JLabel number;

//로또 숫자를 담을 리스트
List<JLabel> numlist;

JRadioButton auto ;
JRadioButton manual;

List<Integer> numm;

//체크박스 리스트
List<JCheckBox> che;

int count;

	public AddPanel(int countpanel)
	{
		//게임마다의 패널
		JPanel gamepanel = new JPanel();
		
		//숫자받는 리스트
		numm = new ArrayList<>();
		che = new ArrayList<>();
		
		//패널위의 게임 명
		gamepanel.setBorder(BorderFactory.createTitledBorder(countpanel + "번째 게임"));
		
		//라디오버튼그룹 패널
		JPanel radio = new JPanel();
		ButtonGroup btng = new ButtonGroup();
		
		auto = new JRadioButton("자동");
		manual = new JRadioButton("수동");
		btng.add(auto);
		btng.add(manual);
		radio.add(auto);
		radio.add(manual);
		
		//버튼 패널
		JPanel extrapanel = new JPanel();
		JButton extrabtn = new JButton("추출");
		extrapanel.add(extrabtn);
		
		//숫자 출력패널
		JPanel nums = new JPanel();
		nums.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
		
		//패널 텍스트박스 담는 리스트
		numlist = new ArrayList<>();
		
		//패널 텍스트박스
		for(int i = 0; i < 6; i++)
		{
			number = new JLabel();
			number.setOpaque(true);
			number.setFont(new Font("DIALOG",Font.BOLD,15));
			number.setHorizontalAlignment(JLabel.CENTER);
			number.setPreferredSize(new Dimension(30, 20));
			number.setBackground(Color.WHITE);
			numlist.add(number);
			nums.add(number);
			numlist.get(i).setName("num"+ i);
		}
		
		gamepanel.add(radio);
		gamepanel.add(extrapanel);
		gamepanel.add(nums);
		add(gamepanel);
		
		//라디오버튼 확인 후 추출버튼
		extrabtn.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand() == "추출")
				{
					if(auto.isSelected())
					{
						numm.clear();
						randomNum();
					}
					if(manual.isSelected())
					{
						numm.clear();
						manuNum();
					}
				}
			}
		});
	}

	//수동일때
	public void manuNum()
	{
		JFrame manu = new JFrame();
		che.clear();
		count = 0;

		manu.setTitle("수동찍기");
		
		JPanel back = new JPanel(new BorderLayout());
		JPanel center = new JPanel(new GridLayout(6 , 8 ,3 , 3));
		JPanel bottom = new JPanel();
		JButton btn = new JButton("확인");
		JButton rebtn = new JButton("리셋");
		
		for(int i = 0; i < 45; i++)
		{
			manu(i + 1);
			center.add(che.get(i));
		}
		
		bottom.add(btn);
		bottom.add(rebtn);
		back.add(center , BorderLayout.CENTER);
		back.add(bottom, BorderLayout.SOUTH);
		manu.add(back);
		manu.pack();
		manu.setVisible(true);
		manu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//수동 확인 버튼
		btn.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == btn)
				{
					for(int i = 0; i < 6; i++)
					{
						Collections.sort(numm);
						numlist.get(i).setText("" + numm.get(i));
					}
					manu.dispose();
				}
			}
		});
		
		//수동 리셋버튼
		rebtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == rebtn)
				{
					NonEnable();
				}
			}
		});
	}
	
	//수동 체크박스 숫자 만드는 메소드
	public JCheckBox manu(int aa)
	{
		JCheckBox chbox = new JCheckBox("" + aa); 
		chbox.setName("" + aa);
		che.add(chbox);
		chbox.addItemListener(new ItemListener()
		{	
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					count++;
					numm.add(Integer.valueOf(chbox.getText()));
					Enable();
				}
				if(e.getStateChange() == ItemEvent.DESELECTED)
				{
					count--;
					numm.remove(Integer.valueOf(chbox.getText()));
				}
			}
		});
		return chbox;
	}
	
	//6개 체크시 비활성화
	public void Enable()
	{
		if(count == 6)
		{
			for(int i = 0; i < 45; i++)
			{
				che.get(i).setEnabled(false);
			}
		}
	}
	
	//체크박스 활성화(리셋버튼)
	public void NonEnable()
	{
		for(int i = 0; i < 45; i++)
		{
			che.get(i).setEnabled(true);
			che.get(i).setSelected(false);
		}
		
	}

	public void randomNum()
	{
		//번호 담을 리스트
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < 6; i++)	
		{
			Random rd = new Random();
			int a = rd.nextInt(45) + 1;
			//포함되있으면 한번 더 시키기
			if(list.contains(a))
			{
				i = i - 1;
			}
			else
			{
				list.add(i, a);
			}
		}
		Collections.sort(list);
		 
		//값 넣기
		for(int i = 0; i < 6; i++)
		{
			int s = list.get(i);
			//텍스트필드에 값 넣기
			numlist.get(i).setText(""+ s);
		}
	}	
}

public class LottoTest
{
	public static void main(String[] args) 
	{
		new Start();
	}
}