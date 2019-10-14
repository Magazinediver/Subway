package subway;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Input {
	public static List<Line> list =new ArrayList<Line>();
	public static String[] kb = new String[63];
	
	public static List<Line> getFileContext(String fileaddress) throws Exception {
		
		//File file=new File("C:\\\\Users\\\\adnim\\\\Desktop\\\\Subway.txt");
		File file=new File(fileaddress);
		FileReader fileReader =new FileReader(file);
        BufferedReader bufferedReader =new BufferedReader(fileReader);
        
        String str=null;
        String str1=null;
        for(int p=0;p<kb.length;p++)
		{
			kb[p]="0";
		}
        int t=0;
        int u=0;
        int i = Integer.parseInt(bufferedReader.readLine());//
        int j = 1;
        while((str=bufferedReader.readLine())!=null) {
        	if(str.equals(String.valueOf(j)))//
        	{
        		Line a  = new Line();
        		int k = Integer.parseInt(bufferedReader.readLine());//
        		a.setLname(bufferedReader.readLine());
        		//System.out.println(a.getLname()+":");//
        		for(int h=0;h<k;h++)
        		{
        			Station b = new Station();
        			if((str1=bufferedReader.readLine())!=null)
        			{
        				String[] s = str1.split(" ");
        				//System.out.println(s[0]+" "+s[1]);//
        				b.setLine(s[0]);
        				b.setSname(s[1]);
        				if(!s[2].equals("·ñ"))
        				{
        					String[] s1 = s[2].split(",");
        					b.setStationrans(s1);
        					u=0;
        					for(int p=0;p<kb.length;p++)
        					{
        						if(kb[p].equals(b.getSname()))
        						{
        							u=1;
        						}
        					}
        					if(u!=1) {
        						kb[t] = b.getSname();
        						t++;
        					}
        				}
        			}
        			a.setStation(b);
        		}
        		list.add(a);
        		j++;
        	}
        }
		return list;
    }
}
