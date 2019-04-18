# Pocket Astronomer

## Team
- Connor Beshears
- Alex Gittemeier
- Mathew Horner
- Randall Porter

## Overall Contributions

### Connor Beshears
Added persistance for the options

### Alex Gittemeier

- [x] Added non-default app icon
- [x] Spent time cleaning up UI of android app to make it look slicker
- [ ] Incorporate Volley library into app to hit our Heroku instance

### Mathew Horner
Wrote the Python REST API we will be using to provide data to our Android application and set up cloud hosting for the service on Heroku.

I created a script to scrape lunar phases from a website and write them to our Postgres DB.

I've also written comprehensive documentation so that we can easily consume this API from our Android application.

### Randall Porter
Code contributions: Set up project, activities, and navigation between activities. Added elements to most of the app screens. Added some functionality to some of the screen elements. Added functionality to the location screen. Modified location to use shared preferences instead of a static variable. Created a class, LocationTool.java, to keep location code accessible and organized.

Team contributions: Created instructions for using Git with Android Studio to work with our project. Created README.md to hold a list of team members and their contributions. Made initial mockup of project for proposal. Created folder for holding project files. Created and submitted the proposal/milestones, and added them to the repo. 

Locations instructions:
You can work with locations in two ways.
1. Create an instance of my LocationTool object in your Activity class, and use the instance methods
2. Call the static methods in LocationTool.java 
Both ways of working with locations have identical methods, signatures, and behaviors, with the exception that the static methods all need an Activity object as the last parameter. The keyword "this" is a good one to use. Available methods include:
```Java
public LocationTool(Activity sender){}

//Instance Methods
public Location getLocation(){}
public double getLatitude(){}
public double getLongitude(){}
public void setLocation(Location loc){}
public void setLocation(double lat, double lon){}
public void setLatitude(double lat){}
public void setLongitude(double lon){}

// Static Methods
public static Location getLocation(Activity sender){}
public static double getLatitude(Activity sender){}
public static double getLongitude(Activity sender){}
public static void setLocation(Location loc, Activity sender){}
public static void setLocation(double lat, double lon, Activity sender){}
public static void setLatitude(double lat, Activity sender){}
public static void setLongitude(double lon, Activity sender){}
```
