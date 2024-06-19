package com.udesc.model;

import java.util.ArrayList;
import java.util.List;

import com.udesc.io.Viewable;

public class Employee implements Viewable {
    private String             name;
    private List<Certificate>  certificates = new ArrayList<Certificate>();
    private List<Absence>      absences     = new ArrayList<Absence>();
    private final TimeTracking timeTracking = new TimeTracking();

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public TimeTracking getTimeTracking() {
        return timeTracking;
    }

    @Override
    public String briefView() {
        return name;
    }

    @Override
    public String view() {
        return String.format(
            "Nome                 | %s \n" +
            "Quantidade atestados | %s \n" +
            "Quantidade faltas    | %s \n" +
            "Apontamentos         | %s \n" +
            "Horas trabalhadas    | %s \n",
            name,
            certificates.size(),
            absences.size(),
            timeTracking.size(),
            timeTracking.total());
    }
}
