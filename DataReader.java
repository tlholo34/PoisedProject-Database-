
package com.company;

import java.sql.*;
import java.util.ArrayList;

/**DataReader
 *
 * this class reads from the database
 */

public class DataReader {
    //arraylist that handles the project numbers
   static ArrayList<Integer> ProjectNumbers= new ArrayList<>();

    public static Connection dbConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "root",
                    "6081Tlholo"
            );
        } catch (Exception ex) {
            System.out.println("Failed to create a database connection!");
            return null;
        }
    }

    public static ArrayList<Integer> ListOFProjects(){
       return  ProjectNumbers;
    }

    /**ViewAllProjects
     *
     * reads everything from project table
     * and prints the data
     *
     */
    public static void ViewAllProjects(){
        try {
            Connection connection = dbConnection();
            Statement statement = connection.createStatement();
            ResultSet results;
            results = statement.executeQuery("select * from project");
            while (results.next()) {
                System.out.println("Project number: " + results.getInt("project_num") + "\nProject name: " + results.getString("project_name") +
                "\nBuilding type: " + results.getString("Building_type") + "\nPhysical Address:" + results.getString("Physical_address") +
                "\nERF number: " + results.getInt("ERF_num") + "\nProject Fee: " + results.getString("Project_fee") + "\nPaid Amount: " +
                results.getInt("Paid_amount") + "\nDeadline: " + results.getDate("Deadline")+"\n");
            }
            results.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println("Error occurred");
        }
    }

    /**getProjectInfo()
     *
     * reads from project table
     * and prints project number and project name
     *
     */
    public static void getProjectInfo() {
        try {
            // Connect to the library_db database
            Connection connection = dbConnection();
            // Create a direct line to the database for running our queries
            Statement statement = connection.createStatement();
            ResultSet results;

            // we select the coulombs we are going to use
            results = statement.executeQuery("select Project_num,Project_name from Project");
            //we print all the coulombs info
            while (results.next()) {
                int pnum = results.getInt("project_num");
                ProjectNumbers.add(pnum);
                System.out.println("Project number: " + pnum + " \t Project name: " + results.getString("Project_name"));
            }
            results.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("error occurred.");
        }
    }
    /**getOverdueProject()
     *
     * reads from tables and if the project deadline is past the current date
     * it prints data needed to print the overdue projects
     *
     */
    public static void getOverdueProject() {
        try {
            Connection connection = dbConnection();
            Statement statement = connection.createStatement();
            ResultSet results;
            results = statement.executeQuery("select project.project_num,project.project_name,client.client_num, client.name,client.phone_num,client.email,client.physical_address "+
                    "from project, client, alpha " +
                    "where deadline < CURRENT_DATE() and project.project_num = alpha.project_num and Alpha.client_num = client.client_num");
            if (results.next()) {
                while (results.next()) {
                    System.out.println("Project number: " + results.getInt("project.project_num") + "\nProject name: " + results.getString("project.project_name")
                            +"\nClient number: " + results.getInt("client.client_num") + "\nClient Name: " +results.getString("client.name") + "\n");                }
            } else {
                System.out.println("No overdue Projects!");
            }
            results.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error occurred!");
        }
    }

    /**selectProject
     *
     * uses the entered number and prints the project data needed
     *
     * @param ProjectNum is the project number that's was entered
     */
    public static void selectProject(int ProjectNum){
        try {
            Connection connection = dbConnection();
            Statement statement = connection.createStatement();
            ResultSet results;
            results = statement.executeQuery("select * from project where Project_num = "+ProjectNum);
            while (results.next()) {
                System.out.println("Project number: "+ results.getInt("project_num")+"\nProject name: "+results.getString("project_name")+
                        "\nBuilding type: "+results.getString("Building_type")+"\nPhysical Address:"+results.getString("Physical_address")+
                        "\nERF number: "+results.getInt("ERF_num")+"\nProject Fee: "+results.getInt("Project_fee")+"\nPaid Amount: "+
                        results.getInt("Paid_amount")+"\nDeadline: "+results.getDate("Deadline"));
            }
            results.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println("Error occurred");
        }
    }
    /**viewIncompleteProject()
     *
     * reads from tables and if the project paid amount is less than project fee and
     * it prints data needed to print the Incomplete projects
     *
     */
    public static void viewIncompleteProject(){
        try {
            Connection connection = dbConnection();
            Statement statement = connection.createStatement();
            ResultSet results;
            results = statement.executeQuery("select project_num,project_name from project where Paid_amount < Project_fee");
            if (results.next()) {
                while(results.next()) {
                    System.out.println("Project number: " + results.getInt("project_num") + "\tProject name: " + results.getString("project_name")+"\n");
                }
            }else {
                System.out.println("All projects complete!");
            }
            results.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println("Error occurred");
        }
    }
    public static void ACCReader(int projectNumber, String tableName){
        try {
            Connection connection = dbConnection();
            Statement statement = connection.createStatement();
            ResultSet results;
            results = statement.executeQuery("select project.project_num,"+tableName+"."+tableName+"_num,"+tableName+".name,"+
                    tableName+".phone_num, "+tableName+".email, "+tableName+".physical_address " +
                    "from project,"+tableName+", alpha " +
                    "where Project.project_num = "+projectNumber+" and project.project_num = alpha.project_num and Alpha."+tableName+"_num = "+tableName+"."+tableName+"_num");
            while(results.next()) {
                System.out.println("Project number: " + results.getInt("Project_num") + "\n" + tableName + " number: " + results.getInt(tableName + "_num") + "\n" +
                tableName + " name: " + results.getString("name") + "\nphone number: " + results.getString("phone_num") + "\nemail: " + results.getString("email") +
                "\nPhysical address: " + results.getString("physical_address"));
            }
            results.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            System.out.println("table reading failed!");
        }
    }
}