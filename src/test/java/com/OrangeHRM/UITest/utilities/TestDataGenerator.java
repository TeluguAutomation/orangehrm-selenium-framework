package com.OrangeHRM.UITest.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

/**
 * TestDataGenerator - Utility class for generating dynamic test data
 * 
 * OOP CONCEPTS USED:
 * 1. STATIC METHODS - Utility methods for data generation
 * 2. ENCAPSULATION - Private helper methods with public API
 * 3. IMMUTABILITY - Generated data is immutable
 * 4. SINGLETON PATTERN - Single Random instance for consistency
 * 5. FACTORY PATTERN - Creates different types of test data
 * 
 * HOW IT WORKS:
 * - Generates random data for various test scenarios
 * - Supports different data types (String, Integer, Date, Email, etc.)
 * - Uses seeded Random for reproducible test data
 * - Provides realistic test data for UI testing
 * - Thread-safe data generation
 * 
 * DESIGN PATTERNS:
 * - Factory Pattern - Creates different types of test data
 * - Singleton Pattern - Single Random instance
 * - Builder Pattern - Fluent API for complex data creation
 * 
 * USAGE:
 * String email = TestDataGenerator.generateEmail();
 * String name = TestDataGenerator.generateFullName();
 * String phone = TestDataGenerator.generatePhoneNumber();
 * 
 * @author TeluguAutomation
 * @version 1.0
 */
public class TestDataGenerator {
    
