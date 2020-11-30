package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Caregiver extends Person {
    private int cid;
    private String telephone;
    private List<Treatment> allTreatments = new ArrayList<Treatment>();

    /**
     *
     * constructs a patient from the given params.
     * @param firstName
     * @param surname
     * @param telephone
     * @param cid
     */
    public Caregiver(String firstName, String surname,  String telephone, int cid) {
        super(firstName, surname);
        this.cid = cid;
        this.telephone = telephone;
    }

    /**
     *
     * constructs a patient from the given params.
     * @param firstName
     * @param surname
     * @param telephone
     */

    public Caregiver(String firstName, String surname, String telephone) {
        super(firstName, surname);
        this.telephone = telephone;
    }

    /**
     *
     * @return caregiver id
     */
    public int getCid() {

        return cid;
    }



    /**
     *
     * @return telephone number
     */
    public String getTelephone() {
        return telephone;
    }



    /**
     *
     * @param cid
     */
    public void setCid(int cid) {
        this.cid = cid;
    }


    /**
     *
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * adds a treatment to the treatment-list, if it does not already contain it.
     * @param m Treatment
     * @return true if the treatment was not already part of the list. otherwise false
     */
    public boolean add(Treatment m) {
        if (!this.allTreatments.contains(m)) {
            this.allTreatments.add(m);
            return true;
        }
        return false;
    }

    /**
     *
     * @return string-representation of the patient
     */
    public String toString() {
        return "Caregiver" + "\nMNID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nTelephone: " + this.telephone;

    }
}