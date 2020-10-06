package com.company;

/**ACC class
 *
 *this class takes in information about Architect Contractor and Client  using a constructor
 *
 */
public class ACC {

    private String ID_num;
    private String Name;
    private String Surname;
    private String EmailAddress;
    private String TelephoneNumber;
    private String PhysicalAddress;

    public ACC(String id_num,String name,String Surname,String emailAddress ,String telephoneNumber,String physicalAddress){
        this.ID_num = id_num;
        this.Name = name;
        this.Surname = Surname;
        this.EmailAddress = emailAddress;
        this.TelephoneNumber = telephoneNumber;
        this.PhysicalAddress = physicalAddress;
    }

    public String getName() {
        return Name;
    }

    public String getID_num() {
        return ID_num;
    }

    public String getSurname() {
        return Surname;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public String getPhysicalAddress() {
        return PhysicalAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        TelephoneNumber = telephoneNumber;
    }

    /**toString method
     *
     * takes the ACC information and places them in this format
     *
     * @return the the data in the format
     */
    //this puts all info into a string
    public String toString() {
        String outtie = "Name: " + getName();
        outtie += "\nSurname: " + getSurname();
        outtie += "\nEmail Address: " + getEmailAddress();
        outtie += "\nTelephone number: " + getTelephoneNumber();
        outtie += "\nPhysical Address: " + getPhysicalAddress();
        outtie += "\n";

        //we return the output
        return outtie;
    }
}