package ����ҵ5;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;  //Timer���ڹ����ں�ִ̨�е��ӳ����������������
import java.util.TimerTask;  //���е���������java.util.TimerTask��ʾ

import javax.swing.BorderFactory;//�߿�
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;


public class Exam extends JFrame{
	
	public String op() {
 	   Random r=new Random();
 	   String s;
 	   int i=r.nextInt(10);
 	   System.out.println("i="+i);
 	   if(i>=0&&i<5)
 		   s="+";
 	   else
 		   s="-";
 	   return s;
    }
	
	
   

    private int max = 50;
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int count = 1;

    private JLabel JLgross = new JLabel("��10ҳ");
    private JLabel JLpage = new JLabel();  //ҳ�����
    private JLabel JLtime = new JLabel("����ʱ:");
    private JLabel JLhour = new JLabel();
    private JLabel JLminute = new JLabel();
    private JLabel JLseconds = new JLabel();
    
    

    private JButton JB1 = new JButton("�ύ����");
    private JButton b1 = new JButton("��ҳ");
    private JButton b2 = new JButton("��һҳ");
    private JButton b3 = new JButton("��һҳ");
    private JButton b4 = new JButton("βҳ");

    
    private JLabel jl[] = new JLabel[50]; //50����Ŀ��ǩ
    private JTextField jtf[] = new JTextField[50];//50���ı��򣬴洢��

    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();

    static CardLayout care = new CardLayout();

    int[] answer=new int[max];
    String[] studentAnswer=new String[max];

    private File file = null;
    private FileWriter fw1 = null;

    public long time = 5400;
  
    private void countDown() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
            	
                time--;
                long hour = time / 3600;
                long minute = (time - hour * 3600) / 60;
                long seconds = time - hour * 3600 - minute * 60;

                JLhour.setText(hour + "ʱ");
                JLminute.setText(minute + "��");
                JLseconds.setText(seconds + "��");


