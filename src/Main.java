//package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

//    public enum Held {
//        Wolverine,Doctor Strange, SpiderMan, Ironman, Hulk, Thor
//
//    }

    public static class Event{

        private int Id;
        private String Held;
        private String Antagonist;
        private String Konfrontationstyp;
        private String Ort;
        private LocalDate Datum;
        private double GlobalerEinfluss;


        public int getId() {
            return Id;
        }
        public void setId(int id) {
            this.Id = id;
        }
        public String getHeld() {
            return Held;
        }
        public void setHeld(String held) {
            this.Held = held;
        }

        public String getAntagonist() {
            return Antagonist;
        }
        public void setAntagonist(String antagonist) {
            this.Antagonist = antagonist;
        }
        public String getKonfrontationstyp() {
            return Konfrontationstyp;
        }
        public void setKonfrontationstyp(String konfrontationstyp) {
            this.Konfrontationstyp = konfrontationstyp;
        }
        public String getOrt() {
            return Ort;
        }
        public void setOrt(String ort) {
            this.Ort = ort;
        }
        public LocalDate getDatum() {
            return Datum;
        }
        public void setDatum(LocalDate datum) {
            this.Datum = datum;
        }
        public double getGlobalerEinfluss() {
            return GlobalerEinfluss;
        }
        public void setGlobalerEinfluss(double globalerEinfluss) {
            this.GlobalerEinfluss = globalerEinfluss;
        }

        public static List<Event> readEvents(String filePath) throws Exception {
            List<Event> events = new ArrayList<>();
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
                String line;
                reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\t");
                    if (parts.length < 7) {
                        String nextLine = reader.readLine();
                        if (nextLine != null) {
                            parts = (line + " " + nextLine).split("\t");
                        }
                    }
                    Event event = new Event();
                    event.setId(Integer.parseInt(parts[0]));
                    event.setHeld(parts[1]);
                    event.setAntagonist(parts[2]);
                    event.setKonfrontationstyp(parts[3]);
                    event.setOrt(parts[4]);
                    event.setDatum(LocalDate.parse(parts[5]));
                    event.setGlobalerEinfluss(Double.parseDouble(parts[6]));
                    events.add(event);
                }
            }
            return events;
        }

        /**
         * javadoc
         */
       public static void numberOfHeldenWithBigGlobalEinfluss(List<Event> events, double threshold) {
           long count = events.stream()
                   .filter(event -> event.getGlobalerEinfluss() > threshold)
                   .map(Event::getHeld)
                   .distinct()
                   .count();
           System.out.println("Number of unique Helden with Globaler Einfluss greater than " + threshold + ": " + count);
       }
       }



    }
}


