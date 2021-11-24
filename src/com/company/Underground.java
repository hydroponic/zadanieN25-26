package com.company;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;


public class Underground {
    @SerializedName("stations")
    private Map<String, List<String>> stations;
    @SerializedName("lines")
    private List<Lines> lines;
    public Underground(List<Lines> lines, Map<String, List<String>> stations) {
        this.lines = lines;
        this.stations = stations;
    }

    public List<Lines> getLines() {
        return lines;
    }
}