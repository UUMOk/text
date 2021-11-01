package 大作业5;
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
import java.util.Timer;  //Timer用于管理在后台执行的延迟任务或周期性任务
import java.util.TimerTask;  //其中的任务是用java.util.TimerTask表示

import javax.swing.BorderFactory;//边框
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

    private JLabel JLgross = new JLabel("共10页");
    private JLabel JLpage = new JLabel();  //页码控制
    private JLabel JLtime = new JLabel("倒计时:");
    private JLabel JLhour = new JLabel();
    private JLabel JLminute = new JLabel();
    private JLabel JLseconds = new JLabel();
    
    

    private JButton JB1 = new JButton("提交考卷");
    private JButton b1 = new JButton("首页");
    private JButton b2 = new JButton("上一页");
    private JButton b3 = new JButton("下一页");
    private JButton b4 = new JButton("尾页");

    
    private JLabel jl[] = new JLabel[50]; //50个题目标签
    private JTextField jtf[] = new JTextField[50];//50个文本框，存储答案

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

                JLhour.setText(hour + "时");
                JLminute.setText(minute + "分");
                JLseconds.setText(seconds + "秒");


                if( time == 0 ) {			
            			 int correctAnswer=0;
            			 for(int i=0,k=0;i<max;i++,k++){

                             studentAnswer[i]=jtf[k].getText().trim();

                             try {
                                 if(Integer.parseInt(studentAnswer[i]) == answer[k]){
                                     //将string字符串类型转换为integer整数类型
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
                         	a = "成绩优秀";
                         }
                         else if(score>=60&&score<90) {
                         	a = "成绩及格";
                         }
                         else if(score<60) {
                         	a = "成绩不及格";
                         }
                         String s="共50道题\n";
                         s=s+"答对"+correctAnswer+"道题\n";
                         s=s+"答错"+(max-correctAnswer)+"道题\n";
                         s=s+"成绩"+String.format("%d",score)+"分\n";
                         s=s+a;
                     

                         file = new File("信息收集.txt");
                         if(!file.exists()){
                             try {
                                 file.createNewFile();
                                 
                             }catch (IOException u1) {
                                 u1.printStackTrace();
                             }
                         }  	
                    
                         try {
                             fw1 = new FileWriter(file, true);

                             fw1.write("姓名    " + login.s1 + "    专业    " +  login.s2
                                     + "    班级    " +  login.s3 + "    考试得分：" + score + "\n");

                             fw1.close();

                         }catch(IOException u2) {
                             u2.printStackTrace();
                         }

                         JOptionPane.showMessageDialog(null, s,"考试结束",JOptionPane.ERROR_MESSAGE);
            		
                }
            }
        }, 0, 1000);//不要延时一秒之后重复运行方法
    }


    private void Event() {

        //提交试卷按钮
        JB1.addActionListener(new ActionListener() {
           
            private Object time;

			public void actionPerformed(ActionEvent e) {

                int correctAnswer=0;
                int l=JOptionPane.showConfirmDialog(null, "是否确认交卷", "提示", JOptionPane.YES_NO_OPTION);//设置交卷按钮后弹出的窗口，l为点击是或否按钮后返回的int值
    			if(l==JOptionPane.YES_OPTION) {
    				
    			
                for(int i=0,k=0;i<max;i++,k++){

                    studentAnswer[i]=jtf[k].getText().trim();

                    try {
                        if(Integer.parseInt(studentAnswer[i]) == answer[k]){
                            //将string字符串类型转换为integer整数类型
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
                	a = "成绩优秀";
                }
                else if(score>=60&&score<90) {
                	a = "成绩及格";
                }
                else if(score<60) {
                	a = "成绩不及格";
                }
                String s="共50道题\n";
                s=s+"答对"+correctAnswer+"道题\n";
                s=s+"答错"+(max-correctAnswer)+"道题\n";
                s=s+"成绩"+String.format("%d",score)+"分\n";
                s=s+a;
            

                file = new File("信息收集.txt");
                if(!file.exists()){
                    try {
                        file.createNewFile();
                        
                    }catch (IOException u1) {
                        u1.printStackTrace();
                    }
                }  	
           
                

                try {
                    fw1 = new FileWriter(file, true);

                    fw1.write("姓名    " + login.s1 + "    专业    " +  login.s2
                            + "    班级    " +  login.s3 + "    考试得分：" + score + "\n");

                    fw1.close();

                }catch(IOException u2) {
                    u2.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, s,"考试结束",JOptionPane.ERROR_MESSAGE);

            }
		}
				
           
    			
        });

        //首页
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                care.first(p3);
                count = 1;
                JLpage.setText("第" + count + "页");
               
            }
        });

        //前一页
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                care.previous(p3);
                if ( count > 1 && count <=10) {
                    count --;
                }else {
                    count = 10 ;
                }
                JLpage.setText("第" + count + "页");
            }
        });

        //下一页
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                care.next(p3);
                if ( count >= 1 && count < 10 ) {
                    count ++;
                }else {
                    count = 1 ;
                }
                JLpage.setText("第" + count + "页");
               
            }
        });

        //尾页
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                care.last(p3);
                count = 10;
                JLpage.setText("第" + count + "页");
            }
        });

    }


    public Exam() {

        this.setTitle("开始考试");
        this.setSize(550, 400);
        this.setLocationRelativeTo(null);  //将此窗口置于屏幕的中央
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(20,20)); //设置 各组件间水平垂直间隔为20像素
       
        countDown();

        p1.add(JLhour);
        p1.add(JLminute);
        p1.add(JLseconds);
        p1.setBackground(Color.red);//倒计时变红
        b1.setFont(new Font("黑体",Font.BOLD,15));
        b2.setFont(new Font("黑体",Font.BOLD,15));
        b3.setFont(new Font("黑体",Font.BOLD,15));
        b4.setFont(new Font("黑体",Font.BOLD,15));
        JLhour.setFont(new Font("黑体",Font.BOLD,15));
        JLminute.setFont(new Font("黑体",Font.BOLD,15));
        JLseconds.setFont(new Font("黑体",Font.BOLD,15));
        JLpage.setFont(new Font("黑体",Font.BOLD,15));
        JLgross.setFont(new Font("黑体",Font.BOLD,15));
        JB1.setFont(new Font("黑体",Font.BOLD,15));
        JLtime.setFont(new Font("黑体",Font.BOLD,15));
        JLpage.setText("第" + count + "页");
        
        
        p2.setLayout(new FlowLayout());
        p2.add(JLgross);
        p2.add(JLpage);//页数
        p2.add(JLtime);
        p2.add(p1);
        p2.add(JB1);
        p2.setBackground(Color.yellow);
       
        this.add(p2,BorderLayout.NORTH);
        
        p3.setLayout(care);
        this.add(p3,BorderLayout.CENTER);
        p3.setBorder(BorderFactory.createLoweredBevelBorder()); //边框
        p3.setBackground(Color.red);//考试页面边框颜色
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
            p.setLayout(new GridLayout(5,2,20,20)); //网格布局5行2列，水平垂直间距都设为20像素

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
                jl[k].setFont(new Font("黑体",Font.BOLD,20));
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
