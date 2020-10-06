package com.company;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PoisedProject {
    /**
     * method ValidPhone()
     * validates the users phone number
     * <p>
     * by checking the length of the phone number
     * checking the first number of the Phone number
     * returns a boolean if true the number is valid
     *
     * @param number is the users phone number
     * @return boolean
     */

    //method validates phone number
    public static Boolean ValidPhone(String number) {
        boolean flag = true;
        try {
            if (number.charAt(0) == '0') {
                if (number.length() == 10) {
                    long p = Long.parseLong(number);
                } else {
                    flag = false;
                }
            } else if (number.substring(0, 3).equalsIgnoreCase("+27")) {
                if (number.length() == 12) {
                    long p = Long.parseLong(number.substring(1));
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * method ValidInput()
     * validates the users input by checking
     * common errors we preventing
     * <p>
     * by making sure the input does not contain any numbers
     * making sure the input is not an number
     * and making sure the input is not too short
     *
     * @param p users input
     * @return "ans" is the the validated input
     */
    //method makes sure that only letters entered
    //and input length is not below 2
    public static String ValidInput(String p) {
        System.out.println(p);
        Scanner input = new Scanner(System.in);
        boolean num = input.hasNextInt();
        while (num) {
            System.out.println("Invalid entry");
            System.out.println(p);
            input.nextLine();
            num = input.hasNextInt();
        }
        String ans = input.nextLine();
        while (ans.length() < 2 || num) {
            System.out.println("Data entered is too short!");
            System.out.println(p);
            num = input.hasNextInt();
            ans = input.nextLine();
        }
        while (ans.matches(".*\\d+.*")) {
            System.out.println("Invalid entry");
            System.out.println(p);
            ans = input.nextLine();
        }

        return ans;
    }

    /**
     * method getACCInfo()
     * Asks the user for appropriate information
     * needed for Customer Contractor and Architect
     *
     * @param x represents the person the information belongs to.
     * @return a new object with the corresponding information
     */
    public static ACC getACCInfo(String x) {
        //we ask the user to enter data for either A.C.C
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a 4 digit " + x + " ID here: ");
        boolean num = in.hasNextInt();
        while (!num) {
            System.out.println("Invalid ID num!");
            System.out.println("Enter a 4 digit " + x + " ID here: ");
            in.nextLine();
            num = in.hasNextInt();
        }
        String ID_num = in.nextLine();
        while (ID_num.length() != 4) {
            System.out.println("ID is not 4 digits!");
            System.out.println("Enter a 4 digit " + x + " ID here: ");
            ID_num = in.nextLine();
        }

        String name = ValidInput("Enter  " + x + " Name here:");

        String Surname = ValidInput("Enter  " + x + " Surname here: ");

        System.out.println("Enter " + x + " Email Address here: ");
        Scanner input = new Scanner(System.in);
        String EmailAdre = input.nextLine();
        //we make sure email contains .com and @
        while (!EmailAdre.contains(".com")) {
            System.out.println("Your email is invalid!");
            System.out.println("Enter " + x + " Email Address here: ");
            EmailAdre = input.nextLine();
            while (!EmailAdre.contains("@")) {
                System.out.println("Your email is invalid!");
                System.out.println("Enter " + x + " Email Address here: ");
                EmailAdre = input.nextLine();
            }
        }

        System.out.println("Enter " + x + " telephone number here: ");
        input = new Scanner(System.in);
        String phoneNumber = input.nextLine();
        //while the phoneNumber is not valid
        while (!ValidPhone(phoneNumber)) {
            //we print this
            System.out.println("Invalid phone number!");
            System.out.println("Enter " + x + " telephone number here: ");
            phoneNumber = input.nextLine();
        }

        System.out.println("Enter " + x + " physical Address here: ");
        input = new Scanner(System.in);
        String physicalAddress = input.nextLine();
        //while user input length less than 6
        while (physicalAddress.length() < 6) {
            System.out.println("Address too short!");
            System.out.println("Enter " + x + " physical Address here: ");
            physicalAddress = input.nextLine();
        }
        return new ACC(ID_num, name, Surname, EmailAdre, phoneNumber, physicalAddress);
    }

    /**
     * method getProjectInfo()
     * <p>
     * implements method getACCInfo()
     * and prints all information
     * then asks the user for Information about the project
     * also prevents user from entering Invalid input
     *
     *after getting all the data we add it to the databases using the
     * DataWriter
     */
    //this method gets all the project info
    public static void getProjectInfo() {
        //we assign x to these people
        //and ask the user the data required
        ACC Customer = getACCInfo("Customer");
        ACC Contractor = getACCInfo("Contractor");
        ACC Architect = getACCInfo("Architect");

        //we print the data for each person
        System.out.println("Customer\n" + Customer);
        System.out.println("===========================" + "\nContractor\n" + Contractor);
        System.out.println("===========================" + "\nArchitect\n" + Architect);

        //we start getting project data

        System.out.println("Enter a 7 digit Project number here: ");
        Scanner input = new Scanner(System.in);
        String PNumber = input.nextLine();

        boolean var = true;
        while (PNumber.length() != 7 || var) {
            try {
                //we check the PNumber length
                if (PNumber.length() != 7) {
                    //if its not 7 we print this
                    System.out.println("Invalid Project number!");
                    System.out.println("Enter a 7 digit Project number here: ");
                    PNumber = input.nextLine();
                }
                //we parse to int to check if it only contains numbers
                int num = Integer.parseInt(PNumber);

                var = false;

            } catch (Exception e) {
                //if it fails we print this
                var = true;
                System.out.println("Invalid Project number!");
                System.out.println("Enter a 7 digit Project number here: ");
                PNumber = input.nextLine();
                //and we run again
            }
        }
        String PName = ValidInput("Enter Project name here or enter null: ");

        String BuildingT = ValidInput("Enter Building type here: ");

        System.out.println("Enter Project Address here: ");
        input = new Scanner(System.in);
        String PAddress = input.nextLine();
        //while user input length is less than 6
        while (PAddress.length() < 6) {
            System.out.println("Address too short!");
            System.out.println("Enter Project Address here: ");
            PAddress = input.nextLine();
        }

        System.out.println("Enter ERF number here: ");
        input = new Scanner(System.in);
        //while input doesn't have int
        while (!input.hasNextInt()) {
            System.out.println("Invalid entry!");
            System.out.println("Enter ERF number here: ");
            input.nextLine();
        }
        String ERFnum = input.nextLine();

        System.out.println("Enter Project Cost here: ");
        input = new Scanner(System.in);
        //while input doesn't have double
        while (!input.hasNextDouble()) {
            System.out.println("Invalid amount!");
            System.out.println("Enter Project Cost here: ");
            input.nextLine();
        }
        double PFee = input.nextDouble();

        System.out.println("Enter Paid amount");
        input = new Scanner(System.in);
        //while input doesn't have double
        while (!input.hasNextDouble()) {
            System.out.println("Invalid amount!");
            System.out.println("Enter Paid amount");
            input.nextLine();
        }
        double PAmount = input.nextDouble();
        //we checking if the paid amount is bigger than project amount
        while (PAmount > PFee) {
            System.out.println("Paid amount greater than Project cost!");
            System.out.println("Enter Paid amount");
            PAmount = input.nextDouble();
        }
        //we print the current date
        LocalDate CurrentDate = LocalDate.now();
        System.out.println("\nCurrent date ---> " + CurrentDate);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        boolean validFlag = true, datesComp = false;
        String Deadline = null;
        while (validFlag || datesComp) {
            System.out.println("Enter Deadline Here in this format ---> (yyyy/mm/dd): ");
            input = new Scanner(System.in);
            Deadline = input.nextLine();

            try {
                datesComp = false;
                //we check if date is in the right format
                //we also check if the date is a valid date
                LocalDate InputDate = LocalDate.parse(Deadline, dateFormat);
                validFlag = false;
                //we check if the user inputted date is not before the current date
                datesComp = InputDate.isBefore(CurrentDate);
            } catch (Exception e) {
                //while there is an error we set to validFlag true
                validFlag = true;
            }
            //if validFlag is true
            if (validFlag) {
                //we print this error message
                System.out.println("Invalid Date");
            }
            //if datesComp is true
            if (datesComp) {
                //we print this error message
                System.out.println("Deadline is before current date.");
            }

        }
        //if the project name is null we print this
        if (PName.equalsIgnoreCase("null")) {
            PName = BuildingT + " " + Customer.getSurname();
        }

        Project p = new Project(PName, PNumber, BuildingT, PAddress, ERFnum,PFee , PAmount, Deadline);
        //we update the Customer Architect and Contractor
        p.setCust(Customer);
        p.setArchitect(Architect);
        p.setContractor(Contractor);
        System.out.println(p);

        //we print the results into the different tables in the database
        DataWriter.ProjectWriter(PNumber, PName, BuildingT, PAddress, ERFnum,PFee , PAmount, Deadline);
        DataWriter.ACCWriter(p.getCust(), "client");
        DataWriter.ACCWriter(p.getArchitect(), "architect");
        DataWriter.ACCWriter(p.getContractor(), "contractor");
        DataWriter.AlphaWriter(PNumber, p.getCust().getID_num(), p.getContractor().getID_num(), p.getArchitect().getID_num());


    }
    //we create this for the connection to the PoisePMS database
    public static Connection dbConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "root",
                    "6081Tlholo"
            );
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Main
     * we show a menu where the user can view all projects, uncompleted projects, overdue projects
     * add new project or edit projects
     * <p>
     * if user picks overdue projects we show all the overdue tasks from database
     * if user picks uncompleted projects we show all the uncompleted tasks from database
     * if users picks view all projects we implement method form DataReader class that displays all projects in database
     * if the user picks to add new project we use the method getProjectInfo which then adds info into database
     * <p>
     * if user enters quit in first menu the project ends
     * <p>
     * if the user enters s we ask which project they would like to see
     * then we display a menu which asks the user which data they would like to see between the ACC
     * else we exit to the main menu
     *<p>
     * if user picks edit projects we ask the user which project they would like to edit
     * we start by showing the project number and the project name
     * and we ask the user the enter the project number of the project they want to edit
     * <p>
     * in the second menu we ask if the user would like to change due date of the task, change the total amount paid
     * update contractors contact details or finalize project.
     * <p>
     * if users picks change due date we ask for a valid date and update project in database
     * if the user picks change total amount paid we show the outstanding amount than ask the user for the amount paid
     * if the user picks update contractors contact details we ask for the email and phone number and update project
     * in database
     * if the user picks finalize project, if the project cost is the same as paid amount we print the project number
     * and print the project is finalized else if the project cost is less than paid amount we print the Invoice also write Invoice in
     * file
     * <p>
     * when the user picks quit in the second menu it takes user to the first menu
     * after entering quit in second menu we implement FileWriter write and updated Project into the Project Info
     *
     * @param
     */
    //main
    public static void main(String[] args) {

        String op;
        do {
            System.out.println("e -Edit project" +
                    "\na -Add new project" +
                    "\nv -View all projects" +
                    "\nin -View Incomplete tasks" +
                    "\no -View overdue projects" +
                    "\ns -select project " +
                    "\nquit");
            Scanner Menu1 = new Scanner(System.in);
            op = Menu1.nextLine();

            if (op.equalsIgnoreCase("v")) {
                //we use the method to display all projects
                DataReader.ViewAllProjects();

            } else if (op.equalsIgnoreCase("a")) {
                //we get all the project info
                //and add it to the database
                getProjectInfo();

            } else if (op.equalsIgnoreCase("o")) {
                //we print the overdue projects
                DataReader.getOverdueProject();

            } else if (op.equalsIgnoreCase("in")) {
                System.out.println("Incomplete tasks:");
                //if the project amount is != the project cost
                //we print the Incomplete task
                DataReader.viewIncompleteProject();

            } else if (op.equalsIgnoreCase("quit")) {
                System.out.println("thank you");

            } else if (op.equalsIgnoreCase("s")) {
                //we print all the projects names and a number for the user to select
                DataReader.getProjectInfo();
                //array list that holds all the project numbers
                ArrayList<Integer> x = DataReader.ListOFProjects();

                System.out.println("which project would you like to view");
                System.out.println("enter Project number here: ");
                Scanner wp = new Scanner(System.in);

                boolean var = true;
                String op1 = wp.nextLine();
                while (var) {
                    try{
                        //we cast op1 to an int
                        //if it fails it goes to the catch
                        Integer.parseInt(op1);
                        //check if op1 is a valid choose
                        if(op1.length() != 7) {
                            System.out.println("Project number entered is not equal to 7!");
                            System.out.println("enter number here: ");

                            //want to check if i can get all the project numbers here
                        }
                        //else we exit the while loop by making var false
                        else {
                            var = false;
                        }
                    }catch (Exception e){
                        //if it fails we print this
                        System.out.println("invalid option");
                        System.out.println("enter number here: ");
                        var = true;
                        //and we run again
                    }
                    if(var){
                        wp = new Scanner(System.in);
                        op1 = wp.nextLine();
                    }
                    else {
                        //we checking if the project number exists
                        var = true;
                        for (int num: x) {
                            if (Integer.parseInt(op1) == num) {
                                var = false;
                            }
                        }
                        if (var){
                            System.out.println("Project number doesn't exist");
                            System.out.println("enter number here: ");
                            wp = new Scanner(System.in);
                            op1 = wp.nextLine();
                        }
                    }
                }
                String option1;
                do {
                    //we make a menu with options
                    System.out.println("co- view contractor info." +
                            "\nc- view client info." +
                            "\na- view architect info." +
                            "\n quit");
                    Scanner menuOp2 = new Scanner(System.in);
                    option1 = menuOp2.nextLine();

                    if (option1.equalsIgnoreCase("co")) {
                        //we display contractor details
                        DataReader.ACCReader(Integer.parseInt(op1),"contractor");
                    }else if (option1.equalsIgnoreCase("c")){
                        //we display client details
                        DataReader.ACCReader(Integer.parseInt(op1),"client");
                    }else if (option1.equalsIgnoreCase("a")){
                        //we display architect details
                        DataReader.ACCReader(Integer.parseInt(op1),"architect");
                    }
                }while (!option1.equalsIgnoreCase("quit"));
            } else if (op.equalsIgnoreCase("e")) {
                //we print all the projects names and a number for the user to select
                DataReader.getProjectInfo();
                //array list that holds all the project numbers
                ArrayList<Integer> x = DataReader.ListOFProjects();

                System.out.println("which project would you like to edit");
                System.out.println("enter Project number here: ");
                Scanner wp = new Scanner(System.in);

                boolean var = true;
                String op1 = wp.nextLine();
                while (var) {
                    try{
                        //we cast op1 to an int
                        //if it fails it goes to the catch
                        Integer.parseInt(op1);
                        //check if op1 is a valid choose
                        if(op1.length() != 7) {
                            System.out.println("Project number entered is not equal to 7!");
                            System.out.println("enter number here: ");

                            //want to check if i can get all the project numbers here
                        }
                        //else we exit the while loop by making var false
                        else {
                            var = false;
                        }
                    }catch (Exception e){
                        //if it fails we print this
                        System.out.println("invalid option");
                        System.out.println("enter number here: ");
                        var = true;
                        //and we run again
                    }
                    if(var){
                        wp = new Scanner(System.in);
                        op1 = wp.nextLine();
                    }
                    else {
                        //we checking if the project number exists
                        var = true;
                        for (int num: x) {
                            if (Integer.parseInt(op1) == num) {
                                var = false;
                            }
                        }
                        if (var){
                            System.out.println("Project number doesn't exist");
                            System.out.println("enter number here: ");
                            wp = new Scanner(System.in);
                            op1 = wp.nextLine();
                        }
                    }
                }

                DataReader.selectProject(Integer.parseInt(op1));
                String option;
                do {
                    //we make a menu with options
                    System.out.println("c- change due date of the task." +
                            "\np- change the total amount paid to date." +
                            "\nu- update contractors contact details." +
                            "\nf- finalize project." +
                            "\n quit");
                    Scanner menuOp = new Scanner(System.in);
                    option = menuOp.nextLine();

                    //if user chooses options c
                    if (option.equalsIgnoreCase("c")) {
                        LocalDate CurrentDate = LocalDate.now();
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        boolean validFlag = true, datesComp = false;

                        String Deadline = null;
                        while (validFlag || datesComp) {
                            //we print the current date
                            System.out.println("\nCurrent date ---> " + CurrentDate);
                            //we ask the user for the new deadline
                            System.out.println("Enter new Project deadline in this format (yyyy-mm-dd): ");
                            Scanner input = new Scanner(System.in);
                            Deadline = input.nextLine();
                            try {
                                datesComp = false;
                                //we check if date is in the right format
                                //we also check if the date is a valid date
                                LocalDate InputDate = LocalDate.parse(Deadline, dateFormat);
                                validFlag = false;
                                //we check if the user inputted date is not before the current date
                                datesComp = InputDate.isBefore(CurrentDate);
                            } catch (Exception e) {
                                //while there is an error we set to validFlag true
                                validFlag = true;
                            }
                            //if validFlag is true
                            if (validFlag) {
                                //we print this error message
                                System.out.println("Invalid Date");
                            }
                            //if datesComp is true
                            if (datesComp) {
                                //we print this error message
                                System.out.println("Deadline is before current date.");
                            }
                        }

                        //we update the deadline
                        try {
                            Connection connection = dbConnection();
                            Statement statement = connection.createStatement();
                            int rowsAffected = statement.executeUpdate("update project set Deadline = "+Deadline+" where project_num = "+op1);
                            System.out.println("Query complete, " + rowsAffected + " row updated. Project deadline updated.");
                            statement.close();
                            connection.close();
                        } catch (Exception e) {
                            System.out.println("Table update failed!");
                        }

                        //else if option is p
                    } else if (option.equalsIgnoreCase("p")) {
                        double am1, am2;
                        try {
                            // Connect to the library_db database
                            Connection connection = dbConnection();
                            // Create a direct line to the database for running our queries
                            Statement statement = connection.createStatement();
                            ResultSet results;

                            // we select the coulombs we are going to use
                            results = statement.executeQuery("select Project_fee ,Paid_amount from Project where Project_num = "+op1);
                            //we print all the coulombs info
                            while (results.next()) {
                                am1 = results.getInt("Project_fee");
                                am2 = results.getInt("Paid_amount");
                                double am3 = am1-am2;
                                //we show the user the outstanding Project cost
                                System.out.println("Project fee: " +  am1 + " \t Paid amount: " + am2+"\nOutstanding amount: "+am3);
                            }
                            results.close();
                            statement.close();
                            connection.close();
                        } catch (Exception e) {
                            System.out.println("error occurred.");
                        }
                        try {
                            // Connect to the library_db database
                            Connection connection = dbConnection();
                            // Create a direct line to the database for running our queries
                            Statement statement = connection.createStatement();
                            ResultSet results1;

                            // we select the coulombs we are going to use
                            results1 = statement.executeQuery("select Project_fee ,Paid_amount from Project where Project_num = "+op1);
                            //we ask the user for paid amount
                            System.out.println("Enter paid amount here: ");
                            Scanner ans = new Scanner(System.in);
                            while (!ans.hasNextDouble()) {
                                System.out.println("Invalid Amount");
                                System.out.println("Enter Paid amount");
                                ans.nextDouble();
                            }
                            double SecPAmount = ans.nextDouble();
                            //We check if the Second Paid amount is greater than outstanding Project cost
                            while (SecPAmount > results1.getInt("Project_Fee")) {
                                System.out.println("Paid amount greater than Project Cost!");
                                System.out.println("Enter Paid amount");
                                SecPAmount = ans.nextDouble();
                            }

                            int rowsAffected = statement.executeUpdate("update project set Paid_amount = "+results1.getInt("Paid_amount")+
                                    " where project_num = "+op1);
                            System.out.println("Query complete, " + rowsAffected + " row updated. Paid amount updated.");

                            results1.close();
                            statement.close();
                            connection.close();
                        }catch (Exception r){
                            System.out.println("Table update failed!");
                        }

                    //if user chooses f
                    } else if (option.equalsIgnoreCase("f")) {
                        try {
                            Connection connection = dbConnection();
                            Statement statement = connection.createStatement();
                            ResultSet results;
                            results = statement.executeQuery("select Project_num from project where Project_fee = Paid_amount and Project_num = "+op1);
                           //if Project fee == amount paid
                            if ( results.next()){
                                System.out.println("Project: "+ results.getInt("project_num")+" Has been finalised.");
                            }
                            else{
                                ResultSet result = statement.executeQuery("select Project.Project_fee,Project.Paid_amount, project.project_num, client.client_num, client.name," +
                                        "client.phone_num, client.email, client.physical_address" +
                                        "from project, client, alpha where project.project_num = "+op1+" and project.project_num = alpha.project_num and Alpha.client_num = client.client_num");

                                while (result.next()) {
                                    float am1 =result.getFloat("Project_fee");
                                    float am2= result.getFloat("Paid_amount");
                                    float am3 = am1-am2;
                                    System.out.println("Project number: "+ result.getInt("project_num")+"\nCustomer Name: "+result.getString("Name")+
                                    "\n\t\tPhone Number: "+result.getString("phone_num")+"\n\t\tPhysical Address:"+result.getString("physical_address")+
                                    "\n\t\tEmailAddress: "+result.getString("email") +"\nOutstanding amount: R"+am3+"\n");

                                    //data to be written to the Invoice.txt file
                                    String invData = "Project number: "+ result.getInt("project_num")
                                            +"Customer Name: " + result.getString("name")
                                            + "\n\t\tPhone Number: " + result.getString("phone_num")
                                            + "\n\t\tPhysicalAddress: " + result.getString("physical_address")
                                            + "\n\t\tEmailAddress: " + result.getString("email")
                                            + "\nOutstanding amount: R"+am3  ;
                                    // we write to the file
                                    InvoiceWriter d = new InvoiceWriter();
                                    d.Invoice(invData);
                                }
                            }
                            results.close();
                            statement.close();
                            connection.close();
                        } catch(Exception e){
                            System.out.println("Error occurred 1");
                        }

                        //if user chooses u
                        } else if (option.equalsIgnoreCase("u")) {
                            //we ask for the contractors new email
                            System.out.println("Enter new Email Address here: ");
                            Scanner Answer = new Scanner(System.in);
                            String NewEmailAddress = Answer.nextLine();
                            //we make sure New email contains @ and .com
                            while (!NewEmailAddress.contains(".com")) {
                                System.out.println("Your email is invalid!");
                                System.out.println("Enter new Email Address here: ");
                                NewEmailAddress = Answer.nextLine();
                                while (!NewEmailAddress.contains("@")) {
                                    System.out.println("Your email is invalid!");
                                    System.out.println("Enter new Email Address here: ");
                                    NewEmailAddress = Answer.nextLine();
                                }
                            }

                            //we ask for the contractors new phone number
                            System.out.println("Enter new telephone number here: ");
                            Answer = new Scanner(System.in);
                            String NewPhoneNumber = Answer.nextLine();
                            while (!ValidPhone(NewPhoneNumber)) {
                                System.out.println("Invalid Phone number.");
                                System.out.println("Enter new telephone number here: ");
                                NewPhoneNumber = Answer.nextLine();
                            }
                            //we update the contractors details
                        try {
                            Connection connection = dbConnection();
                            Statement statement = connection.createStatement();
                            ResultSet results;
                            results = statement.executeQuery("select Alpha.Contractor_num from contractor,alpha,project" +
                                    " where project.project_num = "+op1+" and project.project_num = alpha.project_num and contractor.contractor_num = alpha.contractor_num");

                            statement.executeUpdate("update contractor set email = "+NewEmailAddress+
                                    "where contractor_num ="+results.getInt("Contractor_num"));

                            System.out.println("Contractor "+results.getInt("Contractor_num") + "Details have been updated.");

                            results.close();
                            statement.close();
                            connection.close();
                        } catch (Exception e) {
                            System.out.println("Error occurred!");
                        }
                    }else if (option.equalsIgnoreCase("quit")) {
                            System.out.println("thank you");
                    }
                } while (!option.equalsIgnoreCase("quit"));
            }
        }while (!op.equalsIgnoreCase("quit")) ;
    }
}