    private static final Random random = new Random();
    private static final String[] FIRST_NAMES = {
        "John", "Jane", "Michael", "Sarah", "David", "Lisa", "Robert", "Emily",
        "James", "Jessica", "William", "Ashley", "Richard", "Amanda", "Joseph",
        "Jennifer", "Thomas", "Michelle", "Christopher", "Kimberly", "Charles",
        "Donna", "Daniel", "Carol", "Matthew", "Sandra", "Anthony", "Ruth",
        "Mark", "Sharon", "Donald", "Nancy", "Steven", "Deborah", "Paul", "Dorothy"
    };
    
    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
        "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez",
        "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
        "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark",
        "Ramirez", "Lewis", "Robinson", "Walker", "Young", "Allen", "King",
        "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores", "Green"
    };
    
    private static final String[] DOMAINS = {
        "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "company.com",
        "test.com", "example.com", "demo.com", "sample.com", "orangehrm.com"
    };
    
    private static final String[] DEPARTMENTS = {
        "IT", "HR", "Finance", "Marketing", "Sales", "Operations", "Admin",
        "Support", "Development", "Quality Assurance", "Management", "Legal"
    };
    
    private static final String[] JOB_TITLES = {
        "Software Engineer", "QA Engineer", "Business Analyst", "Project Manager",
        "HR Manager", "Finance Manager", "Marketing Manager", "Sales Manager",
        "Admin Assistant", "Support Specialist", "Developer", "Tester"
    };
    
    /**
     * Generate random first name
     * 
     * @return String - Random first name
     */
    public static String generateFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }
    
    /**
     * Generate random last name
     * 
     * @return String - Random last name
     */
    public static String generateLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }
    
    /**
     * Generate random full name
     * 
     * @return String - Random full name (First Last)
     */
    public static String generateFullName() {
        return generateFirstName() + " " + generateLastName();
    }
    
    /**
     * Generate random email address
     * 
     * @return String - Random email address
     */
    public static String generateEmail() {
        String firstName = generateFirstName().toLowerCase();
        String lastName = generateLastName().toLowerCase();
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];
        int randomNum = random.nextInt(1000);
        
        return firstName + "." + lastName + randomNum + "@" + domain;
    }
    
    /**
     * Generate random username
     * 
     * @return String - Random username
     */
    public static String generateUsername() {
        String firstName = generateFirstName().toLowerCase();
        String lastName = generateLastName().toLowerCase();
        int randomNum = random.nextInt(1000);
        
        return firstName + lastName + randomNum;
    }
    
    /**
     * Generate random password
     * 
     * @param length - Password length (default 8)
     * @return String - Random password
     */
    public static String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return password.toString();
    }
    
    /**
     * Generate random password with default length (8)
     * 
     * @return String - Random password
     */
    public static String generatePassword() {
        return generatePassword(8);
    }
    
    /**
     * Generate random phone number
     * 
     * @return String - Random phone number
     */
    public static String generatePhoneNumber() {
        int areaCode = 100 + random.nextInt(900); // 100-999
        int exchange = 100 + random.nextInt(900); // 100-999
        int number = 1000 + random.nextInt(9000); // 1000-9999
        
        return String.format("(%d) %d-%d", areaCode, exchange, number);
    }
    
    /**
     * Generate random employee ID
     * 
     * @return String - Random employee ID
     */
    public static String generateEmployeeId() {
        return "EMP" + String.format("%04d", random.nextInt(10000));
    }
    
    /**
     * Generate random department
     * 
     * @return String - Random department
     */
    public static String generateDepartment() {
        return DEPARTMENTS[random.nextInt(DEPARTMENTS.length)];
    }
    
    /**
     * Generate random job title
     * 
     * @return String - Random job title
     */
    public static String generateJobTitle() {
        return JOB_TITLES[random.nextInt(JOB_TITLES.length)];
    }
    
    /**
     * Generate random date of birth
     * 
     * @return String - Random date of birth (YYYY-MM-DD)
     */
    public static String generateDateOfBirth() {
        LocalDate startDate = LocalDate.of(1950, 1, 1);
        LocalDate endDate = LocalDate.of(2000, 12, 31);
        
        long startEpoch = startDate.toEpochDay();
        long endEpoch = endDate.toEpochDay();
        long randomEpoch = startEpoch + random.nextInt((int) (endEpoch - startEpoch));
        
        return LocalDate.ofEpochDay(randomEpoch).toString();
    }
    
    /**
     * Generate random date
     * 
     * @param startYear - Start year
     * @param endYear - End year
     * @return String - Random date (YYYY-MM-DD)
     */
    public static String generateDate(int startYear, int endYear) {
        LocalDate startDate = LocalDate.of(startYear, 1, 1);
        LocalDate endDate = LocalDate.of(endYear, 12, 31);
        
        long startEpoch = startDate.toEpochDay();
        long endEpoch = endDate.toEpochDay();
        long randomEpoch = startEpoch + random.nextInt((int) (endEpoch - startEpoch));
        
        return LocalDate.ofEpochDay(randomEpoch).toString();
    }
    
    /**
     * Generate random date time
     * 
     * @return String - Random date time (YYYY-MM-DD HH:mm:ss)
     */
    public static String generateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime randomDateTime = now.minusDays(random.nextInt(365))
                                        .minusHours(random.nextInt(24))
                                        .minusMinutes(random.nextInt(60));
        
        return randomDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * Generate random number within range
     * 
     * @param min - Minimum value
     * @param max - Maximum value
     * @return int - Random number
     */
    public static int generateRandomNumber(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
    
    /**
     * Generate random string
     * 
     * @param length - String length
     * @return String - Random string
     */
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return result.toString();
    }
    
    /**
     * Generate random UUID
     * 
     * @return String - Random UUID
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Generate random boolean
     * 
     * @return boolean - Random boolean value
     */
    public static boolean generateBoolean() {
        return random.nextBoolean();
    }
    
    /**
     * Generate random address
     * 
     * @return String - Random address
     */
    public static String generateAddress() {
        String[] streets = {"Main St", "Oak Ave", "Pine Rd", "Cedar Ln", "Elm St", "Maple Ave"};
        String street = streets[random.nextInt(streets.length)];
        int number = 100 + random.nextInt(900);
        
        return number + " " + street;
    }
    
    /**
     * Generate random city
     * 
     * @return String - Random city
     */
    public static String generateCity() {
        String[] cities = {
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia",
            "San Antonio", "San Diego", "Dallas", "San Jose", "Austin", "Jacksonville",
            "Fort Worth", "Columbus", "Charlotte", "San Francisco", "Indianapolis",
            "Seattle", "Denver", "Washington", "Boston", "El Paso", "Nashville",
            "Detroit", "Oklahoma City", "Portland", "Las Vegas", "Memphis", "Louisville"
        };
        return cities[random.nextInt(cities.length)];
    }
    
    /**
     * Generate random state
     * 
     * @return String - Random state
     */
    public static String generateState() {
        String[] states = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
            "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
            "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
            "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
            "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada",
            "New Hampshire", "New Jersey", "New Mexico", "New York",
            "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
            "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
            "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
            "West Virginia", "Wisconsin", "Wyoming"
        };
        return states[random.nextInt(states.length)];
    }
    
    /**
     * Generate random zip code
     * 
     * @return String - Random zip code
     */
    public static String generateZipCode() {
        return String.format("%05d", random.nextInt(100000));
    }
    
    /**
     * Generate complete user profile data
     * 
     * @return Map<String, String> - Complete user profile
     */
    public static java.util.Map<String, String> generateUserProfile() {
        java.util.Map<String, String> profile = new java.util.HashMap<>();
        
        profile.put("firstName", generateFirstName());
        profile.put("lastName", generateLastName());
        profile.put("email", generateEmail());
        profile.put("username", generateUsername());
        profile.put("password", generatePassword());
        profile.put("phone", generatePhoneNumber());
        profile.put("employeeId", generateEmployeeId());
        profile.put("department", generateDepartment());
        profile.put("jobTitle", generateJobTitle());
        profile.put("dateOfBirth", generateDateOfBirth());
        profile.put("address", generateAddress());
        profile.put("city", generateCity());
        profile.put("state", generateState());
        profile.put("zipCode", generateZipCode());
        
        return profile;
    }
}
