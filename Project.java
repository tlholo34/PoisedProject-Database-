package com.company;

/**Project class
 *
 * this class takes in information about projects using a constructor
 * also information about the Customer and Architect ans Contractor
 *
 */
public class Project{
    private String PName;
    private String PNumber;
    private String BuildingT;
    private String PAddress;
    private String ERFNum;
    private double PAmount;
    private double PFee;
    private String DeadLine;
    private ACC Customer;
    private ACC Architect;
    private ACC Contractor;

    public Project(String pName,String pNumber,String buildingT,String pAddress,String ERFNum,double pFee ,double pAmount ,String deadLine){
        this.PName = pName;
        this.PNumber = pNumber;
        this.BuildingT = buildingT;
        this.PAddress = pAddress;
        this.ERFNum = ERFNum;
        this.PAmount = pAmount;
        this.PFee = pFee;
        this.DeadLine = deadLine;
    }

    public ACC getCust() {
        return Customer;
    }

    public ACC getArchitect() {
        return Architect;
    }

    public ACC getContractor() {
        return Contractor;
    }

    public void setContractor(ACC contractor) {
        Contractor = contractor;
    }

    public void setArchitect(ACC architect) {
        Architect = architect;
    }

    public void setCust(ACC customer){
        Customer = customer;
    }

    public void setDeadLine(String d) {
        DeadLine = d;
    }

    public void setPAmount(double pAmount) {
        PAmount = pAmount;
    }

    public double getPFee() {
        return PFee;
    }

    public String getPName() {
        return PName;
    }

    public String getPAddress() {
        return PAddress;
    }

    public double getPAmount() {
        return PAmount;
    }

    public String getDeadLine() {
        return DeadLine;
    }

    public String getPNumber() {
        return PNumber;
    }

    public String getBuildingT() {
        return BuildingT;
    }

    public String getERFNum() {
        return ERFNum;
    }

    /**toString method
     *
     * uses all the information from Project
     * and places them in this format
     *
     * @return String format of the information
     */
    //this puts all info into a string
    public String toString(){
        String outtie = "Name of Project: "+ getPName();
        outtie += "\nProject number: "+ getPNumber();
        outtie += "\nBuilding Type: "+ getBuildingT();
        outtie += "\nProject Address: "+ getPAddress();
        outtie += "\nERF number: "+ getERFNum();
        outtie += "\nTotal amount Paid: R"+getPAmount();
        outtie += "\nProject Fee: R"+ getPFee();
        outtie += "\nDeadline: "+ getDeadLine();
        outtie+= "\n";

        //we return the output
        return outtie;
    }

}
