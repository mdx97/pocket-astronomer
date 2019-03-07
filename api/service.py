from flask import Flask, request
from urllib import parse
from datetime import datetime, date as Date
import requests
import json

class DateLocation:
    def __init__(self, date, lat, lon):
        self.date = date
        self.lat = lat
        self.lon = lon

app = Flask(__name__)

@app.route("/")
def index():
    return "Hello, Flask!"

@app.route("/sunrise")
def sunrise():
    return get_sun_transition_time("sunrise")

@app.route("/sunset")
def sunset():
    return get_sun_transition_time("sunset")

def get_sun_transition_time(transition):
    args = parse_url_arguments(request.url)
    date_location = extract_date_location(args)
    url = build_sunrise_sunset_url(date_location)
    response = requests.get(url)
    data = json.loads(response.text)
    return data["results"][transition]

def build_sunrise_sunset_url(date_location):
    return "https://api.sunrise-sunset.org/json?lat={}&lng={}&date={}".format(date_location.lat, date_location.lon, date_location.date)
    
def parse_url_arguments(url):
    return dict(parse.parse_qsl(parse.urlsplit(url).query))

def extract_date_location(arguments):
    date = datetime.strptime(arguments["date"], "%m-%d-%Y").date()
    lat = float(arguments["lat"])
    lon = float(arguments["lon"])
    return DateLocation(date, lat, lon)

if __name__ == "__main__":
    app.run()