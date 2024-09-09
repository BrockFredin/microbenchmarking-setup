package com.example.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class MyBenchmark {

    private ObjectMapper objectMapper; // Jackson ObjectMapper instance
    private String complicatedJson; // Complicated JSON to parse

    // Setup method to initialize the ObjectMapper and JSON string
    @Setup(Level.Trial)
    public void setUp() {
        objectMapper = new ObjectMapper();  // Initialize the ObjectMapper
        complicatedJson = "{ " +
        "\"person\": { " +
            "\"name\": \"John\", " +
            "\"age\": 30, " +
            "\"gender\": \"male\", " +
            "\"address\": { " +
                "\"street\": \"123 Main St.\", " +
                "\"city\": \"New York\", " +
                "\"state\": \"NY\", " +
                "\"postalCode\": \"10001\", " +
                "\"coordinates\": { " +
                    "\"latitude\": 40.7128, " +
                    "\"longitude\": -74.0060 " +
                "} " +
            "}, " +
            "\"phones\": [ " +
                "{ \"type\": \"home\", \"number\": \"212-555-1234\", \"verified\": true }, " +
                "{ \"type\": \"work\", \"number\": \"646-555-4567\", \"extension\": 123, \"verified\": false }, " +
                "{ \"type\": \"mobile\", \"number\": \"123-456-7890\", \"extension\": null, \"verified\": true }" +
            "], " +
            "\"email\": \"john.doe@example.com\", " +
            "\"employment\": { " +
                "\"company\": \"TechCorp\", " +
                "\"position\": \"Software Engineer\", " +
                "\"startDate\": \"2015-06-15\", " +
                "\"endDate\": null, " +
                "\"salary\": { " +
                    "\"amount\": 120000, " +
                    "\"currency\": \"USD\" " +
                "}, " +
                "\"projects\": [ " +
                    "{ \"name\": \"Project X\", \"status\": \"completed\", \"teamSize\": 5, \"durationMonths\": 12 }, " +
                    "{ \"name\": \"Project Y\", \"status\": \"ongoing\", \"teamSize\": 8, \"durationMonths\": 18 }, " +
                    "{ \"name\": \"Project Z\", \"status\": \"pending\", \"teamSize\": 3, \"durationMonths\": 6 } " +
                "] " +
            "}, " +
            "\"hobbies\": [\"reading\", \"hiking\", \"coding\", \"painting\", \"traveling\", \"cooking\"], " +
            "\"children\": [ " +
                "{ " +
                    "\"name\": \"Emily\", " +
                    "\"age\": 5, " +
                    "\"school\": { " +
                        "\"name\": \"Little Stars Pre-School\", " +
                        "\"grade\": \"Pre-K\", " +
                        "\"subjects\": [\"math\", \"reading\", \"art\"] " +
                    "} " +
                "}, " +
                "{ " +
                    "\"name\": \"Michael\", " +
                    "\"age\": 3, " +
                    "\"school\": { " +
                        "\"name\": \"Little Stars Pre-School\", " +
                        "\"grade\": \"Toddler\", " +
                        "\"subjects\": [\"drawing\", \"music\"] " +
                    "} " +
                "} " +
            "], " +
            "\"vehicles\": [ " +
                "{ \"type\": \"car\", \"make\": \"Tesla\", \"model\": \"Model S\", \"year\": 2020, \"licensePlate\": \"ABC123\" }, " +
                "{ \"type\": \"bike\", \"brand\": \"Giant\", \"model\": \"Escape 3\", \"year\": 2018, \"licensePlate\": null }," +
                "{ \"type\": \"motorcycle\", \"brand\": \"Harley\", \"model\": \"Iron 883\", \"year\": 2021, \"licensePlate\": \"MOTO456\" }" +
            "], " +
            "\"extendedFamily\": { " +
                "\"parents\": { " +
                    "\"father\": { \"name\": \"David\", \"age\": 62 }, " +
                    "\"mother\": { \"name\": \"Mary\", \"age\": 60 } " +
                "}, " +
                "\"siblings\": [ " +
                    "{ \"name\": \"Alice\", \"age\": 35, \"children\": [{ \"name\": \"Tom\", \"age\": 10 }] }, " +
                    "{ \"name\": \"Bob\", \"age\": 28, \"children\": [{ \"name\": \"Jerry\", \"age\": 3 }, { \"name\": \"Lisa\", \"age\": 1 }] }" +
                "], " +
                "\"cousins\": [ " +
                    "{ \"name\": \"Sarah\", \"age\": 25 }, " +
                    "{ \"name\": \"Nick\", \"age\": 22 } " +
                "] " +
            "}, " +
            "\"properties\": [ " +
                "{ " +
                    "\"type\": \"house\", \"location\": \"New York\", \"price\": 850000, \"rooms\": 6, " +
                    "\"amenities\": [\"pool\", \"garage\", \"garden\", \"solar panels\"] " +
                "}, " +
                "{ " +
                    "\"type\": \"apartment\", \"location\": \"San Francisco\", \"price\": 1200000, \"rooms\": 3, " +
                    "\"amenities\": [\"gym\", \"elevator\", \"parking\"] " +
                "} " +
            "], " +
            "\"investments\": [ " +
                "{ \"type\": \"stocks\", \"name\": \"TechCorp\", \"value\": 50000 }, " +
                "{ \"type\": \"real estate\", \"location\": \"New York\", \"value\": 300000 }, " +
                "{ \"type\": \"bonds\", \"value\": 100000 } " +
            "], " +
            "\"medicalHistory\": { " +
                "\"conditions\": [ \"asthma\", \"high blood pressure\" ], " +
                "\"surgeries\": [ \"appendectomy\" ], " +
                "\"medications\": [ " +
                    "{ \"name\": \"Lisinopril\", \"dosage\": \"10mg\", \"schedule\": \"daily\" }, " +
                    "{ \"name\": \"Ventolin\", \"dosage\": \"100mcg\", \"schedule\": \"as needed\" }" +
                "] " +
            "} " +
        "} " +
    "}";
    }

    @Warmup(iterations = 5)  // 5 warm-up iterations
    @Measurement(iterations = 10)  // 10 measurement iterations
    @Benchmark
    public void testMethod() throws IOException {
        // Benchmark code goes here
        // This is where JIT will optimize the hot spots over time
        // Parsing the complicated JSON string using Jackson's ObjectMapper
         Person person = objectMapper.readValue(complicatedJson, Person.class);
    }

    public class Person {
        public String name;
        public int age;
        public String gender;
        public Address address;
        public Phone[] phones;
        public String email;
        public Employment employment;
        public String[] hobbies;
        public Child[] children;
        public Vehicle[] vehicles;
        public ExtendedFamily extendedFamily;
        public Property[] properties;
        public Investment[] investments;
        public MedicalHistory medicalHistory;
    
        // Address class
        public static class Address {
            public String street;
            public String city;
            public String state;
            public String postalCode;
            public Coordinates coordinates;
    
            // Coordinates class
            public static class Coordinates {
                public double latitude;
                public double longitude;
            }
        }
    
        // Phone class
        public static class Phone {
            public String type;
            public String number;
            public Boolean verified;
            public Integer extension;  // Nullable field, Integer allows null
        }
    
        // Employment class
        public static class Employment {
            public String company;
            public String position;
            public String startDate;
            public String endDate;  // Nullable field, since it could be null
            public Salary salary;
            public Project[] projects;
    
            // Salary class
            public static class Salary {
                public double amount;
                public String currency;
            }
    
            // Project class
            public static class Project {
                public String name;
                public String status;
                public int teamSize;
                public int durationMonths;
            }
        }
    
        // Child class
        public static class Child {
            public String name;
            public int age;
            public School school;
    
            // School class
            public static class School {
                public String name;
                public String grade;
                public String[] subjects;
            }
        }
    
        // Vehicle class
        public static class Vehicle {
            public String type;
            public String make;
            public String model;
            public int year;
            public String licensePlate;  // Nullable field for bikes
        }
    
        // Extended Family class
        public static class ExtendedFamily {
            public Parents parents;
            public Sibling[] siblings;
            public Cousin[] cousins;
    
            // Parents class
            public static class Parents {
                public Parent father;
                public Parent mother;
    
                // Parent class
                public static class Parent {
                    public String name;
                    public int age;
                }
            }
    
            // Sibling class
            public static class Sibling {
                public String name;
                public int age;
                public Child[] children;  // Children of siblings (nieces/nephews)
            }
    
            // Cousin class
            public static class Cousin {
                public String name;
                public int age;
            }
        }
    
        // Property class
        public static class Property {
            public String type;
            public String location;
            public int price;
            public int rooms;
            public String[] amenities;
        }
    
        // Investment class
        public static class Investment {
            public String type;
            public String name;
            public int value;
            public String location;  // Nullable for certain types of investments
        }
    
        // MedicalHistory class
        public static class MedicalHistory {
            public String[] conditions;
            public String[] surgeries;
            public Medication[] medications;
    
            // Medication class
            public static class Medication {
                public String name;
                public String dosage;
                public String schedule;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        // Set JVM flags to print compilation and lower compilation threshold
        String[] jvmArgs = new String[] {
            "-XX:+PrintCompilation",
            "-XX:CompileThreshold=100"  // Force compilation after 100 method calls
        };

        org.openjdk.jmh.Main.main(args);
    }
}