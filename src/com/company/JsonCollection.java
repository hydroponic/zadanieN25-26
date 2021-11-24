package com.company;

import java.util.ArrayList;
import java.util.List;

class JsonCollection {

    ArrayList<Lines> LINES=new ArrayList<Lines>();
    ArrayList<Stations> STATIONS=new ArrayList<Stations>();

    public void setLines( ArrayList<Lines> emps ) {
        this.LINES = emps;
    }

    public ArrayList<Lines> getLines() {
        return this.LINES;
    }
    public ArrayList<Stations> getSTATIONS() {
        return this.STATIONS;
    }

    public void addLines( Lines h ) {
        this.LINES.add( h );
    }
    public void addStations(Stations s) {
        this.STATIONS.add(s);
    }



}