                if( time == 0 ) {			
            			 int correctAnswer=0;
            			 for(int i=0,k=0;i<max;i++,k++){

                             studentAnswer[i]=jtf[k].getText().trim();

                             try {
                                 if(Integer.parseInt(studentAnswer[i]) == answer[k]){
                                     //��string�ַ�������ת��Ϊinteger��������
                                     correctAnswer++;
                                 }
                             }
                             catch(NumberFormatException u) {

                             }
                         }

                         int score = 100*(int)correctAnswer/max;

                         setVisible(false);
                         dispose();
                         String a =null;
                         if(score>=90) {
                         	a = "�ɼ�����";
                         }
                         else if(score>=60&&score<90) {
                         	a = "�ɼ�����";
                         }
                         else if(score<60) {
                         	a = "�ɼ�������";
                         }
                         String s="��50����\n";
                         s=s+"���"+correctAnswer+"����\n";
                         s=s+"���"+(max-correctAnswer)+"����\n";
                         s=s+"�ɼ�"+String.format("%d",score)+"��\n";
                         s=s+a;
                     

                         file = new File("��Ϣ�ռ�.txt");
                         if(!file.exists()){
                             try {
                                 file.createNewFile();
                                 
                             }catch (IOException u1) {
                                 u1.printStackTrace();
                             }
                         }  	
                    
                         try {
                             fw1 = new FileWriter(file, true);

                             fw1.write("����    " + login.s1 + "    רҵ    " +  login.s2
                                     + "    �༶    " +  login.s3 + "    ���Ե÷֣�" + score + "\n");

                             fw1.close();

                         }catch(IOException u2) {
                             u2.printStackTrace();
                         }

                         JOptionPane.showMessageDialog(null, s,"���Խ���",JOptionPane.ERROR_MESSAGE);
            		
                }
            }
        }, 0, 1000);//��Ҫ��ʱһ��֮���ظ����з���
    }


    private void Event() {

        //�ύ�Ծ�ť
        JB1.addActionListener(new ActionListener() {
           
            private Object time;

			public void actionPerformed(ActionEvent e) {

                int correctAnswer=0;
                int l=JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ�Ͻ���", "��ʾ", JOptionPane.YES_NO_OPTION);//���ý���ť�󵯳��Ĵ��ڣ�lΪ����ǻ��ť�󷵻ص�intֵ
    			if(l==JOptionPane.YES_OPTION) {
    				
    			
                for(int i=0,k=0;i<max;i++,k++){

                    studentAnswer[i]=jtf[k].getText().trim();

                    try {
                        if(Integer.parseInt(studentAnswer[i]) == answer[k]){
                            //��string�ַ�������ת��Ϊinteger��������
                            correctAnswer++;
                        }
                    }catch(NumberFormatException u) {

                    }
                }

                int score = 100*(int)correctAnswer/max;

                setVisible(false);
                dispose();
                String a =null;
                if(score>=90) {
                	a = "�ɼ�����";
                }
                else if(score>=60&&score<90) {
                	a = "�ɼ�����";
                }
                else if(score<60) {
                	a = "�ɼ�������";
                }
                String s="��50����\n";
                s=s+"���"+correctAnswer+"����\n";
                s=s+"���"+(max-correctAnswer)+"����\n";
                s=s+"�ɼ�"+String.format("%d",score)+"��\n";
                s=s+a;
            

                file = new File("��Ϣ�ռ�.txt");
                if(!file.exists()){
                    try {
                        file.createNewFile();
                        
                    }catch (IOException u1) {
                        u1.printStackTrace();
                    }
                }  	
           
                

                try {
                    fw1 = new FileWriter(file, true);

                    fw1.write("����    " + login.s1 + "    רҵ    " +  login.s2
                            + "    �༶    " +  login.s3 + "    ���Ե÷֣�" + score + "\n");

                    fw1.close();

                }catch(IOException u2) {
                    u2.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, s,"���Խ���",JOptionPane.ERROR_MESSAGE);

            }
		}
				
           
    			
        });

        //��ҳ
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                care.first(p3);
                count = 1;
                JLpage.setText("��" + count + "ҳ");
               
            }
        });

        //ǰһҳ
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                care.previous(p3);
                if ( count > 1 && count <=10) {
                    count --;
                }else {
                    count = 10 ;
                }
                JLpage.setText("��" + count + "ҳ");
            }
        });

        //��һҳ
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                care.next(p3);
                if ( count >= 1 && count < 10 ) {
                    count ++;
                }else {
                    count = 1 ;
                }
                JLpage.setText("��" + count + "ҳ");
               
            }
        });

        //βҳ
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                care.last(p3);
                count = 10;
                JLpage.setText("��" + count + "ҳ");
            }
        });

    }


    public Exam() {

        this.setTitle("��ʼ����");
        this.setSize(550, 400);
        this.setLocationRelativeTo(null);  //���˴���������Ļ������
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(20,20)); //���� �������ˮƽ��ֱ���Ϊ20����
       
        countDown();

        p1.add(JLhour);
        p1.add(JLminute);
        p1.add(JLseconds);
        p1.setBackground(Color.red);//����ʱ���
        b1.setFont(new Font("����",Font.BOLD,15));
        b2.setFont(new Font("����",Font.BOLD,15));
        b3.setFont(new Font("����",Font.BOLD,15));
        b4.setFont(new Font("����",Font.BOLD,15));
        JLhour.setFont(new Font("����",Font.BOLD,15));
        JLminute.setFont(new Font("����",Font.BOLD,15));
        JLseconds.setFont(new Font("����",Font.BOLD,15));
        JLpage.setFont(new Font("����",Font.BOLD,15));
        JLgross.setFont(new Font("����",Font.BOLD,15));
        JB1.setFont(new Font("����",Font.BOLD,15));
        JLtime.setFont(new Font("����",Font.BOLD,15));
        JLpage.setText("��" + count + "ҳ");
        
        
        p2.setLayout(new FlowLayout());
        p2.add(JLgross);
        p2.add(JLpage);//ҳ��
        p2.add(JLtime);
        p2.add(p1);
        p2.add(JB1);
        p2.setBackground(Color.yellow);
       
        this.add(p2,BorderLayout.NORTH);
        
        p3.setLayout(care);
        this.add(p3,BorderLayout.CENTER);
        p3.setBorder(BorderFactory.createLoweredBevelBorder()); //�߿�
        p3.setBackground(Color.red);//����ҳ��߿���ɫ
        JB1.setBackground(Color.red);
       
        Random r1=new Random();
        Random r2=new Random();
        int i1,i2;
        i1=r1.nextInt(10);
        i2=r2.nextInt(10);
        String s1,s2;
        if(i1>=0&&i2<5) 
        	s1="+";
        else
        	s1="-";
        
        
        for(int i = 0 , k = 0; i < 10 ; i++ ) {

            JPanel p = new JPanel();
            p.setLayout(new GridLayout(5,2,20,20)); //���񲼾�5��2�У�ˮƽ��ֱ��඼��Ϊ20����

            for(int j = 0 ; j < 5 ; j++) {

                a=(int)(Math.random()*100+1);
                b=(int)(Math.random()*100+1);
                c=(int)(Math.random()*100+1);
                
                	String s;
                	s1=op();
                	s2=op();
                	int sum;
                	if(s1.equals("+")) {
                		sum=a+b;
                	}
                	else {
                		sum=a-b;
                	}
                	if(s2.equals("+")) {
                		sum+=c;
                	}
                	else {
                		sum-=c;
                	}
                	
                answer[k] = sum;
                
                if(sum<0) {
                	j--;
                	continue;
                }
                jl[k]=new JLabel(a+s1+b+s2+c+"=");
                jl[k].setFont(new Font("����",Font.BOLD,20));
                p.add(jl[k]);
                 jtf[k] = new JTextField(6);
                 p.add(jtf[k]);
                 k++;
              
            }

            p3.add(p);
        }
       
        p4.setLayout(new GridLayout(1,4));

        p4.add(b1);
        p4.add(b2);
        p4.add(b3);
        p4.add(b4);
        
        this.add(p4, BorderLayout.SOUTH);

       

        Event();

        setVisible(true);

    }


}
