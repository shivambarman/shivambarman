import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public void saveEmployee(File jsonFile) throws InterruptedException, ExecutionException {
		System.out.println("Method called");
		//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	
		int core=Runtime.getRuntime().availableProcessors();
		System.out.println("CPU Core- "+core);
		ExecutorService executor=Executors.newFixedThreadPool(core);
		CompletableFuture<Void> future=CompletableFuture.runAsync(new ReadUser(jsonFile),executor);
		// future.get();
		/*if(!future.isDone()) {
			Thread.sleep(2000);
		}*/
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Test t=new Test();
		//ClassLoader classLoader = Test.class.getClassLoader();
		// ClassLoader classLoader = Test.getClassLoader();
		//File file=new File("user.json");
		File jsonFile=new File("C:\\Users\\Shivam\\Documents\\user.json");
		File jsonFile2=new File("C:\\Users\\Shivam\\Documents\\user2.json");
		t.saveEmployee(jsonFile);
		t.saveEmployee(jsonFile2);
		System.out.println("End of Main method");
		//CompletableFuture.supplyAsync(supplier)
	}

}
