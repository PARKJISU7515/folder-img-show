import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.concurrent.Callable;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Comparator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.jython.book.interfaces.CostCalculatorType;
//import org.plyjy.factory.JythonObjectFactory;
//import org.python.util.PythonInterpreter;




//import org.python.util.PythonInterpreter;

// ������� �ֽź� �ڵ� 


public class Total01 extends JFrame implements Runnable
{
   private Thread thread;
   private JLabel label;

   JFrame frame = new JFrame();
   
   private JButton btn;
   private JButton btn2;
   

   private JPanel panel1; 
   private JPanel panel2; 
   private JPanel panel3; 

   private ImageIcon img = null; //�̹��� �ҷ����� 
   private ImageIcon img2 = null; //�̹��� �ҷ����� 
   private ImageIcon img3 = null; //�̹��� �ҷ����� 
   int index = 0;

   
   public static void main(String[] args) throws IOException
   {
      new Total01();        
  }
  
   /*
   class newWindow extends JFrame {
	    // ��ư�� �������� ��������� �� â�� ������ Ŭ����
	    newWindow() {
	        setTitle("���� ��� â");
	        // ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
	        // �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
	        
	        JPanel NewWindowContainer = new JPanel();
	        setContentPane(NewWindowContainer);
	        
	        JLabel NewLabel = new JLabel("�� â�� ���µ� ����!");
	        
	        NewWindowContainer.add(NewLabel);
	        
	        setSize(300,100);
	        setResizable(false);
	        setVisible(true);
	    }
	}
   
*/
   public  List<File> getImgFileList(String path){      

      return getImgFileList(new File(path));
   }   




   public  List<File> getImgFileList(File file){      

      List<File> resultList = new ArrayList<File>(); //�̹��� ������ ������ ����Ʈ ����

      if(!file.exists()) return resultList;



      File[] list = file.listFiles(new FileFilter() { //���ϴ� ���� �������� 

         String strImgExt = "jpg|jpeg|png|gif|bmp"; 

         @Override
         public boolean accept(File pathname) {                     


            boolean chkResult = false;
            if(pathname.isFile()) {
               String ext = pathname.getName().substring(pathname.getName().lastIndexOf(".")+1);

               chkResult = strImgExt.contains(ext.toLowerCase());      

            } else {
               chkResult = true;
            }
            return chkResult;
         }
      });      

      for(File f : list) {         
         if(f.isDirectory()) {
            //�����̸� �̹�������� �������� ����޼��带 ���ȣ��
            resultList.addAll(getImgFileList(f));             
         }else {         
            //������ �ƴϰ� �����̸� ����Ʈ(resultList)�� �߰�
            resultList.add(f);
         }
      }         
      return resultList; 
   }
   
   class newWindow extends JFrame {
	    // ��ư�� �������� ��������� �� â�� ������ Ŭ����
	    newWindow() {
	        setTitle("���� ��� â");
	        // ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
	        // �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
	        
	        JPanel NewWindowContainer = new JPanel();
	        setContentPane(NewWindowContainer);
	        
	        JLabel NewLabel = new JLabel("�� â ����!");
	        
	        NewWindowContainer.add(NewLabel);
	        
	        setSize(500,500);
	        setResizable(false);
	        setVisible(true);
	    }
	}




   
   
   public Total01()
   {
      super("CCTV");
      
      
     // setTitle("��ư���� ���� ���ο� â");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JFrame frame = new JFrame("Panel ����");
      JButton[] btn = new JButton[5];
      JPanel panel = new JPanel();
      
      for(int i=0; i<btn.length; i++){
    	   btn[i] = new JButton(i+"��° ��ư");}
    	   
    		  
      BorderLayout layout = new BorderLayout();
      
      frame.setSize(500, 500);
      frame.setLayout(layout);
      
      panel.add(btn[0]);
      panel.add(btn[1]);
      panel.add(btn[2]);
      
      frame.add(panel,layout.NORTH);
      
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
    //  JButton OpenWindow = new JButton("�� â ����");
      
     // OpenWindow.addActionListener(new ActionListener() {
          // ������� ��ư "�� â ����"�� ��ư�� �������� �߻��ϴ� �ൿ�� ����
     //     @Override
     //     public void actionPerformed(ActionEvent e) {
             
     //         new newWindow(); // Ŭ���� newWindow�� ���� ������
     //     }
          
   //  });
      

    
     
      
      
   
      
      
      
   
      /////////////////////////////��ư ////////////////////////////////
      
      
      //�г� 1,2,3 
      panel1 = new JPanel() {
         @Override
         public void paint(Graphics g) {
            if(img != null) {

               // �̹��� �׸���
               //�������κ��� ����  , ���������� ���� , ���� ,����
               g.drawImage(img.getImage(), 5, 35, 550, 550, null);
            }
         }
      };

      panel2 = new JPanel() {
         @Override
         public void paint(Graphics g) {
            if(img2 != null) {

               g.drawImage(img2.getImage(),560, 35, 250, 250, this);
            }
         }
      };

      panel3 = new JPanel() {
         @Override
         public void paint(Graphics g) {
            if(img3 != null) {

               // �̹��� �׸���
               g.drawImage(img3.getImage(), 560, 20, 250, 250, this);
            }
         }
      };

      panel1.setPreferredSize(new Dimension(800, 800)); //âũ�� 
      panel2.setPreferredSize(new Dimension(500, 500));
      panel3.setPreferredSize(new Dimension(500, 500));


      //�г� �̹��� ��ġ ���� ���� �ؾߵ� 
      //this.add(panel1, "West"); //W
      //this.add(panel2, "North"); //N
      ////this.add(panel3, "Center");
      //this.add(panel3, "East"); //S


      this.add(panel1, "North"); //W
      this.add(panel2, "Center"); //N
      ////this.add(panel3, "Center");
      this.add(panel3, "South"); //S



      pack();
      //���� setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);


      //�ð� �۲�, ��ġ ���� 
      label = new JLabel();
      label.setFont(new Font("Serif",Font.PLAIN,20)); //�۲� 

      label.setLocation(10, 10);
      //   add(label);

      add(label, "North");

      ////add(label, "Left");

      if(thread == null){
         thread = new Thread(this); 
         thread.start();
      }

      //JLable�� JFrame�� ���
      //setBounds(500,500,500,500); //Frame�� x,y,width,height ������ ũ�� ����

   
    
  
      setDefaultCloseOperation(EXIT_ON_CLOSE); //������ ����� ����
   }





