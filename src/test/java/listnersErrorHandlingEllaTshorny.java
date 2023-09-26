

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class listnersErrorHandlingEllaTshorny implements ITestListener {


    public void onFinish(ITestContext arg0) {
        System.out.println("Test is finished !");


    }


    public void onStart(ITestContext arg0) {
        System.out.println("Test is starting");
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub				

    }


    public void onTestFailure(ITestResult arg0) {
        System.out.println("Test failed !");


    }


    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub				

    }


    public void onTestStart(ITestResult arg0) {
        System.out.println("Test successfully Started ! ");

    }


    public void onTestSuccess(ITestResult arg0) {
        System.out.println("Test successfully Ended !");

    }
}