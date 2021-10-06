package Modules;

import Controllers.PopUpWindowController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Logic {
    public final ArrayList<LoginData> loginDataList = new ArrayList<>();
    /*Makes connection to database*/
    private ResultSet connectToDatabase(String query){
        try{
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/securepassword", "root", "");
            Statement myStat = myConn.createStatement();
            if(query.startsWith("UPDATE") || query.startsWith("DELETE"))myStat.executeUpdate(query);
            else return myStat.executeQuery(query);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
    public void databaseConnectionError(){
        this.createPopUpWindow("Database error!!!");
    }
    /*Gets data from database and inserts it into order list*/
    public boolean getDataFromDatabase(){
        try{
            ResultSet myRes = this.connectToDatabase("SELECT * FROM logindata");
            while(myRes.next())
                this.loginDataList.add(new LoginData(myRes.getInt("id"), myRes.getString("name"), myRes.getString("login"), myRes.getString("password")));
        }
        catch(Exception exc){
            this.databaseConnectionError();
            return false;
        }
        return true;
    }
    public void deCodePassword(String key, LoginData selectedItem){
        int sum = 0;
        String newPassword = "";
        for(LoginData item : this.loginDataList){
            if(item == selectedItem){
                for(int i=0;i<key.length();i++){
                    if(i%2==0)sum += Character.getNumericValue(key.charAt(i));
                    else sum -= Character.getNumericValue(key.charAt(i));
                }
                System.out.print("S:" + sum + " ");
                for(int i=0;i<item.getPassword().length();i++){
                    int asciiValue = item.getPassword().charAt(i) + sum;
                    char convertedChar = (char)asciiValue;
                    newPassword += convertedChar;
                }
                item.setDeCodedPassword(newPassword);
                System.out.print("P:"+ newPassword);
            }
        }
    }
    private void createPopUpWindow(String message){
        Window newWindow = new Window("PopUp Window", "/Views/PopUpWindow.fxml", "/Styles/style.css", 235, 92);
        newWindow.initWindow();
        PopUpWindowController scene4Controller = newWindow.getLoader().getController();
        scene4Controller.transferMessage(message, null);
        newWindow.showWindow();
    }
}
