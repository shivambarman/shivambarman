- 👋 Hi, It's Shivam Barman Repository
## Thread(Completable Future)

int core=Runtime.getRuntime().availableProcessors();
ExecutorService service=Executors.newFixedThreadPool(nThreads)
### Types of Executor Service 
1.	newFixedThreadPool
2.	newCachedThreadPool
3.	newSingleThreadExecutor
4.	newScheduledThreadPool

### service.execute();
service.execute(Runnable)
This execute method will use when Runnable Interface implemented in class it doesnot have any return type.

### service.submit();
service.submit(Runnable/Callable)
this submit method will use when Callable interface implemented in class it has return type

Thread2 t2=new Thread2();
Future<Integer> future=service.submit(t2);
//In between we can work anything here then we want output also
Int data=Future.get(); //it will block or we can say not it will wait till response will receive

### Completable Future
It will not block any code in Main Thread.
We can complete it process if it’s taking time which is not possible in future/ExecutorService
We can handle exception here which is not possible in future/ExecutorService.
Also we can combine two CompletableFuture to run in thread mode rather than we will wait for one thread to complete.



```
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
```
Using Executor Service for defining our own custom Executor otherwise it will work on single pool only.
It’s not mandatory to use executorservice in CompletableFuture

## thenApply() & thenApplyAsync()
These two methods using for calling another thread or dependent thread with each other but thenApplyAsync will work async without depend on other thread.

## thenAccept() & thenRun()
thenAccept(Consumer) accept as consumer functional interface and no return type also it will call any method.
thenRun(Runnable) behaves same as thenAccept but it required runnable method only

```
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
```
