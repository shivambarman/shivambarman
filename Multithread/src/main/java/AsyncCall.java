import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class AsyncCall {

	public List<User> getData(File jsonFile) throws InterruptedException, ExecutionException {
		int core = Runtime.getRuntime().availableProcessors();
		// ExecutorService service=Executors.newFixedThreadPool(core);
		ExecutorService service = Executors.newCachedThreadPool();
		CompletableFuture<List<User>> future = CompletableFuture.supplyAsync(() -> {
			try {
				return new UserRead(jsonFile).userRead();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}, service);
		return future.get();
	}

	public Void trainningRemainder(File jsonFile) throws InterruptedException, ExecutionException, ParseException {
		ReadEmployee read = new ReadEmployee(jsonFile);
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = sdformat.parse("01/07/2022");
		int core = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(core);
		// ExecutorService service=Executors.newCachedThreadPool();
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread Read- " + Thread.currentThread().getName());
			try {
				return read.userRead();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}).thenApply((employees) -> {
			System.out.println("Thread Filter Joinned - " + Thread.currentThread().getName());
			return employees.stream().filter(employee -> d1.before(employee.getJoinedDate()))
					.collect(Collectors.toList());
		}).thenApply((employees) -> {
			System.out.println("Thread Filter Trainning - " + Thread.currentThread().getName());
			return employees.stream().filter(employee -> false == employee.isTrainningActivity())
					.collect(Collectors.toList());
		}).thenApply((employees) -> {
			System.out.println("Thread EmailId read - " + Thread.currentThread().getName());
			return employees.stream().map(Employee::getEmail).collect(Collectors.toList());
		}).thenAccept((emails) -> emails.forEach(email -> sendEmail(email)));
		 return future.get();
	}

	static void sendEmail(String email) {
		System.out.println("Sending Mail to-> " + email);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException, ParseException {
		// TODO Auto-generated method stub
		AsyncCall a = new AsyncCall();
		File jsonFile = new File("C:\\Users\\Shivam\\Documents\\user.json");
		File jsonFile2 = new File("C:\\Users\\Shivam\\Documents\\user2.json");
		File jsonFile3 = new File("C:\\Users\\Shivam\\Documents\\employee.json");
		// Simple Supply Async Call
		/*
		 * List<User> user1=a.getData(jsonFile); List<User> user2=a.getData(jsonFile2);
		 * user1.stream().forEach(System.out::println);
		 * user2.stream().forEach(System.out::println);
		 */
		/**
		 * If want to filter date SimpleDateFormat sdformat = new
		 * SimpleDateFormat("dd/MM/yyyy"); Date d1 = sdformat.parse("01/07/2022");
		 * List<Employee> employees2= employees.stream()
		 * .filter(employee->d1.before(employee.getJoinedDate()))
		 * .collect(Collectors.toList());
		 * employees2.stream().forEach(System.out::println);
		 */
		// Using thenApply,thenAccept,thenRun

		a.trainningRemainder(jsonFile3);

		// employees.stream().forEach(System.out::println);
	}

}
