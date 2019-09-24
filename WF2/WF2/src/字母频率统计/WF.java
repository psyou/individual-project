  package 字母频率统计;
  import java.io.BufferedReader;
  import java.io.File;
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.FileReader;
  import java.io.IOException;
  import java.io.InputStreamReader;
  import java.text.DecimalFormat;
  import java.text.NumberFormat;
  import java.util.*;
  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.Comparator;
  import java.util.HashMap;
  import java.util.List;
  import java.util.Map;
  import java.util.Map.Entry;
  public class WF {
      public static void wf(String args) throws  IOException {
          try {
          //    DecimalFormat df = new DecimalFormat("######0.00");
              FileReader fr = new FileReader(args);
              BufferedReader br = new BufferedReader(fr);
              NumberFormat nf = NumberFormat.getInstance();
                
              
        
              nf.setMaximumFractionDigits(2);
              HashMap<String, Integer> map = new HashMap<String, Integer>();
               
              String string =null;
              Integer count = 0;
              Integer total = 0;
               
              while ((string=br.readLine())!=null) {
                  char[] ch = string.toCharArray();
                   
                  for (int i = 0; i < ch.length; i++) {
                      if (ch[i] > 'A' && ch[i]< 'z') {
                           
                      total++;
                      ch[i] = Character.toLowerCase(ch[i]);
                      count = map.get(ch[i]+"");
                      if (count == null) {
                          count = 1;
                      }else {
                          count++;
                      }
                      map.put(ch[i]+"", count);
                  }
                  }
              }
               ArrayList<String> list = new ArrayList<String>();
               list.addAll(map.keySet());  
                
               
               for(int i = 0;i < list.size();i++)
               {
                    
                   for(int j = 0;j < (list.size() - i-1);j++)     //list.size() - i-1因为要用到i+1要考虑是否超出范围的问题
                   {
                        
                       if(map.get(list.get(j)) < map.get(list.get(j+1)))
                       {
                           String t = list.get(j);
                           list.set(j, list.get(j+1));
                           list.set( j+1, t);
                       }
                   }
               }
               for(int i = 0 ; i < list.size();i++)
                  {
                      System.out.println(list.get(i) + ":" + map.get(list.get(i)) +"   "+ nf.format((float)(map.get(list.get(i)))*100/total) + "%");
                  }
               
               
          } catch (FileNotFoundException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }
       
       
       
       
	  public static void danci(String args) throws FileNotFoundException {
          File file=new File(args);                  //读取文件
         Scanner input=new Scanner(file);
          HashMap<String,Integer> hashMap=new HashMap<String,Integer>();
          while(input.hasNextLine()) {
              String line=input.nextLine();
              String[] lineWords=line.split("\\W+");          
              Set<String> wordSet=hashMap.keySet();
              for(int i=0;i<lineWords.length;i++) {
                  if(wordSet.contains(lineWords[i])) {
                      Integer number=hashMap.get(lineWords[i]);
                      number++;
                      hashMap.put(lineWords[i], number);  
                      }
                  else {
                      hashMap.put(lineWords[i], 1);
                  }
              }
          }
          for (String key : hashMap.keySet()) {
              System.out.println(key+"出现："+hashMap.get(key)+"次");
          }
      }
      
      public static void danci2(String args) throws IOException {
          Scanner input=new Scanner(System.in);
          System.out.println("输入要统计的个数：");
          int n=input.nextInt();
          File file = new File(args);
          FileInputStream fis = new FileInputStream(file);
          InputStreamReader isr = new InputStreamReader(fis);
  
          int thechar;
          StringBuffer sb = new StringBuffer();
          HashMap<String, Integer> wordList = new HashMap<String, Integer>();
          
          while ((thechar = isr.read()) != -1) {
              char letter = (char) thechar;
              if ((letter >= 'a' && letter <= 'z')
                      || (letter >= 'A' && letter <= 'Z')) {
                  sb.append(letter);
              } else if (sb.length() != 0) {
                  String theword = new String(sb);
                  if (wordList.containsKey(theword)) {
                      wordList.put(theword, wordList.get(theword) + 1);
                  } else {
                      wordList.put(theword, 1);
                  }
                  sb.delete(0, sb.length());
              }
          }
          isr.close();
          
          List<Map.Entry<String, Integer>> words = new ArrayList<Map.Entry<String, Integer>>(
                 wordList.entrySet());
         
          Collections.sort(words, new Comparator<Map.Entry<String, Integer>>() {
  
              @Override
              public int compare(Entry<String, Integer> o1,
                      Entry<String, Integer> o2) {
                  return -(o1.getValue() - o2.getValue());
              }
          });
          System.out.println("读取的文件中出现频率最多的"+n+"个单词是：");
          int i = 0;
          for (Map.Entry<String, Integer> node : words) {
              if (i < n) {
                  System.out.println(node.getKey() + " : " + node.getValue());
              } else {
                  break;
              }
              i++;
          }
      }
     
     
     public static void menu() {
         System.out.println("*****************************");
         System.out.println("欢迎访问本系统");
         System.out.println("1.统计字母出现频率");
         System.out.println("2.统计不重复单词情况");
         System.out.println("3.统计出现次数前n个单词");
         System.out.println("0.退出");
         System.out.println("*****************************");
         System.out.println("请选择：");
     }
    public static  void main(String[] args) throws IOException   {
     
     Scanner shuru=new Scanner(System.in);
     int a=1;
     
     while(a!=0) {
         menu();
         a=shuru.nextInt();
         switch(a)
         {
         case 1:wf(args[1]);break;
         case 2:danci(args[1]);break;
         case 3:danci2(args[1]);break;
         case 0:a=0;break;
         default:a=0;break;
         }
     }
 }
 }

