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

// 현재까지 최신본 코드 


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

   private ImageIcon img = null; //이미지 불러오기 
   private ImageIcon img2 = null; //이미지 불러오기 
   private ImageIcon img3 = null; //이미지 불러오기 
   int index = 0;

   
   public static void main(String[] args) throws IOException
   {
      new Total01();        
  }
  
   /*
   class newWindow extends JFrame {
	    // 버튼이 눌러지면 만들어지는 새 창을 정의한 클래스
	    newWindow() {
	        setTitle("새로 띄운 창");
	        // 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
	        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
	        
	        JPanel NewWindowContainer = new JPanel();
	        setContentPane(NewWindowContainer);
	        
	        JLabel NewLabel = new JLabel("새 창을 띄우는데 성공!");
	        
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

      List<File> resultList = new ArrayList<File>(); //이미지 파일을 저장할 리스트 생성

      if(!file.exists()) return resultList;



      File[] list = file.listFiles(new FileFilter() { //원하는 파일 가져오기 

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
            //폴더이면 이미지목록을 가져오는 현재메서드를 재귀호출
            resultList.addAll(getImgFileList(f));             
         }else {         
            //폴더가 아니고 파일이면 리스트(resultList)에 추가
            resultList.add(f);
         }
      }         
      return resultList; 
   }
   
   class newWindow extends JFrame {
	    // 버튼이 눌러지면 만들어지는 새 창을 정의한 클래스
	    newWindow() {
	        setTitle("새로 띄운 창");
	        // 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
	        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
	        
	        JPanel NewWindowContainer = new JPanel();
	        setContentPane(NewWindowContainer);
	        
	        JLabel NewLabel = new JLabel("새 창 성공!");
	        
	        NewWindowContainer.add(NewLabel);
	        
	        setSize(500,500);
	        setResizable(false);
	        setVisible(true);
	    }
	}




   
   
   public Total01()
   {
      super("CCTV");
      
      
     // setTitle("버튼으로 띄우는 새로운 창");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JFrame frame = new JFrame("Panel 예제");
      JButton[] btn = new JButton[5];
      JPanel panel = new JPanel();
      
      for(int i=0; i<btn.length; i++){
    	   btn[i] = new JButton(i+"번째 버튼");}
    	   
    		  
      BorderLayout layout = new BorderLayout();
      
      frame.setSize(500, 500);
      frame.setLayout(layout);
      
      panel.add(btn[0]);
      panel.add(btn[1]);
      panel.add(btn[2]);
      
      frame.add(panel,layout.NORTH);
      
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
    //  JButton OpenWindow = new JButton("새 창 띄우기");
      
     // OpenWindow.addActionListener(new ActionListener() {
          // 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
     //     @Override
     //     public void actionPerformed(ActionEvent e) {
             
     //         new newWindow(); // 클래스 newWindow를 새로 만들어낸다
     //     }
          
   //  });
      

    
     
      
      
   
      
      
      
   
      /////////////////////////////버튼 ////////////////////////////////
      
      
      //패널 1,2,3 
      panel1 = new JPanel() {
         @Override
         public void paint(Graphics g) {
            if(img != null) {

               // 이미지 그리기
               //왼쪽으로부터 길이  , 위에서부터 길이 , 가로 ,세로
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

               // 이미지 그리기
               g.drawImage(img3.getImage(), 560, 20, 250, 250, this);
            }
         }
      };

      panel1.setPreferredSize(new Dimension(800, 800)); //창크기 
      panel2.setPreferredSize(new Dimension(500, 500));
      panel3.setPreferredSize(new Dimension(500, 500));


      //패널 이미지 위치 선정 수정 해야됨 
      //this.add(panel1, "West"); //W
      //this.add(panel2, "North"); //N
      ////this.add(panel3, "Center");
      //this.add(panel3, "East"); //S


      this.add(panel1, "North"); //W
      this.add(panel2, "Center"); //N
      ////this.add(panel3, "Center");
      this.add(panel3, "South"); //S



      pack();
      //수정 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);


      //시계 글꼴, 위치 선정 
      label = new JLabel();
      label.setFont(new Font("Serif",Font.PLAIN,20)); //글꼴 

      label.setLocation(10, 10);
      //   add(label);

      add(label, "North");

      ////add(label, "Left");

      if(thread == null){
         thread = new Thread(this); 
         thread.start();
      }

      //JLable을 JFrame에 등록
      //setBounds(500,500,500,500); //Frame의 x,y,width,height 프레임 크기 조정

   
    
  
      setDefaultCloseOperation(EXIT_ON_CLOSE); //윈도우 종료시 종료
   }





   public void run()
   {   
      int cnt =0;
     
      while(true) 
      {
    	  

         File file = new File("C:\\Users\\박지수\\Desktop\\CCTV"); 
         List<File> Imglist1 = getImgFileList("C:\\Users\\박지수\\Desktop\\CCTV"); 

         if( file.exists() )
         { //파일존재여부확인

            if(file.isDirectory()){ //파일이 디렉토리인지 확인

               File[] files = file.listFiles();

               for( int i=0; i<files.length; i++){

                  //폴더가 비어있을  경우   1초 딜레이
                  if(Imglist1.isEmpty()) {
                     try{
                        Thread.sleep(1000);}
                     catch(InterruptedException e){
                        e.printStackTrace();
                     }
                  }

                  else {
                     // 폴더에 이미지가 있을경우 마지막 사진만 보여주기 코드 하위 4줄

                     Collections.reverse(Imglist1); //이미지 리스 트 역순 

                     index =0;

                     //질문 -> for 문으로 이미지 하나씩 다 보여주기 

                     img = new ImageIcon( Imglist1.get(index).getAbsolutePath()); //이미지 찾아서 그리기

                     panel1.repaint();  //마지막 이미지 보여주기 
                     index++;

                  }

                  if( files[i].delete() ){
                     System.out.println(files[i].getName()+" 삭제성공");
                  }else{
                     System.out.println(files[i].getName()+" 삭제실패");
                  }

               }
            }
         }


         //패널2에 보여줄 이미지 가져오기

         File file2 = new File("C:\\Users\\박지수\\Desktop\\UP"); 
         List<File> Imglist2 = getImgFileList("C:\\Users\\박지수\\Desktop\\UP");

         if( file2.exists() )
         { //파일존재여부확인

            if(file2.isDirectory()){ //파일이 디렉토리인지 확인

               File[] files2 = file2.listFiles();

               for( int i=0; i<files2.length; i++){

                  //폴더가 비어있을  경우   1초 딜레이
                  if(Imglist2.isEmpty()) {
                     try{
                        Thread.sleep(1000);}
                     catch(InterruptedException e){
                        e.printStackTrace();
                     }
                  }

                  else {
                     // 폴더에 이미지가 있을경우 마지막 사진만 보여주기 코드 하위 4줄

                     Collections.reverse(Imglist2); //이미지 리스 트 역순 

                     int index2 =0;

                     //질문 -> for 문으로 이미지 하나씩 다 보여주기 

                     img2 = new ImageIcon( Imglist2.get(index2).getAbsolutePath()); //이미지 찾아서 그리기

                     panel2.repaint();  //마지막 이미지 보여주기 
                     index2++;

                  }

                  if( files2[i].delete() ){
                     System.out.println(files2[i].getName()+" 삭제성공");
                  }else{
                     System.out.println(files2[i].getName()+" 삭제실패");
                  }

               }

            }

         }



         //패널3에 보여줄 이미지 가져오기
         File file3 = new File("C:\\Users\\박지수\\Desktop\\DOWN"); 
         List<File> Imglist3 = getImgFileList("C:\\Users\\박지수\\Desktop\\DOWN");

         if( file3.exists() )
         { //파일존재여부확인

            if(file3.isDirectory()){ //파일이 디렉토리인지 확인

               File[] files3 = file3.listFiles();

               for( int i=0; i<files3.length; i++){

                  //폴더가 비어있을  경우   1초 딜레이
                  if(Imglist3.isEmpty()) {
                     try{
                        Thread.sleep(1000);}
                     catch(InterruptedException e){
                        e.printStackTrace();
                     }
                  }

                  else {
                     // 폴더에 이미지가 있을경우 마지막 사진만 보여주기 코드 하위 4줄

                     Collections.reverse(Imglist3); //이미지 리스 트 역순 

                     int index3 =0;

                     //질문 -> for 문으로 이미지 하나씩 다 보여주기 

                     img3 = new ImageIcon( Imglist3.get(index3).getAbsolutePath()); //이미지 찾아서 그리기

                     panel3.repaint();  //마지막 이미지 보여주기 
                     index3++;

                  }

                  if( files3[i].delete() ){
                     System.out.println(files3[i].getName()+" 삭제성공");
                  }else{
                     System.out.println(files3[i].getName()+" 삭제실패");
                  }

               }

            }

         }


      }
   }

}
