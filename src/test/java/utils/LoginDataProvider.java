package utils;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    private static  final Object[][] loginData = {{"standard_user", "secret_sauce"},
            {"locked_out_user", "secret_sauce"},
            {"problem_user", "secret_sauce"}};
    @DataProvider(name="LoginCredentials")
    public Object[][] loginCredentials(){
        return loginData;

    }
}
