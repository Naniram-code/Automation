package Tests.TestNG.RestFullTestNgGrouping.crud;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class DemoListener implements ITestListener, IExecutionListener //(Interface)
//public class DemoListener extends TestListenerAdapter// (class)
{ @Override
	public void onTestSuccess(ITestResult result) {   //Implements method
		System.out.println("on TestSuccess");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure");
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped");
	}
	@Override
	public void onStart(ITestContext context) {System.out.println("onStart");

	}
	@Override
	public void onFinish(ITestContext context) {System.out.println("onFinish");}
	@Override
	public void onExecutionStart() {
		System.out.println(System.currentTimeMillis()+"=Start Time");}
	@Override
	public void onExecutionFinish() {
		System.out.println(System.currentTimeMillis()+ "=Finish  Time");}}

