package mk.thread.executorservices;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileProcessingRunnable  implements Runnable{

	private String filesname;
	
	public JsonFileProcessingRunnable(String filesname) {
	
		this.filesname=filesname;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread started::::"+Thread.currentThread().getName());
		try {
			fileReaer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread ended::::"+Thread.currentThread().getName());
	}
	
	public  void fileReaer() throws IOException {
	
		Map<String,String> map = new HashMap();
		Stream<String>stream=Files.lines(Paths.get(this.filesname),Charset.forName("ISO-8859-1"));
		   stream.forEach(line ->{
			   JSONParser parser = new JSONParser();  
		   try {
			JSONObject json = (JSONObject) parser.parse(line);
			   String pnr_id= (String) json.get("PNR_ID");
			   String segments= (String) json.get("Segments").toString();
			   map.put(pnr_id, segments);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}  });
		
		   System.out.println("Map   "+map);
	}
	
	public static void main(String[] args) {
		ExecutorService executor=Executors.newFixedThreadPool(5);
		File folder = new File("/Volumes/MYHARDDRIVE/BDUPROJECT" + "/json");
		 File[] listOfFiles = folder.listFiles();
		 List<String> list = new ArrayList<>();
		 for(int i=0;i<listOfFiles.length;i++){
	            list.add(listOfFiles[i].getAbsolutePath());
	        }
		
		for(int i=1;i<list.size();i++) {
			Runnable worker=new JsonFileProcessingRunnable(list.get(i));
			executor.execute(worker);
		}
		executor.shutdown();
		
		if(executor.isTerminated()) {
			System.out.println("All threads are terminated");
		}
		
	}

}
