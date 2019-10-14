package subway;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;




public class subway<T> {
	public static List<Line> mainlist = new ArrayList<Line>();
	protected int[][] subTrainMatrix; // 二维数组
	private static final int MAX_WEIGHT = 99; // 线路长度的最大权值
	private int[] dist;
	private List<Station> vertex = new ArrayList<Station>();// 按照顺序保存node
	private List<Edge> edges;
 
	public int[][] getSubTrainMatrix() {
		return subTrainMatrix;
	}
 
	public void setVertex(List<Station> vertices) {
		this.vertex = vertices;
	}
 
	public List<Station> getVertex() {
		return vertex;
	}
 
	public List<Edge> getEdges() {
		return edges;
	}
 
	public int getVertexSize() {
		return this.vertex.size();
	}
 
	public int vertexCount() {
		return subTrainMatrix.length;
	}
 
	@Override
	public String toString() {
		String str = "邻接矩阵：\n";
		int n = subTrainMatrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				str += this.subTrainMatrix[i][j] == MAX_WEIGHT ? " $" : " "
						+ this.subTrainMatrix[i][j];
			str += "\n";
		}
		return str;
	}
 
	public subway(int size) {
		this.vertex = new ArrayList<Station>();
		this.subTrainMatrix = new int[size][size];
		this.dist = new int[size];
		for (int i = 0; i < size; i++) { // 初始化邻接矩阵
			for (int j = 0; j < size; j++) {
				this.subTrainMatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;// 
			}
		}
	}
 
	public subway(List<Station> vertices) {
		this.vertex = vertices;
		int size = getVertexSize();
		this.subTrainMatrix = new int[size][size];
		this.dist = new int[size];
		for (int i = 0; i < size; i++) { // 初始化邻接矩阵
			for (int j = 0; j < size; j++) {
				this.subTrainMatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;
			}
		}
	}
 

	public int getPosInvertex(Station start) {
		for(int i=0;i<vertex.size();i++)
        {
			//System.out.println(vertex.get(i).getSname());
    			if(vertex.get(i).getSname().equals(start.sname))
    			{
    				return i;
    			}
        }
		//return vertex.indexOf(start);
		return -1;
	}
 
	public int getWeight(Station start, Station stop) {
		int i = getPosInvertex(start);
		int j = getPosInvertex(stop);
		return this.subTrainMatrix[i][j];
	} //返回起始站和目的站间的距离
 
	public void insertEdge(Station start, Station stop, int weight) { // 
		int n = subTrainMatrix.length;
		int i = getPosInvertex(start);
		int j = getPosInvertex(stop);
		if (i >= 0 && i < n && j >= 0 && j < n
				&& this.subTrainMatrix[i][j] == MAX_WEIGHT && i != j) {
			this.subTrainMatrix[i][j] = weight;
			this.subTrainMatrix[j][i] = weight;
		}
	}
 
	public void addEdge(Station start, Station dest, int weight) {
		this.insertEdge(start, dest, weight);
	}
 
	public void removeEdge(String start, String stop) { //删除边 
		int i = vertex.indexOf(start);
		int j = vertex.indexOf(stop);
		if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()
				&& i != j)
			this.subTrainMatrix[i][j] = MAX_WEIGHT;
	}
 
	
 
	private static subway<Station> graph;
 
	/** 显示顶点间的距离*/
	public void printL(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.out.printf("%4d", a[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void printstation() throws Exception {
		for(int i=0;i<mainlist.size();i++)
		{
			System.out.println(mainlist.get(i).getLname());
			for(int j=0;j<mainlist.get(i).getStation().size();j++)
		    {
				System.out.print(mainlist.get(i).getStation().get(j).getSname()+" ");
				for(int k=0;k<mainlist.get(i).getStation().get(j).getStationrans().length;k++)
		        {
					System.out.println(mainlist.get(i).getStation().get(j).getStationrans()[k]);
		        }
		    }
		}
	}
	
	public static void printline(String lname) throws Exception {
		for(int i=0;i<mainlist.size();i++)
		{
			if(mainlist.get(i).getLname().equals(lname))
			{
				System.out.println(mainlist.get(i).getLname());
				for(int j=0;j<mainlist.get(i).getStation().size();j++)
			    {
					System.out.print(mainlist.get(i).getStation().get(j).getSname()+" ");
					for(int k=0;k<mainlist.get(i).getStation().get(j).getStationrans().length;k++)
			        {
						System.out.println(mainlist.get(i).getStation().get(j).getStationrans()[k]);
			        }
			    }
			}
		}
	}
	

	public static boolean outPutRoute(String routename,String filename) throws FileNotFoundException {//
		byte[] text;
		
		System.out.println(routename+":");
		File file=new File(filename);
		try {
			FileOutputStream out=new FileOutputStream(file);
			text=(routename+"\r\n").getBytes();
			out.write(text);
			for(int i=0;i<mainlist.size();i++)
			{
					for(int j=0;j<mainlist.get(i).getStation().size();j++)
				    {
						System.out.println(mainlist.get(i).getStation().get(j).getSname());
						text=(mainlist.get(i).getStation().get(j).getSname()+"\r\n").getBytes();
						out.write(text);
				    }
					break;
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public static Boolean Mainoutput(String start,String stop,String filename){
		
		int s = 0;
		int t = 0;
		Station a = new Station();
		Station b = new Station();
		int flag = 0;
		
		List<Station> vertices = new LinkedList<Station>();
		String[] kb = Input.kb.clone();
		String[] kb1 = kb.clone();
		
		int c = 0;
		int v = 0;
		
		
    	for(int i=0;i<mainlist.size();i++)
        {
    		
    		for(int j=0;j<mainlist.get(i).getStation().size();j++)
            {	
    			flag=0;
    			Station st = new Station();
    			st.setSname("0");
    			
    			for(int l=0;l<kb.length;l++)
				{
    				if(mainlist.get(i).getStation().get(j).getSname().equals(kb1[l]))
					{
    					flag = 1;
					}
    				
				}
    			if(flag==0)//非换乘站
    			{
    				st.setSname(mainlist.get(i).getStation().get(j).getSname());
    				st.setLine(mainlist.get(i).getStation().get(j).getLine());
    				st.setStationrans(mainlist.get(i).getStation().get(j).getStationrans());
	    			c++;
    			}
	    		else{
    				for(int p=0;p<kb.length;p++)
    				{
    					if((mainlist.get(i).getStation().get(j).getSname().equals(kb1[p]))&&(!(kb[p].equals("0"))))
    					{
    						st.setSname(mainlist.get(i).getStation().get(j).getSname());
    						st.setLine(mainlist.get(i).getStation().get(j).getLine());
    						st.setStationrans(mainlist.get(i).getStation().get(j).getStationrans());
    						kb[p]="0";
    						v++;
    					}
    				
    				}
    			}
    				
    			
    			
    			if(!st.getSname().equals("0"))
    			{
    				vertices.add(st);
    			}
            }
        }
    	
		graph = new subway<Station>(vertices);	
		
		
		for(int i=0;i<mainlist.size();i++)
        {
    		for(int j=0;j<mainlist.get(i).getStation().size()-1;j++)
            {
    			graph.addEdge(mainlist.get(i).getStation().get(j), mainlist.get(i).getStation().get(j+1), 1);
            }
        }

		
		

		
		
		for(int i=0;i<mainlist.size();i++)
        {
    		for(int j=0;j<mainlist.get(i).getStation().size();j++)
            {
    			if(mainlist.get(i).getStation().get(j).getSname().equals(start)&&s==0)
    			{
    				a = mainlist.get(i).getStation().get(j);
    				//System.out.println(a.sname);
    				s=1;
    			}
    			if(mainlist.get(i).getStation().get(j).getSname().equals(stop)&&t==0)
    			{
    				b = mainlist.get(i).getStation().get(j);
    				//System.out.println(b.sname);
    				t=1;
    			}
    			if(s==1&&t==1)
    				break;
            }
    		if(s==1&&t==1)
				break;
        }
		int len = graph.find(a, b) + 1;
		if(len!=100)
		{
			System.out.println(a.sname + " -> " + b.sname + "最短需要经过的站点为：" + len);
		}else{
			System.out.println("因某些问题，无法到达！");
		}
		graph.findoutput(a, b,len,filename);
		return true;
	}
	
	
	public int find(Station start, Station stop) {
		//System.out.println(start.sname+stop.sname);
		int startPos = getPosInvertex(start);
		//System.out.println(startPos);
		int stopPos = getPosInvertex(stop);
		//System.out.println(stopPos);
		if (stopPos < 0 || stopPos > getVertexSize())
		{
			System.out.println("目标站点不存在，无法到达");
			return MAX_WEIGHT;
		}
		if (startPos < 0 || startPos > getVertexSize())
		{
			System.out.println("起始站点不存在，无法到达");
			return MAX_WEIGHT;
		}
		String[] path = dijkstra(startPos);
		System.out.println("从" + start.sname + " 到 " + stop.sname + "的最短路径为");
		System.out.println(path[stopPos]);
		return dist[stopPos];
	}
	
	public void findoutput(Station start, Station stop,int len,String filename) {
		
		int startPos = getPosInvertex(start);
		int stopPos = getPosInvertex(stop);
	
		if (stopPos < 0 || stopPos > getVertexSize())
		{
			System.out.println("目标站点不存在，无法到达");
			return;
		}
		if (startPos < 0 || startPos > getVertexSize())
		{
			System.out.println("起始站不存在，无法到达");
			return;
		}
		String[] path = dijkstra(startPos);
		//System.out.println(path[stopPos]);
		
		byte[] text;
		File file=new File(filename);
		try {
			FileOutputStream out=new FileOutputStream(file);
			text=(len+":\r\n").getBytes();
			out.write(text);
			text=("从 " + start.sname + "到 " + stop.sname + " 的最短路径"+":\r\n").getBytes();
			out.write(text);
			text=(path[stopPos]+":\r\n").getBytes();
			out.write(text);
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	
	public static void main(String[] args) throws Exception {
		
		
		int flag1=0;
		int flag2=0;
		String operate=null;
		String route=null;
		String start=null;
		String stop=null;
		String output=null;
		
		
		if(args.length==0) System.out.println("命令与参数不能为空");
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("-map")) {
				flag1=1;
				flag2=1;
				if(args.length<i+2) {
					System.out.println("命令格式不正确！");
					return;
				}
				mainlist =Input.getFileContext(args[i+1]);
			}else if(args[i].equals("-a")) {
				flag1=1;
				operate="-a";
				if(args.length<i+2) {
					System.out.println("命令格式不正确！");
					return;
				}
				route=args[i+1];
			}else if(args[i].equals("-b")) {
				flag1=1;
				operate="-b";
				if(args.length<i+3) {
					System.out.println("命令格式不正确！");
					return;
				}
				start=args[i+1];
				stop=args[i+2];
			}else if(args[i].equals("-o")) {
				flag1=1;
				output=args[i+1];
			}
		}
		if(flag1==0) {
			System.out.println("命令格式不正确！");
			return;
		}
		if(flag2==0) {
			System.out.println("无地铁信息文件");
			return;
		}
		if(operate==null&&output!=null) {
			System.out.println("命令格式不正确！");
			return;
		}
		if(operate!=null&&output!=null) {
			if(operate.equals("-a")) {
				try {
					if(outPutRoute(route, output)==false) {
						System.out.println("无法生成路线");
						return;
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}else if(operate.equals("-b")) {
				if(start.equals(stop)) {
					System.out.println("出发站与到达站不能相同！！！！！");
					return;
				}
				if(Mainoutput(start, stop, output)==false) {
					System.out.println("目的站不存在");
					return;
				}
			}
		}
	
	
	}
 
	
	//Dijkstra算法 
	private String[] dijkstra(int vertex) {
		int n = dist.length - 1;
		String[] path = new String[n + 1]; //存放路径 
	
		for (int i = 0; i <= n; i++)
		{		
			path[i] = new String(this.vertex.get(vertex).sname+"("+this.vertex.get(i).getLine()+")" + "-->"
					+ this.vertex.get(i).sname+"("+this.vertex.get(i).getLine())+")";
		}
				
			
					
 
		boolean[] visited = new boolean[n + 1];
		// 
		for (int i = 0; i <= n; i++) {
			dist[i] = subTrainMatrix[vertex][i];//初始化到各个顶点的距离
			visited[i] = false;//初始化所有访问过的节点
		}
 
		dist[vertex] = 0;
		visited[vertex] = true;
 
		for (int i = 1; i <= n; i++) {// 访问所有节点
			int temp = MAX_WEIGHT;
			int visiting = vertex;
			for (int j = 0; j <= n; j++) {
				if ((!visited[j]) && (dist[j] < temp)) {
					temp = dist[j];
					visiting = j;
				}
			}
		
			visited[visiting] = true; 
			for (int j = 0; j <= n; j++) {
				if (visited[j]) {
					continue;
				}
				int newdist = dist[visiting] + subTrainMatrix[visiting][j];//
				if (newdist < dist[j]) {
					// dist[j] 
					dist[j] = newdist;
					path[j] = path[visiting] + "-->" + this.vertex.get(j).sname+"("+this.vertex.get(j).getLine()+")";
				}
			}// update all new distance
 
		}// visite all nodes
			// for (int i = 0; i <= n; i++)
		// System.out.println(" + path[i]);
		// System.out.println("=====================================");
		return path;
	}
 
	/**
	 * 
	 * 
	 * @author patrick
	 * 
	 */
	class Edge {
		private T start, dest;
		private int weight;
 
		public Edge() {
		}
 
		public Edge(T start, T dest, int weight) {
			this.start = start;
			this.dest = dest;
			this.weight = weight;
		}
 
		public String toString() {
			return "(" + start + "," + dest + "," + weight + ")";
		}
 
	}
 
}
	