   public void run()
   {   
      int cnt =0;
     
      while(true) 
      {
    	  

         File file = new File("C:\\Users\\������\\Desktop\\CCTV"); 
         List<File> Imglist1 = getImgFileList("C:\\Users\\������\\Desktop\\CCTV"); 

         if( file.exists() )
         { //�������翩��Ȯ��

            if(file.isDirectory()){ //������ ���丮���� Ȯ��

               File[] files = file.listFiles();

               for( int i=0; i<files.length; i++){

                  //������ �������  ���   1�� ������
                  if(Imglist1.isEmpty()) {
                     try{
                        Thread.sleep(1000);}
                     catch(InterruptedException e){
                        e.printStackTrace();
                     }
                  }

                  else {
                     // ������ �̹����� ������� ������ ������ �����ֱ� �ڵ� ���� 4��

                     Collections.reverse(Imglist1); //�̹��� ���� Ʈ ���� 

                     index =0;

                     //���� -> for ������ �̹��� �ϳ��� �� �����ֱ� 

                     img = new ImageIcon( Imglist1.get(index).getAbsolutePath()); //�̹��� ã�Ƽ� �׸���

                     panel1.repaint();  //������ �̹��� �����ֱ� 
                     index++;

                  }

                  if( files[i].delete() ){
                     System.out.println(files[i].getName()+" ��������");
                  }else{
                     System.out.println(files[i].getName()+" ��������");
                  }

               }
            }
         }


         //�г�2�� ������ �̹��� ��������

         File file2 = new File("C:\\Users\\������\\Desktop\\UP"); 
         List<File> Imglist2 = getImgFileList("C:\\Users\\������\\Desktop\\UP");

         if( file2.exists() )
         { //�������翩��Ȯ��

            if(file2.isDirectory()){ //������ ���丮���� Ȯ��

               File[] files2 = file2.listFiles();

               for( int i=0; i<files2.length; i++){

                  //������ �������  ���   1�� ������
                  if(Imglist2.isEmpty()) {
                     try{
                        Thread.sleep(1000);}
                     catch(InterruptedException e){
                        e.printStackTrace();
                     }
                  }

                  else {
                     // ������ �̹����� ������� ������ ������ �����ֱ� �ڵ� ���� 4��

                     Collections.reverse(Imglist2); //�̹��� ���� Ʈ ���� 

                     int index2 =0;

                     //���� -> for ������ �̹��� �ϳ��� �� �����ֱ� 

                     img2 = new ImageIcon( Imglist2.get(index2).getAbsolutePath()); //�̹��� ã�Ƽ� �׸���

                     panel2.repaint();  //������ �̹��� �����ֱ� 
                     index2++;

                  }

                  if( files2[i].delete() ){
                     System.out.println(files2[i].getName()+" ��������");
                  }else{
                     System.out.println(files2[i].getName()+" ��������");
                  }

               }

            }

         }



         //�г�3�� ������ �̹��� ��������
         File file3 = new File("C:\\Users\\������\\Desktop\\DOWN"); 
         List<File> Imglist3 = getImgFileList("C:\\Users\\������\\Desktop\\DOWN");

         if( file3.exists() )
         { //�������翩��Ȯ��

            if(file3.isDirectory()){ //������ ���丮���� Ȯ��

               File[] files3 = file3.listFiles();

               for( int i=0; i<files3.length; i++){

                  //������ �������  ���   1�� ������
                  if(Imglist3.isEmpty()) {
                     try{
                        Thread.sleep(1000);}
                     catch(InterruptedException e){
                        e.printStackTrace();
                     }
                  }

                  else {
                     // ������ �̹����� ������� ������ ������ �����ֱ� �ڵ� ���� 4��

                     Collections.reverse(Imglist3); //�̹��� ���� Ʈ ���� 

                     int index3 =0;

                     //���� -> for ������ �̹��� �ϳ��� �� �����ֱ� 

                     img3 = new ImageIcon( Imglist3.get(index3).getAbsolutePath()); //�̹��� ã�Ƽ� �׸���

                     panel3.repaint();  //������ �̹��� �����ֱ� 
                     index3++;

                  }

                  if( files3[i].delete() ){
                     System.out.println(files3[i].getName()+" ��������");
                  }else{
                     System.out.println(files3[i].getName()+" ��������");
                  }

               }

            }

         }


      }
   }

}
