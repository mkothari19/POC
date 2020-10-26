package mk.thread.executorservices;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileProcessingCallable implements Callable<Map<String,String>> {

private String filename=null;
JsonFileProcessingCallable(String filename){
	this.filename=filename;
}
	
	@Override
	public Map<String, String> call() throws Exception {
		// TODO Auto-generated method stub
	Map<String,String> pnrmap=new HashMap<String,String>();
	Stream<String> stream=	Files.lines(Paths.get(this.filename),Charset.forName("ISO-8859-1"));
     stream.forEach(line->{
    	 JSONParser parse=new JSONParser();
    	 try {
			JSONObject obj= (JSONObject) parse.parse(line);
			String pnr_id=(String) obj.get("PNR_ID");
			String segments=(String) obj.get("Segments");
			pnrmap.put(pnr_id,segments);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	 
     });
		return pnrmap;
	}
public static void main(String[] args) throws InterruptedException, ExecutionException {
	ExecutorService executor=Executors.newFixedThreadPool(5);
	List<Future<Map<String,String>>> result = new ArrayList<>();
	Map<String,String> finalResult = new HashMap<>();
	File folder = new File("/Volumes/MYHARDDRIVE/BDUPROJECT" + "/json");
	 File[] listOfFiles = folder.listFiles();
	 List<String> list = new ArrayList<>();
	 for(int i=0;i<listOfFiles.length;i++){
            list.add(listOfFiles[i].getAbsolutePath());
        }
	for(int i=0;i<list.size();i++) {
		result.add(executor.submit(new JsonFileProcessingCallable(list.get(i)) ));
	}
	
	for(int i=0;i<result.size();i++) {
		Map<String,String> temp=result.get(i).get();
		temp.forEach((K,V)->{
			finalResult.put(K,V);
			
		});
	
		System.out.println(finalResult);
	}
	
}
	
}
