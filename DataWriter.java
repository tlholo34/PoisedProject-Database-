package com.company;

import java.sql.*;

/**DataWriter
 *
 * this class writes all the information on a local server
 */
public class DataWriter {
    //this method connects to the library database
    public static Connection dbConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "root",
                    "6081Tlholo"
            );
        }catch (Exception ex){
            return null;
        }
    }

    /**ProjectWriter
     *
     *connects to the database and writes the data into the project table
     *
     * @param ProjectID project number
     * @param PName project name
     * @param BuildingT building type
     * @param PAddress Project Address
     * @param ERFnum ERF number
     * @param PFee Project fee
     * @param PAmount Paid amount
     * @param DeadLine project deadline
     */
     public static void ProjectWriter(String ProjectID, String PName, String BuildingT, String PAddress, String ERFnum, double PFee, double PAmount, String DeadLine) {
         try {
             // Connect to the PoisePMS database
             Connection connection = dbConnection();
             // Create a direct line to the database for running our queries
             Statement statement = connection.createStatement();
             //we insert the new values into the table
             statement.executeUpdate("INSERT INTO Project VALUES (" +ProjectID+ "," + "'" + PName + "'" + "," + "'" + BuildingT+
                     "'" + "," + "'" + PAddress + "'" + "," + ERFnum + "," + PFee + "," + PAmount + "," + "'" + DeadLine + "')");
             statement.close();
             connection.close();
         } catch (Exception e) {
             System.out.println("error occurred here");
         }
     }

    /**ACCWriter
     * connects the database and
     * writes into the table depending on which ACC which was entered
     * and the table name entered
     *
     * @param name this is the Architect, contractor or Client
     * @param tableName the table where we adding data
     */
    public static void ACCWriter(ACC name, String tableName) {
        try {
            Connection connection = dbConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO "+tableName+" VALUES ("+name.getID_num()+","+ "'" +name.getName()+" "+name.getSurname()+ "'" +","+
            "'"+ name.getTelephoneNumber()+ "'" +","+"'"+name.getEmailAddress()+"'"+","+"'"+name.getPhysicalAddress()+"')");
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error occurred!");
        }
    }

    /**AlphaWriter
     *
     * connects to the database
     * writes the data into the alpha table the different ID numbers
     *
     * @param Project_num Project number
     * @param Client_num client number
     * @param Contractor_num contractor number
     * @param Architect_num Architect_num
     */
    public static void AlphaWriter(String Project_num,String  Client_num,String  Contractor_num, String  Architect_num){
        try{
            Connection connection = dbConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO alpha VALUES ("+Project_num+","+Client_num+","+Contractor_num+","+Architect_num+")");
            statement.close();
            connection.close();
        }catch (Exception e){
            System.out.println("Error occurred!");
        }
    }
}