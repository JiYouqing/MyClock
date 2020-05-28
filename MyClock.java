package FinalAssignment;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class MyClock implements Runnable {

    Thread clock;
    private String time_now = "";
    private String date_now = "";
    private String time_now2 = "";
    private JFrame frame1 = new JFrame("九条鲸鱼钟");
    private JButton b1 = new JButton("设置闹钟");
    private JButton bclock1 = new JButton("关闭闹钟1");
    private JButton bclock2 = new JButton("关闭闹钟2");
    private JButton bclock3 = new JButton("关闭闹钟3");
    private JButton bset = new JButton("查看设置状态");
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel setlabel1 = new JLabel();
    private JLabel setlabel2 = new JLabel();
    private JLabel setlabel3 = new JLabel();
    private String[] alarm = {"未设置闹钟1", "未设置闹钟2", "未设置闹钟3"};//闹钟数目(最多三个）
    private int[] flag = {0, 0, 0};
    private int Flag = 0;

    //初始化
    public void Init() {
        frame1.setSize(400, 300);
        frame1.setLayout(null);
        //按钮大小和位置
        b1.setBounds(270, 50, 110, 25);
        bclock1.setBounds(270, 160, 110, 25);
        bclock2.setBounds(270, 190, 110, 25);
        bclock3.setBounds(270, 220, 110, 25);


        //添加按钮到主窗体
        frame1.add(b1);
        frame1.add(bclock1);
        frame1.add(bclock2);
        frame1.add(bclock3);
        frame1.add(bset);

        //关闭窗口时退出程序
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //让窗口可见
        frame1.setVisible(true);


    }
    //主函数(入口)
    public static void main(String[] arg) {
        new MyClock();

    }
    //start()
    public void start() { //开始
        if (clock == null) {
            clock = new Thread(this); //实例化进程
            clock.start(); //开始进程
        }
    }
    public void run() {
        while (clock != null) {
            get();

            //label
            label1.setText(time_now);
            label2.setText(date_now);
            label1.setBounds(70, 50, 300, 100);
            frame1.add(label1);
            label1.setForeground(Color.PINK);
            label1.setFont(new Font("楷体", Font.PLAIN, 40));
            label2.setBounds(100, 100, 300, 100);
            frame1.add(label2);
            label2.setForeground(Color.PINK);
            label2.setFont(new Font("楷体", Font.PLAIN, 15));
            //查看闹钟1的设置
            if(flag[0]==0) setlabel1.setText("闹钟1:"+alarm[0]+"：关");
            if(flag[0]==1) setlabel1.setText("闹钟1:"+alarm[0]+"：开");
            setlabel1.setBounds(70, 160, 110, 20);
            frame1.add(setlabel1);
            setlabel1.setForeground(Color.GRAY);
            setlabel1.setFont(new Font("楷体", Font.PLAIN, 10));
            //查看闹钟2的设置
            if(flag[1]==0) setlabel2.setText("闹钟2:"+alarm[1]+"：关");
            if(flag[1]==1) setlabel2.setText("闹钟2:"+alarm[1]+"：开");
            setlabel2.setBounds(70, 180, 110, 20);
            frame1.add(setlabel2);
            setlabel2.setForeground(Color.GRAY);
            setlabel2.setFont(new Font("楷体", Font.PLAIN, 10));
            //查看闹钟3的设置
            if(flag[2]==0) setlabel3.setText("闹钟3:"+alarm[2]+"：关");
            if(flag[2]==1) setlabel3.setText("闹钟3:"+alarm[2]+"：开");
            setlabel3.setBounds(70, 200, 110, 20);
            frame1.add(setlabel3);
            setlabel3.setForeground(Color.GRAY);
            setlabel3.setFont(new Font("楷体", Font.PLAIN, 10));

            pop_reminderBox();


            time_now = "";
            date_now = "";
            time_now2 = "";


            try {
                Thread.sleep(1000);//线程暂停1000毫秒
                label1.setText(time_now);
                label2.setText(date_now);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    //获取时期与时间
    public void get() {
        Calendar time = new GregorianCalendar();//日历
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);

        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);

        date_now += year + "年" + month + "月" + day + "日";

        if (hour <= 9) {
            time_now += "0" + hour + ":";
        } else {
            time_now += hour + ":";
        }

        if (minute <= 9) {
            time_now += "0" + minute + ":";
        } else {
            time_now += minute + ":";
        }

        if (second <= 9) {
            time_now += "0" + second;
        } else {
            time_now += second;
        }

    }
    //点击设置闹钟
    public void b1_click() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set_AlarmClock();
            }
        });
    }
    //闹钟关闭(打开）按钮
    public void bclock1_click() {
        bclock1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag[0] == 0) {
                    flag[0] = 1;
                    bclock1.setText("关闭闹钟1");
                } else {
                    flag[0] = 0;
                    bclock1.setText("开启闹钟1");
                }
            }

        });
    }
    public void bclock2_click() {
        bclock2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag[1] == 0) {
                    flag[1] = 1;
                    bclock1.setText("关闭闹钟2");
                } else {
                    flag[1] = 0;
                    bclock1.setText("开启闹钟2");
                }
            }

        });
    }
    public void bclock3_click() {
        bclock3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag[2] == 0) {
                    flag[2] = 1;
                    bclock1.setText("关闭闹钟");
                } else {
                    flag[2] = 0;
                    bclock1.setText("开启闹钟");
                }
            }

        });
    }
    //闹钟输入
    public void set_AlarmClock() {
        //闹钟窗口设置
        JFrame frame2 = new JFrame("闹钟设置");
        frame2.setSize(250, 200);
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        frame2.setLocation(screenWidth / 2 - 100, screenHeight / 2 - 150);//设置窗口居中显示

        //闹钟输入窗口
        frame2.setLayout(null);
        TextField f1 = new TextField();
        TextField f2 = new TextField();
        TextField f3 = new TextField();
        f1.setBounds(25, 25, 100, 20);
        f2.setBounds(25, 75, 100, 20);
        f3.setBounds(25, 125, 100, 20);
        frame2.add(f1);
        frame2.add(f2);
        frame2.add(f3);
        JButton b3 = new JButton("确定");
        b3.setBounds(150, 130, 60, 20);
        b3.setFont(new Font("楷体", Font.PLAIN, 10));
        frame2.add(b3);
        frame2.setVisible(true);

        f1.setText(alarm[0]);
        f2.setText(alarm[1]);
        f3.setText(alarm[2]);


        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!f1.getText().equals(alarm[0]))//并且本来是开着的
                { flag[0] = 1; }
                if(!f2.getText().equals(alarm[1]))
                { flag[1] = 1; }
                if(!f3.getText().equals(alarm[2]))
                { flag[2] = 1; }

                alarm[0] = f1.getText();
                alarm[1] = f2.getText();
                alarm[2] = f3.getText();

                frame2.dispatchEvent(new WindowEvent(frame2, WindowEvent.WINDOW_CLOSING));
            }
        });

    }
    //预设时间到时弹出对话框
    public void pop_reminderBox() {

        for (int i = 0; i < time_now.length() - 3; i++) {
            time_now2 += time_now.charAt(i);
        }
        for (int i = 0; i < 3; i++) {
            //调试所用
            //System.out.println("time_now"+time_now);
            //System.out.println("time_now2"+time_now2);
            //System.out.println("alarm[i]"+alarm[i]);
            //System.out.println(Flag);
            //System.out.println("--------------");
            if ((flag[i] == 1) && (alarm[i].equals(time_now2))) {
                //弹出框

                int res=JOptionPane.showConfirmDialog(null,"闹钟时间到，是否关闭（若不关闭，则闹钟推迟十分钟）","闹钟",JOptionPane.YES_NO_OPTION);
                if(res==JOptionPane.YES_OPTION){//点击关闭就关闭
                    flag[i]=0;
                    //看关闭的是那个闹钟，将其text设置成打开闹钟
                    if(flag[0]==0)
                        bclock1.setText("打开闹钟1");
                    if(flag[1]==0)
                        bclock2.setText("打开闹钟2");
                    if(flag[2]==0)
                        bclock3.setText("打开闹钟3");
                }
                else {//贪睡系统
                    flag[i]=1;
                    //看关闭的是那个闹钟，将其text设置成打开闹钟
                    if(alarm[0].equals(time_now2))
                    {
                        Calendar time = new GregorianCalendar();//日历
                        int hour = time.get(Calendar.HOUR_OF_DAY);
                        int minute = time.get(Calendar.MINUTE)+10;
                        if(minute>60) {hour++;minute-=60;}
                        if(hour==25)  {hour=1;}
                        String time_now_now="";
                        if (hour <= 9) {
                            time_now_now += "0" + hour + ":";
                        } else {
                            time_now_now += hour + ":";
                        }

                        if (minute <= 9) {
                            time_now_now += "0" + minute;
                        } else {
                            time_now_now += minute;
                        }
                        alarm[0]=time_now_now;//将alarm[0]延后十分钟
                    }
                    if(alarm[1].equals(time_now2))
                    {
                        Calendar time = new GregorianCalendar();//日历
                        int hour = time.get(Calendar.HOUR_OF_DAY);
                        int minute = time.get(Calendar.MINUTE)+10;
                        String time_now_now="";
                        if(minute>60) {hour++;minute-=60;}
                        if(hour==25)  {hour=1;}
                        if (hour <= 9) {
                            time_now_now += "0" + hour + ":";
                        } else {
                            time_now_now += hour + ":";
                        }

                        if (minute <= 9) {
                            time_now_now += "0" + minute;
                        } else {
                            time_now_now += minute;
                        }
                        alarm[1]=time_now_now;//将alarm[1]延后十分钟
                    }
                    if(alarm[2].equals(time_now2))
                    {

                        Calendar time = new GregorianCalendar();//日历
                        int hour = time.get(Calendar.HOUR_OF_DAY);
                        int minute = time.get(Calendar.MINUTE)+10;
                        String time_now_now="";
                        if(minute>60) {hour++;minute-=60;}
                        if(hour==25)  {hour=1;}
                        if (hour <= 9) {
                            time_now_now += "0" + hour + ":";
                        } else {
                            time_now_now += hour + ":";
                        }

                        if (minute <= 9) {
                            time_now_now += "0" + minute;
                        } else {
                            time_now_now += minute ;
                        }

                        alarm[2]=time_now_now;//将alarm[2]延后十分钟
                    }
                }
            }

        }
    }
    //构造函数
    public MyClock() {
        Init();
        start();
        b1_click();
        bclock1_click();
        bclock2_click();
        bclock3_click();
    }
}
