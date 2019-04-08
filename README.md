# Pocket Astronomer

## Team
- Connor Beshears
- Alex Gittemeier
- Mathew Horner
- Randall Porter

## Overall Contributions

### Connor Beshears
Started looking for additional resources to pull data from the web

### Alex Gittemeier
stuff

### Mathew Horner
Wrote the Python REST API we will be using to provide data to our Android application and set up cloud hosting for the service on Heroku.

I created a script to scrape lunar phases from a website and write them to our Postgres DB.

I've also written comprehensive documentation so that we can easily consume this API from our Android application.

### Randall Porter
Code contributions: Set up project, activities, and navigation between activities. Added elements to most of the app screens. Added some functionality to some of the screen elements. 

Team contributions: Created instructions for using Git with Android Studio to work with our project. Created README.md to hold a list of team members and their contributions. Made initial mockup of project for proposal. Created folder for holding project files. Created and submitted the proposal/milestones, and added them to the repo. Added functionality to the location screen.

How to use the location stuff I set up:
Location loc = LocationsActivity.getLocation(); //This returns an object with the current location.
double lat = loc.getLatitude(); //Get latitude 
double lon = loc.getLongitude(); //Get longitude
LocationsActivity.setLocation(newLat, newLon); //Set the location. This takes doubles.
