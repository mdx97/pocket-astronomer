# API Endpoints
-
### [GET] Time of Sunrise
Arguments

* Day
* Location (latitude/longitude)
 
Returns the time of sunrise at the location provided in the local time.

### [GET] Time of Sunset
Arguments

* Day
* Location (latitude/longitude)

Returns the time of sunset at the location provided in the local time.

### [GET] Lunar Phase
Arguments

* Day

Returns the lunar phase for the night of the given day.

### [GET] Astronomical Events
Arguments

* Start Date
* End Date
* Classifications

Returns a group of Astronomical Event objects that occur in the given date range and are of an appropriate classification.

# Data
### Astronomical Event
Attributes

* Name
* Datetime
* Classification

### Classifications for Astronomical Events

* Lunar Eclipse
* Solar Eclipse
* Lunar Transit
* Solar Transit
* Satellites
* Comets
* Meteor Showers