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
		setTitle("����");
		setSize(300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel(new GridLayout(3,0));
		labelpanel = new JPanel();
		textpanel = new JPanel();
		btnpanel = new JPanel();
		
		label = new JLabel("������ ���� ���� �Է�");
		labelpanel.add(label);
		
		
		text = new JTextField(10);
		textpanel.add(text);
		
		btn = new JButton("�Է�");
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

//�Է¹��� �� ��ŭ �г� �߰��ϱ�
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
		
		
		//�Է¹��� ����ŭ ���� ���
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
		//���â ����
		if(e.getSource() == startbtn)
		{
			ResultGame();
		}
		//�Է¹��� �� ��ŭ �г��� �ؽ�Ʈ �ʵ� ����
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
		//�ʱ�ȭ��
		if(e.getSource() == backbtn)
		{
			setVisible(false);
			new Start();
		}
	}
	
	//���â
	public void ResultGame()
	{
		JFrame result = new JFrame();
		
		//�����г�
		JPanel backpanel = new JPanel(new GridLayout(0,1));
		
		//��÷��ȣ ��� �г�
		JPanel resultpanel = new JPanel();
		
		
	
		JPanel resultnum = new JPanel();
		
		resultnum.setBorder(BorderFactory.createTitledBorder("��÷��ȣ"));
		
		JPanel bouns = new JPanel();
		
		bouns.setBorder(BorderFactory.createTitledBorder("���ʽ�"));

		JPanel bonun2 = new JPanel();
		
		JLabel bonusnum = new JLabel();
		bonusnum.setOpaque(true);
		bonusnum.setPreferredSize(new Dimension(70, 50));
		bonusnum.setHorizontalAlignment(JLabel.CENTER);
		bonusnum.setFont(new Font("DIALOG",Font.BOLD,35));
		bonusnum.setBackground(Color.GREEN);

		//��÷��ȣ
		List<Integer> list = new ArrayList<Integer>();
		
			for(int i = 0; i < 6; i++)	
			{
				Random rd = new Random();
				int a = rd.nextInt(45) + 1;
				//���Ե������� �ѹ� �� ��Ű��
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
			
			//���ʽ� ��ȣ �����
			Random rd = new Random();
			int b = rd.nextInt(45) + 1;
			
			while(list.contains(b))
			{
				b = rd.nextInt(45) + 1;
				continue;
			}
			list.add(6, b);
			
			//�� �ֱ�
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
			
			//��÷��ȣ ���
			backpanel.add(resultpanel);
				
		for(int i = 0; i < count; i++)
		{
			//���ù�ȣ, ��ġ��ȣ ��� �г�
			JPanel winpanel = new JPanel();
			JLabel selnum = new JLabel("���� ��ȣ : ");
			
			JLabel samenum = new JLabel("��ġ ��ȣ : ");
			
			JPanel ee = new JPanel(new GridLayout(2,0));
			
			//�г����� ���� ��
			winpanel.setBorder(BorderFactory.createTitledBorder((i+1) +"��° ����"));

			//���ü��� ����г�
			JPanel selectnums = new JPanel();
			selectnums.add(selnum);
			selectnums.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
			
			//������ ��ȣ
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
			
			//��ġ ��ȣ ���
			JPanel samenums = new JPanel();
			samenums.add(samenum);
			samenums.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
			
			// list -> ��÷��ȣ ����Ʈ
			// samelist -> �����¹�ȣ ����Ʈ
			// winlist -> ��ġ��ȣ ��������
			
			List<Integer> winlist = new ArrayList<>(); 
			
			for(int k = 0; k < 6; k++)
			{
				String aa = gamelist.get(i).numlist.get(k).getText();
			
				if(list.contains(samelist.get(k)))
				{
					winlist.add(samelist.get(k));
				}
			}
				
			//��ġ��ȣ ���
			if(winlist.isEmpty())
			{
				JLabel qwe = new JLabel("��÷��ȣ ����");
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
			
			//��� ����
			switch(winlist.size())
			{
			//��
			case 0 :
				winsumlabel.setText("��");
				break;
			//��
			case 1 :
				winsumlabel.setText("��");
				break;
			//��
			case 2 :
				winsumlabel.setText("��");
				break;
			//5��	
			case 3 :
				winsumlabel.setText("5��");
				break;
			//4��
			case 4 :
				winsumlabel.setText("4��");
				break;
			//3��
			case 5 :
				winsumlabel.setText("3��");
				break;
			//2�� or 1��
			case 6 :
				//2��
				if(winlist.contains(list.get(6)))
				{
					winsumlabel.setText("2��");
				}
				//1��
				else
				{
					winsumlabel.setText("1��");
				}
				break;
			}
			
			ee.add(selectnums);
			ee.add(winsum);
			ee.add(samenums);
			
			winpanel.add(ee);
			//��ġ��ȣ
			backpanel.add(winpanel);
		}	
		result.add(backpanel);
		result.pack();
		result.setVisible(true);
	}
}

//�г� �߰� Ŭ����
class AddPanel extends JPanel 
{
//�ζ� ���� ��� �ؽ�Ʈ �ʵ�
JLabel number;

//�ζ� ���ڸ� ���� ����Ʈ
List<JLabel> numlist;

JRadioButton auto ;
JRadioButton manual;

List<Integer> numm;

//üũ�ڽ� ����Ʈ
List<JCheckBox> che;

int count;

	public AddPanel(int countpanel)
	{
		//���Ӹ����� �г�
		JPanel gamepanel = new JPanel();
		
		//���ڹ޴� ����Ʈ
		numm = new ArrayList<>();
		che = new ArrayList<>();
		
		//�г����� ���� ��
		gamepanel.setBorder(BorderFactory.createTitledBorder(countpanel + "��° ����"));
		
		//������ư�׷� �г�
		JPanel radio = new JPanel();
		ButtonGroup btng = new ButtonGroup();
		
		auto = new JRadioButton("�ڵ�");
		manual = new JRadioButton("����");
		btng.add(auto);
		btng.add(manual);
		radio.add(auto);
		radio.add(manual);
		
		//��ư �г�
		JPanel extrapanel = new JPanel();
		JButton extrabtn = new JButton("����");
		extrapanel.add(extrabtn);
		
		//���� ����г�
		JPanel nums = new JPanel();
		nums.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
		
		//�г� �ؽ�Ʈ�ڽ� ��� ����Ʈ
		numlist = new ArrayList<>();
		
		//�г� �ؽ�Ʈ�ڽ�
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
		
		//������ư Ȯ�� �� �����ư
		extrabtn.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand() == "����")
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

	//�����϶�
	public void manuNum()
	{
		JFrame manu = new JFrame();
		che.clear();
		count = 0;

		manu.setTitle("�������");
		
		JPanel back = new JPanel(new BorderLayout());
		JPanel center = new JPanel(new GridLayout(6 , 8 ,3 , 3));
		JPanel bottom = new JPanel();
		JButton btn = new JButton("Ȯ��");
		JButton rebtn = new JButton("����");
		
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
		
		//���� Ȯ�� ��ư
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
		
		//���� ���¹�ư
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
	
	//���� üũ�ڽ� ���� ����� �޼ҵ�
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
	
	//6�� üũ�� ��Ȱ��ȭ
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
	
	//üũ�ڽ� Ȱ��ȭ(���¹�ư)
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
		//��ȣ ���� ����Ʈ
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < 6; i++)	
		{
			Random rd = new Random();
			int a = rd.nextInt(45) + 1;
			//���Ե������� �ѹ� �� ��Ű��
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
		 
		//�� �ֱ�
		for(int i = 0; i < 6; i++)
		{
			int s = list.get(i);
			//�ؽ�Ʈ�ʵ忡 �� �ֱ�
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