# Pocket-Astronomer API Documentation

## About

We will be hosting the web service on a cloud hosting platform known as Heroku. The service directly draws data from two different sources...

1. https://sunrise-sunset.org/api for getting sunrise and sunset data.
2. A Postgres database which stores data on lunar phases and astronomical events scraped from multiple different sources.

## Usage

In order to access the service, you need the URL. This is the base URL you will use to build requests off of...

```
https://pocket-astronomer-api.herokuapp.com/
```

## Endpoints

### /sunrise and /sunset

Parameters:

- date
- lat
- lon

Returns:

- time

Example Request:

```
https://pocket-astronomer-api.herokuapp.com/sunrise?date=2019-04-02&lat=40.34&lon=-94.8
```

Example Response:

```
06:00:20 AM
```

### /lunar_phase

Parameters:

- date

Returns:

- date
- phase

Example Request:

```
https://pocket-astronomer-api.herokuapp.com/lunar_phase?date=2019-04-02
```

Example Response:

```
{
	"date": "2019-04-02",
	"phase": "Waning Crescent"
}
```

Possible values for `phase` are:

- New Moon
- Waxing Crescent
- First Quarter
- Waxing Gibbous
- Full Moon
- Waning Gibbous
- Last Quarter
- Waning Crescent

### /events

Parameters:

- start_date
- end_date
- classification

Returns (Collection):

- id
- name
- datetime
- classification

Example Request:

```
https://pocket-astronomer-api.herokuapp.com/events?start_date=2019-04-02&end_date=2099-04-30&classification=Comets
```

Example Response:

```
[
	{
		"id": 1,
		"name": "Halley's Comet Perihelion",
		"datetime": "2061-07-28 00:00:00 AM",
		"classification": "Comets"
	}
]
```

Possible values for `classification` are:

* Lunar Eclipse
* Solar Eclipse
* Transit
* Comets